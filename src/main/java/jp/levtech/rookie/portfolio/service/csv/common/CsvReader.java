package jp.levtech.rookie.portfolio.service.csv.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CsvReader {
	
	// データ行を読み取り
	public List<CSVRecord> read(MultipartFile file) throws IOException {
		try (Reader reader = new InputStreamReader(file.getInputStream(), Charset.forName("MS932"))) {
			CSVFormat format = CSVFormat.DEFAULT
					.builder()
					.setHeader()
					.setSkipHeaderRecord(true)
					.setTrim(true)
					.build();
			
			// ここで全部読み切ってリストにする
			return format.parse(reader).getRecords();
		}
	}
	
	// ヘッダー行を読み取り
	public List<String> readHeader(MultipartFile file) throws IOException {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(file.getInputStream(), Charset.forName("MS932")))) {
			
			String header = br.readLine();
			if (header == null) {
				throw new IllegalArgumentException("CSV が空です");
			}
			
			return Arrays.stream(header.split(","))
					.map(String::trim)
					.map(this::removeQuotes) // 前後の " を削る
					.toList();
		}
	}
	
	// 前後の " を取り除く共通メソッド
	private String removeQuotes(String col) {
		if (col != null && col.length() >= 2
				&& col.startsWith("\"")
				&& col.endsWith("\"")) {
			return col.substring(1, col.length() - 1);
		}
		return col;
	}
	
}
