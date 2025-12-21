package jp.levtech.rookie.portfolio.service.csv.converter.credit;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jp.levtech.rookie.portfolio.dto.mufg.MufgCreditCsvDto;
import jp.levtech.rookie.portfolio.entity.mufg.MufgCreditTransaction;
import jp.levtech.rookie.portfolio.service.csv.common.Utility;
import jp.levtech.rookie.portfolio.service.csv.converter.CsvConverter;

@Component
public class MufgCreditConverter implements CsvConverter<MufgCreditCsvDto, MufgCreditTransaction> {
	
	@Override
	public MufgCreditTransaction toEntity(MufgCreditCsvDto dto) {
		
		MufgCreditTransaction entity = new MufgCreditTransaction();
		
		entity.setConfirmationInfo(dto.getConfirmationInfo().trim());
		entity.setPaymentDate(Utility.normalizeDate(dto.getPaymentDate()));
		entity.setMerchantName(dto.getMerchantName().trim());
		entity.setUsageDate(Utility.normalizeDate(dto.getUsageDate()));
		entity.setAmountYen(Utility.normalizeAmount(dto.getAmountYen()));
		entity.setCreatedAt(LocalDateTime.now());
		
		return entity;
	}
}
