package jp.levtech.rookie.portfolio.service.csv.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jp.levtech.rookie.portfolio.service.csv.enums.CsvFormat;

@Component
public class CsvHeaderDefinitions {
	// ヘッダー情報を定義
	public static final Map<CsvFormat, List<String>> HEADER_DEFINITIONS = Map.of(
			CsvFormat.BANK_MUFG,
			List.of("日付", "摘要", "摘要内容", "支払い金額", "預かり金額",
					"差引残高", "メモ", "未資金化区分", "入払区分"),
			CsvFormat.CREDIT_MUFG,
			List.of("確定情報", "お支払日", "ご利用店名（海外ご利用店名／海外都市名）",
					"ご利用日", "支払回数", "何回目", "ご利用金額（円）",
					"現地通貨額・通貨名称・換算レート"));
}
