package jp.levtech.rookie.portfolio.dto;

import lombok.Data;

@Data
public class CreditCardImportDto {
	    // 1. 確定情報（確定 / 未確定 等）
	    private String confirmationInfo;
	    // 2. お支払日
	    private String paymentDate;
	    // 3. ご利用店名（海外ご利用店名／海外都市名）
	    private String merchantName;
	    // 4. ご利用日
	    private String usageDate;
	    // 5. 支払回数
	    private String numberOfPayments;
	    // 6. 何回目
	    private String nthPayment;
	    // 7. ご利用金額（円）
	    private String amountYen;
	    // 8. 現地通貨額・通貨名称・換算レート
	    private String localCurrencyInfo;
}