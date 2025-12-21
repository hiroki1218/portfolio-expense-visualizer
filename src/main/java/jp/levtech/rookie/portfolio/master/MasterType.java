package jp.levtech.rookie.portfolio.master;

import lombok.Getter;

@Getter
public enum MasterType {
	CATEGORY("category", "カテゴリ"), BANK_SUMMARY("bank-summary", "銀行-摘要"), BANK_DETAIL("bank-detail",
			"銀行-摘要内容"), CREDIT_MERCHANT("credit-merchant", "クレカ-利用店");
	
	private final String path;
	private final String label;
	
	MasterType(String path, String label) {
		this.path = path;
		this.label = label;
	}
	
	public static MasterType fromPath(String path) {
		
		if (path == null || path.isBlank()) {
			throw new IllegalArgumentException("typeが未指定です。");
		}
		
		for (MasterType t : values()) {
			if (t.path.equals(path)) {
				return t;
			}
		}
		throw new IllegalArgumentException("不正なtypeです:" + path);
	}
}
