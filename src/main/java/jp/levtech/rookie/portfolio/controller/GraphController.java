package jp.levtech.rookie.portfolio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.levtech.rookie.portfolio.dto.graph.CategoryAmountDto;
import jp.levtech.rookie.portfolio.service.graph.GraphService;

@RestController
public class GraphController {
	
	private final GraphService graphService;
	
	public GraphController(GraphService graphService) {
		this.graphService = graphService;
	}
	
	//円グラフを取得
	@GetMapping("/api/graphs/category-amounts")
	public List<CategoryAmountDto> getCategoryAmounts() {
		return graphService.categoryAmounts();
	}
	
}
