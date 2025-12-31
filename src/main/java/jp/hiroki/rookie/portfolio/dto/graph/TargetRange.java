package jp.hiroki.rookie.portfolio.dto.graph;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TargetRange {
	
	private final LocalDate fromDate;
	private final LocalDate toDate;
	
}
