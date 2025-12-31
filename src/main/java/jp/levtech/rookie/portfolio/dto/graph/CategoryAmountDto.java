package jp.levtech.rookie.portfolio.dto.graph;

import lombok.Data;

@Data
public class CategoryAmountDto {
	
	//円グラフ
	private final String categoryName;
	private final Long totalAmount;
	
}
