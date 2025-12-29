package jp.levtech.rookie.portfolio.service.master;

import java.util.List;

import jp.levtech.rookie.portfolio.dto.MasterDto;
import jp.levtech.rookie.portfolio.master.MasterType;

public interface MasterService {
	//一覧を取得
	List<MasterDto> list(MasterType type);
	
	//更新
	public void update(MasterType type, Long id, Long categoryid);
	
}
