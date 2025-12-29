package jp.levtech.rookie.portfolio.service.master;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import jp.levtech.rookie.portfolio.dto.MasterDto;
import jp.levtech.rookie.portfolio.master.MasterType;
import jp.levtech.rookie.portfolio.repository.master.CategoryMasterMapper;
import jp.levtech.rookie.portfolio.repository.master.DetailMasterMapper;
import jp.levtech.rookie.portfolio.repository.master.MerchantMasterMapper;
import jp.levtech.rookie.portfolio.repository.master.SummaryMasterMapper;

@Service
public class MasterServiceImpl implements MasterService {
	
	private final CategoryMasterMapper categoryMasterMapper;
	private final SummaryMasterMapper summaryMasterMapper;
	private final DetailMasterMapper detailMasterMapper;
	private final MerchantMasterMapper merchantMasterMapper;
	
	public MasterServiceImpl(
			CategoryMasterMapper categoryMasterMapper,
			SummaryMasterMapper summaryMasterMapper,
			DetailMasterMapper detailMasterMapper,
			MerchantMasterMapper merchantMasterMapper) {
		this.categoryMasterMapper = categoryMasterMapper;
		this.summaryMasterMapper = summaryMasterMapper;
		this.detailMasterMapper = detailMasterMapper;
		this.merchantMasterMapper = merchantMasterMapper;
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
	}
}
