package jp.hiroki.rookie.portfolio.dto.summary;

import lombok.Data;

@Data
public class viewSummaryDto {
	private String currentMonthLabel;
	private String currentMonthTotalLabel;
	private String previousMonthTotalLabel;
	private String diffAmountLabel;
	private String diffPercentLabel;
}
