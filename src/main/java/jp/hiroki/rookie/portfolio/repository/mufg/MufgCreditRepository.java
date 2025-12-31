package jp.hiroki.rookie.portfolio.repository.mufg;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.hiroki.rookie.portfolio.dto.mufg.MufgCreditTransactionDtl;
import jp.hiroki.rookie.portfolio.repository.mufg.mapper.MufgCreditMapper;

@Repository
public class MufgCreditRepository {
	private final MufgCreditMapper mapper;
	
	public MufgCreditRepository(MufgCreditMapper mapper) {
		this.mapper = mapper;
	}
	
	//DB保存
	public void saveAll(List<MufgCreditTransactionDtl> entities) {
		mapper.batchInsert(entities);
	}
	
}
