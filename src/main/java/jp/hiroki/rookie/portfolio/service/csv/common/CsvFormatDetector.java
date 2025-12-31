package jp.hiroki.rookie.portfolio.service.csv.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jp.hiroki.rookie.portfolio.service.csv.enums.CsvFormat;

@Component
public class CsvFormatDetector {
	public CsvFormat detect(List<String> headerColumns) {
		
		//ヘッダー情報の項目が一致するか検証
		for (Map.Entry<CsvFormat, List<String>> entry : CsvHeaderDefinitions.HEADER_DEFINITIONS.entrySet()) {
			
			if (headerColumns.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		throw new IllegalArgumentException("対応していない CSV フォーマットです。");
	}
}
