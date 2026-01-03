package jp.hiroki.rookie.portfolio.service.summary;

import java.time.YearMonth;

import jp.hiroki.rookie.portfolio.dto.summary.SummaryDto;
import jp.hiroki.rookie.portfolio.dto.summary.viewSummaryDto;

public interface MonthlySummaryService {
	//計算
	SummaryDto getSummary(YearMonth now);
	
	//表示
	viewSummaryDto viewSummary(SummaryDto summary);
}
