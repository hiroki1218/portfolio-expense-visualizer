package jp.hiroki.rookie.portfolio.service.csv.converter;

public interface CsvConverter<T, E> {
	E toEntity(T dto);
}
