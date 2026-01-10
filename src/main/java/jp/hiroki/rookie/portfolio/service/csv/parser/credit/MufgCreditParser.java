package jp.hiroki.rookie.portfolio.service.csv.parser.credit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import jp.hiroki.rookie.portfolio.dto.mufg.MufgCreditCsvDto;
import jp.hiroki.rookie.portfolio.service.csv.parser.CsvParser;

@Component
public class MufgCreditParser implements CsvParser<MufgCreditCsvDto> {
	
	@Override
	public List<MufgCreditCsvDto> parse(Iterable<CSVRecord> records) {
		
		List<MufgCreditCsvDto> creditCsvList = new ArrayList<>();
		
		for (CSVRecord record : records) {
			
			String confirmationInfo = record.get("確定情報");
			String paymentDate = record.get("お支払日");
			String merchantName = record.get("ご利用店名（海外ご利用店名／海外都市名）");
			String usageDate = record.get("ご利用日");
			String numberOfPayments = record.get("支払回数");
			String nthPayment = record.get("何回目");
			String amountYen = record.get("ご利用金額（円）");
			String localCurrencyInfo = record.get("現地通貨額・通貨名称・換算レート");
			
			//最初の2カラムが全部空なら、この行は空行としてスキップ
			if (isBlank(confirmationInfo) && isBlank(paymentDate)) {
				continue;
			}
			
			MufgCreditCsvDto dto = new MufgCreditCsvDto();
			dto.setConfirmationInfo(confirmationInfo);
			dto.setPaymentDate(paymentDate);
			dto.setMerchantName(merchantName);
			dto.setUsageDate(usageDate);
			dto.setNumberOfPayments(numberOfPayments);
			dto.setNthPayment(nthPayment);
			dto.setAmountYen(amountYen);
			dto.setLocalCurrencyInfo(localCurrencyInfo);
			
			creditCsvList.add(dto);
		}
		return creditCsvList;
	}
	
	//	空白データチェック
	private boolean isBlank(String s) {
		return s == null || s.trim().isEmpty();
	}
}
