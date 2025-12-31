package jp.hiroki.rookie.portfolio.dto.graph;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MonthlyAmountDto {
	private final LocalDate month;
	private final Long totalAmount;
}
