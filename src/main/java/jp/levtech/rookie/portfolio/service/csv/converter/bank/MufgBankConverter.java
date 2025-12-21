package jp.levtech.rookie.portfolio.service.csv.converter.bank;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jp.levtech.rookie.portfolio.dto.mufg.MufgBankCsvDto;
import jp.levtech.rookie.portfolio.entity.mufg.MufgBankTransaction;
import jp.levtech.rookie.portfolio.service.csv.common.Utility;
import jp.levtech.rookie.portfolio.service.csv.converter.CsvConverter;

@Component
public class MufgBankConverter implements CsvConverter<MufgBankCsvDto, MufgBankTransaction> {
	
	private static final long UNCLASSIFIED_CATEGORY_ID = 15L;
	
	@Override
	public MufgBankTransaction toEntity(MufgBankCsvDto dto) {
		
		MufgBankTransaction entity = new MufgBankTransaction();
		
		entity.setTransactionDate(Utility.normalizeDate(dto.getTransactionDate()));
		entity.setSummary(dto.getSummary());
		entity.setDetail(dto.getDetail());
		entity.setWithdrawalAmount(Utility.normalizeAmount(dto.getWithdrawalAmount()));
		entity.setDepositAmount(Utility.normalizeAmount(dto.getDepositAmount()));
		entity.setBalance(Utility.normalizeAmount(dto.getBalance()));
		entity.setCreatedAt(LocalDateTime.now());
		
		entity.setCategoryId(UNCLASSIFIED_CATEGORY_ID);
		
		return entity;
		
	}
}
