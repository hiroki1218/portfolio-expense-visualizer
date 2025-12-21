package jp.levtech.rookie.portfolio.repository.mufg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.levtech.rookie.portfolio.entity.mufg.MufgCreditTransaction;

@Mapper
public interface MufgCreditMapper {
	
	// 複数件保存（CSV用）
	void batchInsert(@Param("entities") List<MufgCreditTransaction> entities);
	
}
