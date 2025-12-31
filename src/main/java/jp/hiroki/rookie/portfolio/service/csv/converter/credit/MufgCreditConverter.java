package jp.hiroki.rookie.portfolio.service.csv.converter.credit;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jp.hiroki.rookie.portfolio.dto.mufg.MufgCreditCsvDto;
import jp.hiroki.rookie.portfolio.dto.mufg.MufgCreditTransactionDtl;
import jp.hiroki.rookie.portfolio.service.csv.common.Utility;
import jp.hiroki.rookie.portfolio.service.csv.converter.CsvConverter;

@Component
public class MufgCreditConverter implements CsvConverter<MufgCreditCsvDto, MufgCreditTransactionDtl> {
	
	@Override
	public MufgCreditTransactionDtl toEntity(MufgCreditCsvDto dto) {
		
		MufgCreditTransactionDtl entity = new MufgCreditTransactionDtl();
		
		entity.setConfirmationInfo(dto.getConfirmationInfo().trim());
		entity.setPaymentDate(Utility.normalizeDate(dto.getPaymentDate()));
		entity.setMerchantName(dto.getMerchantName().trim());
		entity.setUsageDate(Utility.normalizeDate(dto.getUsageDate()));
		entity.setAmountYen(Utility.normalizeAmount(dto.getAmountYen()));
		entity.setCreatedAt(LocalDateTime.now());
		
		return entity;
	}
}
