package jp.levtech.rookie.portfolio.repository.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.levtech.rookie.portfolio.dto.MasterDto;

@Mapper
public interface SummaryMasterMapper {
	
	//一覧取得
	List<MasterDto> list();
	
	//更新
	void update(Long id, Long categoryId);
	
	//ID取得
	MasterDto findById(@Param("id") Long id);
}
