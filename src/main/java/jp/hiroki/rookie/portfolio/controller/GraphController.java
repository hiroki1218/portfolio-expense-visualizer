package jp.hiroki.rookie.portfolio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.hiroki.rookie.portfolio.dto.graph.CategoryAmountDto;
import jp.hiroki.rookie.portfolio.dto.graph.CategoryCompareDto;
import jp.hiroki.rookie.portfolio.dto.graph.MonthlyAmountDto;
import jp.hiroki.rookie.portfolio.service.graph.GraphService;

@RestController
public class GraphController {
	
	private final GraphService graphService;
	
	public GraphController(GraphService graphService) {
		this.graphService = graphService;
	}
	
	//円グラフ
	@GetMapping("/api/graphs/category-amounts")
	public List<CategoryAmountDto> getCategoryAmounts() {
		return graphService.categoryAmounts();
	}
	
	//カテゴリ別棒グラフ
	@GetMapping("/api/graphs/category-compare")
	public List<CategoryCompareDto> getCategoryCompare() {
		return graphService.categoryCompare();
	}
	
	//折れ線グラフ
	@GetMapping("/api/graphs/monthly-amount")
	public List<MonthlyAmountDto> getMonthlyAmount() {
		return graphService.monthAmount();
	}
}
