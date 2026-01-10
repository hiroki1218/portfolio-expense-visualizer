package jp.hiroki.rookie.portfolio.controller;

import java.time.YearMonth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.hiroki.rookie.portfolio.dto.summary.SummaryDto;
import jp.hiroki.rookie.portfolio.dto.summary.viewSummaryDto;
import jp.hiroki.rookie.portfolio.service.summary.MonthlySummaryService;

@Controller
public class HomeController {
	
	private final MonthlySummaryService monthlySummaryService;
	
	public HomeController(MonthlySummaryService monthlySummaryService) {
		this.monthlySummaryService = monthlySummaryService;
	}
	
	@GetMapping("/")
	public String home(Model model) {
		
		YearMonth now = YearMonth.now();
		
		SummaryDto summary = monthlySummaryService.getSummary(now.minusMonths(1));
		viewSummaryDto viewLabel = monthlySummaryService.viewSummary(summary);
		
		model.addAttribute("Label", viewLabel);
		return "home";
	}
	
	@GetMapping("/graph/monthly-amount")
	public String monthlyAmount() {
		return "graph/monthlyAmount";
	}
}
