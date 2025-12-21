package jp.levtech.rookie.portfolio.repository.mufg;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.levtech.rookie.portfolio.entity.mufg.MufgCreditTransaction;
import jp.levtech.rookie.portfolio.repository.mufg.mapper.MufgCreditMapper;

@Repository
public class MufgCreditRepository {
	private final MufgCreditMapper mapper;
	
	public MufgCreditRepository(MufgCreditMapper mapper) {
		this.mapper = mapper;
	}
	
	//DB保存
	public void saveAll(List<MufgCreditTransaction> entities) {
		mapper.batchInsert(entities);
	}
	
}
