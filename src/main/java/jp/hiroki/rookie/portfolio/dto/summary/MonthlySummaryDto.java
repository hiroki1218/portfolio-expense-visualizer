package jp.hiroki.rookie.portfolio.dto.summary;

import java.math.BigDecimal;
import java.time.YearMonth;

import lombok.Data;

@Data
public class MonthlySummaryDto {
	
	private YearMonth month;
	private BigDecimal thisMonthTotal;
	private BigDecimal prevMonthTotal;
	private BigDecimal diffAmount;
	private BigDecimal diffRate;
	
}
