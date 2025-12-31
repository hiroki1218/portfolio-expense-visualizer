package jp.levtech.rookie.portfolio.service.graph;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.levtech.rookie.portfolio.dto.graph.CategoryAmountDto;
import jp.levtech.rookie.portfolio.dto.graph.TargetRange;
import jp.levtech.rookie.portfolio.repository.graph.GraphMapper;

@Service
public class GraphService {
	
	private final GraphMapper graphMapper;
	
	public GraphService(GraphMapper graphMapper) {
		this.graphMapper = graphMapper;
	}
	
	//円グラフ(カテゴリ別合計)
	public List<CategoryAmountDto> categoryAmounts() {
		TargetRange range = getCurrentMonthRange();
		return graphMapper.categoryAmounts(range.getFromDate(), range.getToDate());
	}
	
	//当月の開始日/終了日を返す
	private TargetRange getCurrentMonthRange() {
		YearMonth current = YearMonth.now();
		LocalDate fromDate = current.atDay(1);
		LocalDate toDate = current.atEndOfMonth();
		
		return new TargetRange(fromDate, toDate);
	}
	
	//前月の開始日/終了日を返す
	private TargetRange getBeforeMonthRange() {
		YearMonth previos = YearMonth.now().minusMonths(1);
		LocalDate fromDate = previos.atDay(1);
		LocalDate toDate = previos.atEndOfMonth();
		
		return new TargetRange(fromDate, toDate);
	}
}
