package jp.levtech.rookie.portfolio.service.summary;

import java.time.YearMonth;

import jp.levtech.rookie.portfolio.dto.summary.MonthlySummaryDto;

public interface MonthlySummaryService {
	MonthlySummaryDto getSummary(YearMonth targetMonth);
}
