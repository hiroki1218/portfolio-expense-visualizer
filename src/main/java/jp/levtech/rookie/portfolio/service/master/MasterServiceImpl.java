package jp.levtech.rookie.portfolio.service.master;

import java.util.List;

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
	
	@Override
	public List<MasterDto> list(MasterType type) {
		return switch (type) {
		case CATEGORY -> categoryMasterMapper.list();
		case BANK_SUMMARY -> summaryMasterMapper.list();
		case BANK_DETAIL -> detailMasterMapper.list();
		case CREDIT_MERCHANT -> merchantMasterMapper.list();
		};
	}
	
}
