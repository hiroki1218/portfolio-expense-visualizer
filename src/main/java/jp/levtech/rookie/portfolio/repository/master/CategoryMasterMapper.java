package jp.levtech.rookie.portfolio.repository.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.levtech.rookie.portfolio.dto.master.MasterDto;

@Mapper
public interface CategoryMasterMapper {
	
	//一覧表示
	List<MasterDto> list();
	
	//新規登録
	void insert(@Param("categoryName") String categoryName);
	
	//更新
	void update(@Param("id") Long id,
			@Param("categoryName") String categoryName);
	
	//削除
	void delete(@Param("id") Long id);
	
	//1件取得
	MasterDto findById(@Param("id") Long id);
}
