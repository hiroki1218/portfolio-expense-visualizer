package jp.levtech.rookie.portfolio.controller;

import java.time.YearMonth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.levtech.rookie.portfolio.dto.MonthlySummaryDto;
import jp.levtech.rookie.portfolio.service.summary.MonthlySummaryService;

@Controller
public class HomeController {
	
	private final MonthlySummaryService monthlySummaryService;
	
	public HomeController(MonthlySummaryService monthlySummaryService) {
		this.monthlySummaryService = monthlySummaryService;
	}
	
	@GetMapping("/")
	public String home(Model model) {
		
		//操作している端末の「今月」を対象月
		YearMonth now = YearMonth.now().minusMonths(1);
		
		//今月＋前月のサマリーを取得
		MonthlySummaryDto summary = monthlySummaryService.getSummary(now);
		
		//画面に渡す
		model.addAttribute("summary", summary);
		
		return "home";
	}
}
