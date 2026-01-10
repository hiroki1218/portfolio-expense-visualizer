package jp.hiroki.rookie.portfolio.service.csv;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jp.hiroki.rookie.portfolio.repository.mufg.MufgBankRepository;
import jp.hiroki.rookie.portfolio.repository.mufg.MufgCreditRepository;
import jp.hiroki.rookie.portfolio.service.csv.common.CsvFormatDetector;
import jp.hiroki.rookie.portfolio.service.csv.common.CsvReader;
import jp.hiroki.rookie.portfolio.service.csv.converter.bank.MufgBankConverter;
import jp.hiroki.rookie.portfolio.service.csv.converter.credit.MufgCreditConverter;
import jp.hiroki.rookie.portfolio.service.csv.enums.CsvFormat;
import jp.hiroki.rookie.portfolio.service.csv.parser.bank.MufgBankParser;
import jp.hiroki.rookie.portfolio.service.csv.parser.credit.MufgCreditParser;
import jp.hiroki.rookie.portfolio.service.master.MasterServiceImpl;

@Service
public class ImportCsvServiceImpl implements ImportCsvService {
	
	private final CsvReader csvReader;
	private final CsvFormatDetector csvFormatDetector;
	private final MufgBankParser bankParser;
	private final MufgCreditParser creditParser;
	private final MufgBankConverter bankConverter;
	private final MufgCreditConverter creditConverter;
	private final MufgBankRepository bankRepository;
	private final MufgCreditRepository creditRepository;
	private final MasterServiceImpl masterSyncService;
	
	public ImportCsvServiceImpl(
			CsvReader csvReader,
			CsvFormatDetector csvFormatDetector,
			MufgBankParser bankParser,
			MufgCreditParser creditParser,
			MufgBankConverter bankConverter,
			MufgCreditConverter creditConverter,
			MufgBankRepository bankRepository,
			MufgCreditRepository creditRepository,
			MasterServiceImpl masterSyncService) {
		this.csvReader = csvReader;
		this.csvFormatDetector = csvFormatDetector;
		this.bankParser = bankParser;
		this.creditParser = creditParser;
		this.bankConverter = bankConverter;
		this.creditConverter = creditConverter;
		this.bankRepository = bankRepository;
		this.creditRepository = creditRepository;
		this.masterSyncService = masterSyncService;
	}
	
	@Override
	public void importCsv(MultipartFile file) {
		
		try {
			// 1. ヘッダー判定
			List<String> header = csvReader.readHeader(file);
			CsvFormat format = csvFormatDetector.detect(header);
			
			// 2. データ行読み込み
			Iterable<CSVRecord> records = csvReader.read(file);
			
			// 3. フォーマットごとの処理
			switch (format) {
			
			// MUFG 銀行 CSV
			case BANK_MUFG -> {
				// DTO にパース
				var dtoList = bankParser.parse(records);
				
				// DTO → Entity
				var entityList = dtoList.stream()
						.map(bankConverter::toEntity)
						.toList();
				
				// DB 保存
				bankRepository.saveAll(entityList);
				masterSyncService.syncBankMasters();
			}
			
			// MUFG クレカ CSV
			case CREDIT_MUFG -> {
				var dtoList = creditParser.parse(records);
				
				var entityList = dtoList.stream()
						.map(creditConverter::toEntity)
						.toList();
				
				creditRepository.saveAll(entityList);
				masterSyncService.syncCreditMasters();
			}
			}
			
		} catch (Exception e) {
			// 想定外エラー時のログ・例外
			throw new RuntimeException("CSV 取り込み中にエラーが発生しました", e);
		}
	}
}
