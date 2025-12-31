package jp.hiroki.rookie.portfolio.entity.mufg;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MufgBankTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 主キー（自動採番）
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
