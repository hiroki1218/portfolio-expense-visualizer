package jp.levtech.rookie.portfolio.service.csv.converter;

public interface CsvConverter<T, E> {
	E toEntity(T dto);
}
