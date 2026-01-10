package jp.hiroki.rookie.portfolio.repository.master;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MasterSyncMapper {
	//マスターテーブルの同期
	int syncCreditMerchantMaster();
	
	int syncBankDetailMaster();
	
	int syncBankSummaryMaster();
	
	//transactionテーブルに同期
	int syncCreditByMerchantMasterId(@Param("masterId") Long masterId);
	
	int syncBankByDetailMasterId(@Param("masterId") Long masterId);
	
	int syncBankBySummaryMasterId(@Param("masterId") Long masterId);
}
