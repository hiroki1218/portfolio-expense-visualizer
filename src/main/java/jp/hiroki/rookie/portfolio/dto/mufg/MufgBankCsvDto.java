package jp.hiroki.rookie.portfolio.dto.mufg;

import lombok.Data;

@Data
public class MufgBankCsvDto {
	// 1. 日付（取引日）
	private String transactionDate;
	// 2. 摘要（例：振込、カード引落など）
	private String summary;
	// 3. 摘要内容（店名や取引相手など）
	private String detail;
	// 4. 支払い金額（出金）
	private String withdrawalAmount;
	// 5. 預かり金額（入金）
	private String depositAmount;
	// 6. 差引残高（口座残高）
	private String balance;
}
