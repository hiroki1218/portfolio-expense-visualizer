package jp.hiroki.rookie.portfolio.service.csv.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utility {
	
	// 2025/8/3, 2025/08/03 など
	private static final DateTimeFormatter DATE_SLASH = DateTimeFormatter.ofPattern("uuuu/M/d");
	
	// 2025年9月10日 など
	private static final DateTimeFormatter DATE_JP = DateTimeFormatter.ofPattern("uuuu年M月d日");
	
	// 日付の正規化
	public static LocalDate normalizeDate(String raw) {
		if (raw == null || raw.isBlank()) {
			return null;
		}
		
		String s = raw.trim();
		// 念のため "-" が来ても "/" に統一
		s = s.replace('-', '/');
		
		// 1. スラッシュ形式を試す
		try {
			return LocalDate.parse(s, DATE_SLASH);
		} catch (DateTimeParseException ignore) {
			// 失敗したら次の形式を試す
		}
		
		// 2. 日本語形式を試す
		try {
			return LocalDate.parse(s, DATE_JP);
		} catch (DateTimeParseException e) {
			// どちらでも読めなければ明示的にエラーにする
			throw new IllegalArgumentException("対応していない日付形式です: " + raw, e);
		}
	}
	
	//金額の正規化
	public static BigDecimal normalizeAmount(String raw) {
		if (raw == null || raw.isBlank()) {
			return null;
		}
		//カンマ除去
		String cleaned = raw.replace(",", "").trim();
		return new BigDecimal(cleaned);
	}
	
}
