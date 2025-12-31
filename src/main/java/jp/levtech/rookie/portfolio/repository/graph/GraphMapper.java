package jp.levtech.rookie.portfolio.repository.graph;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.levtech.rookie.portfolio.dto.graph.CategoryAmountDto;

@Mapper
public interface GraphMapper {
	
	//円グラフ
	List<CategoryAmountDto> categoryAmounts(
			@Param("fromDate") LocalDate fromDate,
			@Param("toDate") LocalDate toDate);
}
