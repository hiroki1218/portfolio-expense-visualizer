package jp.hiroki.rookie.portfolio.dto.mufg;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MufgCreditTransactionDtl {
	
	private Long id;
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
