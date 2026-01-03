package jp.hiroki.rookie.portfolio.repository.mufg;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import jp.hiroki.rookie.portfolio.dto.mufg.MufgBankTransactionDto;
import jp.hiroki.rookie.portfolio.repository.mufg.mapper.MufgBankMapper;

@Repository
public class MufgBankRepository {
	
	private final MufgBankMapper mapper;
	
	public MufgBankRepository(MufgBankMapper mapper) {
		this.mapper = mapper;
	}
	
	//DB保存
	public void saveAll(List<MufgBankTransactionDto> entities) {
		mapper.batchInsert(entities);
	}
	
	public BigDecimal getAmountTotal(LocalDate start, LocalDate end) {
		return mapper.getAmountTotal(start, end);
	}
	
}
