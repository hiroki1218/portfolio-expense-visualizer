package jp.hiroki.rookie.portfolio.repository.graph;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.hiroki.rookie.portfolio.dto.graph.CategoryAmountDto;
import jp.hiroki.rookie.portfolio.dto.graph.CategoryCompareDto;
import jp.hiroki.rookie.portfolio.dto.graph.MonthlyAmountDto;

@Mapper
public interface GraphMapper {
	
	//円グラフ
	List<CategoryAmountDto> categoryAmounts(
			@Param("currentFrom") LocalDate currentFrom,
			@Param("currentTo") LocalDate currentTo);
	
	//カテゴリ別棒グラフ
	List<CategoryCompareDto> categoryCompare(
			@Param("previousFrom") LocalDate previousFrom,
			@Param("previousTo") LocalDate previousTo,
			@Param("currentFrom") LocalDate currentFrom,
			@Param("currentTo") LocalDate currentTo);
	
	//月別折れ線グラフ
	List<MonthlyAmountDto> monthlyAmount();
	
}
