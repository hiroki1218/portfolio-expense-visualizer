package jp.levtech.rookie.portfolio.service.master;

import java.util.List;

import jp.levtech.rookie.portfolio.dto.MasterDto;
import jp.levtech.rookie.portfolio.master.MasterType;

public interface MasterService {
	List<MasterDto> list(MasterType type);
}
