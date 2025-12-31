package jp.hiroki.rookie.portfolio.dto.graph;

import lombok.Data;

@Data
public class CategoryAmountDto {
	private final String categoryName;
	private final Long totalAmount;
}
