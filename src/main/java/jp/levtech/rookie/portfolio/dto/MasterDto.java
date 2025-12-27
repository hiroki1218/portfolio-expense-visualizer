package jp.levtech.rookie.portfolio.dto;

import lombok.Data;

@Data
public class MasterDto {
	private Long id;
	private String name;
	private Long categoryId;
	private String categoryName;
}
