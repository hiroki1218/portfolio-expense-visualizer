package jp.hiroki.rookie.portfolio.dto.graph;

import lombok.Data;

@Data
public class CategoryCompareDto {
	private final String categoryName;
	private final Long previousTotalAmount;
	private final Long currentTotalAmount;
}
