package jp.levtech.rookie.portfolio.service.csv.parser.bank;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import jp.levtech.rookie.portfolio.dto.mufg.MufgBankCsvDto;
import jp.levtech.rookie.portfolio.service.csv.parser.CsvParser;

@Component
public class MufgBankParser implements CsvParser<MufgBankCsvDto> {
	
	@Override
	public List<MufgBankCsvDto> parse(Iterable<CSVRecord> records) {
		
		List<MufgBankCsvDto> bankCsvList = new ArrayList<>();
		
		for (CSVRecord record : records) {
			
			String transactionDate = record.get("日付");
			String summary = record.get("摘要");
			String detail = record.get("摘要内容");
			String withdrawal = record.get("支払い金額");
			String deposit = record.get("預かり金額");
			String balance = record.get("差引残高");
			
			//先頭2列が空白チェック
			if (isBlank(transactionDate) && isBlank(summary)) {
				continue;
			}
			
			MufgBankCsvDto dto = new MufgBankCsvDto();
			dto.setTransactionDate(transactionDate);
			dto.setSummary(summary);
			dto.setDetail(detail);
			dto.setWithdrawalAmount(withdrawal);
			dto.setDepositAmount(deposit);
			dto.setBalance(balance);
			
			bankCsvList.add(dto);
		}
		return bankCsvList;
	}
	
	//	空白データチェック
	private boolean isBlank(String s) {
		return s == null || s.trim().isEmpty();
	}
}
