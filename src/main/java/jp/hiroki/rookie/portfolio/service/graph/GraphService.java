package jp.hiroki.rookie.portfolio.service.graph;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.hiroki.rookie.portfolio.dto.graph.CategoryAmountDto;
import jp.hiroki.rookie.portfolio.dto.graph.CategoryCompareDto;
import jp.hiroki.rookie.portfolio.dto.graph.MonthlyAmountDto;
import jp.hiroki.rookie.portfolio.dto.graph.TargetRange;
import jp.hiroki.rookie.portfolio.repository.graph.GraphMapper;

@Service
public class GraphService {
	
	private final GraphMapper graphMapper;
	
	public GraphService(GraphMapper graphMapper) {
		this.graphMapper = graphMapper;
	}
	
	//円グラフ
	public List<CategoryAmountDto> categoryAmounts() {
		TargetRange range = getCurrentMonthRange();
		return graphMapper.categoryAmounts(range.getFromDate(), range.getToDate());
	}
	
	//カテゴリ別棒グラフ
	public List<CategoryCompareDto> categoryCompare() {
		TargetRange previousRange = getPreviousMonthRange();
		TargetRange currentRange = getCurrentMonthRange();
		return graphMapper.categoryCompare(
				previousRange.getFromDate(),
				previousRange.getToDate(),
				currentRange.getFromDate(),
				currentRange.getToDate());
	}
	
	//月別折れ線グラフ
	public List<MonthlyAmountDto> monthAmount() {
		return graphMapper.monthlyAmount();
	}
	
	//当月の開始日/終了日を返す
	private TargetRange getCurrentMonthRange() {
		YearMonth current = YearMonth.now();
		LocalDate currentFrom = current.atDay(1);
		LocalDate currentTo = current.atEndOfMonth();
		
		return new TargetRange(currentFrom, currentTo);
	}
	
	//前月の開始日/終了日を返す
	private TargetRange getPreviousMonthRange() {
		YearMonth previous = YearMonth.now().minusMonths(2);
		LocalDate previousFrom = previous.atDay(1);
		LocalDate previousTo = previous.atEndOfMonth();
		
		return new TargetRange(previousFrom, previousTo);
	}
}
