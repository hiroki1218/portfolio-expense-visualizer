package jp.hiroki.rookie.portfolio.dto.mufg;

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
public class MufgCreditTransactionDtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 主キー（自動採番）
	// 1. 確定情報（確定 / 未確定 等）
	private String confirmationInfo;
	// 2. お支払日
	private LocalDate paymentDate;
	// 3. ご利用店名
	private String merchantName;
	// 4. ご利用日
	private LocalDate usageDate;
	// 5. ご利用金額（円）
	private BigDecimal amountYen;
	// 6. 作成日時
	private LocalDateTime createdAt;
}
