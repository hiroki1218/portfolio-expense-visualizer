package jp.levtech.rookie.portfolio.service.csv.parser;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public interface CsvParser<T> {
	List<T> parse(Iterable<CSVRecord> records);
}
