package jp.levtech.rookie.portfolio.master;

import lombok.Getter;

@Getter
public enum MasterType {
	CATEGORY("category", "カテゴリ"), BANK_SUMMARY("bank-summary", "銀行-摘要"), BANK_DETAIL("bank-detail",
			"銀行-摘要内容"), CREDIT_MERCHANT("credit-merchant", "クレカ-利用店");
	
	private final String type;
	private final String label;
	
	MasterType(String type, String label) {
		this.type = type;
		this.label = label;
	}
	
	public static MasterType fromPath(String type) {
		
		if (type == null || type.isBlank()) {
			throw new IllegalArgumentException("typeが未指定です。");
		}
		
		for (MasterType table : values()) {
			if (table.type.equals(type)) {
				return table;
			}
		}
		throw new IllegalArgumentException("不正なtypeです:" + type);
	}
}
