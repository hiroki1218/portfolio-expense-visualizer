package jp.hiroki.rookie.portfolio.dto.mufg;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MufgBankTransactionDto {
	
	private Long id;
	// 1. 日付（取引日）
	private LocalDate transactionDate;
	// 2. 摘要（カテゴリ）
	private String summary;
	// 3. 摘要内容（店名・振込元など）
	private String detail;
	// 4. 出金金額（支払い）
	private BigDecimal withdrawalAmount;
	// 5. 入金金額（預かり）
	private BigDecimal depositAmount;
	// 6. 差引残高（残高）
	private BigDecimal balance;
	// 7. カテゴリID
	private Long categoryId;
	// 8. 作成日時
	private LocalDateTime createdAt;
}
