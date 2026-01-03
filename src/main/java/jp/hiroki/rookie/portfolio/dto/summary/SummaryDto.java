package jp.hiroki.rookie.portfolio.dto.summary;

import java.math.BigDecimal;
import java.time.YearMonth;

import lombok.Data;

@Data
public class SummaryDto {
	
	private YearMonth currentMonth;
	private BigDecimal currentMonthTotal;
	private BigDecimal previousMonthTotal;
	private BigDecimal diffAmount;
	private BigDecimal diffRate;
	private BigDecimal diffPercent;
	private BigDecimal ratio;
	
}
