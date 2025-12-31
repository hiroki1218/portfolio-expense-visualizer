package jp.hiroki.rookie.portfolio.service.summary;

import java.time.YearMonth;

import jp.hiroki.rookie.portfolio.dto.summary.MonthlySummaryDto;

public interface MonthlySummaryService {
	MonthlySummaryDto getSummary(YearMonth targetMonth);
}
