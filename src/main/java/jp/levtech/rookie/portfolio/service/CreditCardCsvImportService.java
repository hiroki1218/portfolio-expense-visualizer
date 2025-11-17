package jp.levtech.rookie.portfolio.service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jp.levtech.rookie.portfolio.dto.CreditCardImportDto;
import jp.levtech.rookie.portfolio.entity.CreditCardTransaction;
import jp.levtech.rookie.portfolio.repository.CreditCardTransactionRepository;

@Service
public class CreditCardCsvImportService {

	@Autowired
    private CreditCardTransactionRepository repository;

	public List<CreditCardImportDto> importCreditCardCsv(MultipartFile file) throws Exception {

		List<CreditCardImportDto> list = new ArrayList<>();
		List<CreditCardTransaction> entityList = new ArrayList<>();

		try (Reader reader = new InputStreamReader(
        		file.getInputStream(),Charset.forName("MS932")
        	)){
				CSVFormat format = CSVFormat.DEFAULT
						.builder()
				        .setHeader()
				        .setSkipHeaderRecord(true)
				        .setTrim(true)
				        .build();

				Iterable<CSVRecord> records = format.parse(reader);
				boolean isFirst = true;

				for(CSVRecord record:records) {

					//2行目は不要のためスキップ
					if(isFirst) {
						isFirst = false;
						continue;
					}

					//dtoインスタンスを生成
					CreditCardImportDto dto = new CreditCardImportDto();
					//各フィールドごとに格納
					dto.setConfirmationInfo(record.get("確定情報"));
					dto.setPaymentDate(record.get("お支払日"));
					dto.setMerchantName(record.get("ご利用店名（海外ご利用店名／海外都市名）"));
					dto.setUsageDate(record.get("ご利用日"));
					dto.setNumberOfPayments(record.get("支払回数"));
					dto.setNthPayment(record.get("何回目"));
					dto.setAmountYen(record.get("ご利用金額（円）"));
					dto.setLocalCurrencyInfo(record.get("現地通貨額・通貨名称・換算レート"));
					list.add(dto);

					CreditCardTransaction entity = new  CreditCardTransaction();
					entity.setConfirmationInfo(dto.getConfirmationInfo().trim());
					entity.setPaymentDate(normalizeDate(dto.getPaymentDate()));
					entity.setMerchantName(dto.getMerchantName().trim());
					entity.setUsageDate(normalizeDate(dto.getUsageDate()));
					entity.setAmountYen(normalizeAmount(dto.getAmountYen()));
					entity.setCreatedAt(LocalDateTime.now());
					entityList.add(entity);
				}
        }
		repository.saveAll(entityList);
        return list;
	}

	//日付の正規化
	private LocalDate normalizeDate(String raw) {
		if(raw == null || raw.isBlank()) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu年M月d日");
		return LocalDate.parse(raw.trim(),formatter);
	}

	//金額の正規化
	private BigDecimal normalizeAmount(String raw) {
		if(raw == null || raw.isBlank()) {
			return null;
		}
		//カンマ除去
		String cleaned = raw.replace(",", "").trim();
		return new BigDecimal(cleaned);
	}
}
