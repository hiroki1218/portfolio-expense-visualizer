package jp.hiroki.rookie.portfolio.repository.mufg.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.hiroki.rookie.portfolio.dto.mufg.MufgBankTransactionDto;

@Mapper
public interface MufgBankMapper {
	
	// 複数件保存（CSV用）
	void batchInsert(@Param("entities") List<MufgBankTransactionDto> entities);
	
	BigDecimal sumSpending(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
