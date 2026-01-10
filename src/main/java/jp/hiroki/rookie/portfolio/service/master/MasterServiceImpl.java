package jp.hiroki.rookie.portfolio.service.master;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.hiroki.rookie.portfolio.dto.master.MasterDto;
import jp.hiroki.rookie.portfolio.master.MasterType;
import jp.hiroki.rookie.portfolio.repository.master.CategoryMasterMapper;
import jp.hiroki.rookie.portfolio.repository.master.DetailMasterMapper;
import jp.hiroki.rookie.portfolio.repository.master.MasterSyncMapper;
import jp.hiroki.rookie.portfolio.repository.master.MerchantMasterMapper;
import jp.hiroki.rookie.portfolio.repository.master.SummaryMasterMapper;

@Service
public class MasterServiceImpl implements MasterService {
	
	private final CategoryMasterMapper categoryMasterMapper;
	private final SummaryMasterMapper summaryMasterMapper;
	private final DetailMasterMapper detailMasterMapper;
	private final MerchantMasterMapper merchantMasterMapper;
	private final MasterSyncMapper masterSyncMapper;
	
	public MasterServiceImpl(
			CategoryMasterMapper categoryMasterMapper,
			SummaryMasterMapper summaryMasterMapper,
			DetailMasterMapper detailMasterMapper,
			MerchantMasterMapper merchantMasterMapper,
			MasterSyncMapper masterSyncMapper) {
		this.categoryMasterMapper = categoryMasterMapper;
		this.summaryMasterMapper = summaryMasterMapper;
		this.detailMasterMapper = detailMasterMapper;
		this.merchantMasterMapper = merchantMasterMapper;
		this.masterSyncMapper = masterSyncMapper;
	}
	
	//一覧取得
	@Override
	public List<MasterDto> list(MasterType type) {
		return switch (type) {
		case CATEGORY -> categoryMasterMapper.list();
		case BANK_SUMMARY -> summaryMasterMapper.list();
		case BANK_DETAIL -> detailMasterMapper.list();
		case CREDIT_MERCHANT -> merchantMasterMapper.list();
		};
	}
	
	//カテゴリ更新
	@Override
	@Transactional
	public void update(MasterType type, Long id, Long categoryId) {
		
		if (id == null || categoryId == null) {
			throw new IllegalArgumentException("更新対象が不正です");
		}
		
		MasterDto current = switch (type) {
		case BANK_SUMMARY -> summaryMasterMapper.findById(id);
		case BANK_DETAIL -> detailMasterMapper.findById(id);
		case CREDIT_MERCHANT -> merchantMasterMapper.findById(id);
		default -> throw new IllegalArgumentException("更新対象外です");
		};
		
		if (Objects.equals(categoryId, current.getCategoryId())) {
			throw new IllegalArgumentException("更新内容が既存値と同一です");
		}
		
		switch (type) {
		case BANK_SUMMARY -> summaryMasterMapper.update(id, categoryId);
		case BANK_DETAIL -> detailMasterMapper.update(id, categoryId);
		case CREDIT_MERCHANT -> merchantMasterMapper.update(id, categoryId);
		case CATEGORY -> throw new IllegalArgumentException("カテゴリは更新対象外です");
		}
		switch (type) {
		case CREDIT_MERCHANT -> masterSyncMapper.syncCreditByMerchantMasterId(id);
		case BANK_DETAIL -> masterSyncMapper.syncBankByDetailMasterId(id);
		case BANK_SUMMARY -> masterSyncMapper.syncBankBySummaryMasterId(id);
		default -> {
			/* 同期なし */ }
		}
	}
	
	//同期処理
	@Override
	@Transactional
	public void syncBankMasters() {
		masterSyncMapper.syncBankDetailMaster();
		masterSyncMapper.syncBankSummaryMaster();
	}
	
	//同期処理
	@Override
	public void syncCreditMasters() {
		masterSyncMapper.syncCreditMerchantMaster();
	}
}
