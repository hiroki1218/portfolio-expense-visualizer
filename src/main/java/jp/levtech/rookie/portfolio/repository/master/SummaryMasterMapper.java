package jp.levtech.rookie.portfolio.repository.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.levtech.rookie.portfolio.dto.MasterDto;

@Mapper
public interface SummaryMasterMapper {
	List<MasterDto> list();
}
