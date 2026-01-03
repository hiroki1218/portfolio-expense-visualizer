package jp.hiroki.rookie.portfolio.service.summary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Service;

import jp.hiroki.rookie.portfolio.dto.summary.SummaryDto;
import jp.hiroki.rookie.portfolio.dto.summary.viewSummaryDto;
import jp.hiroki.rookie.portfolio.repository.mufg.MufgBankRepository;

@Service
public class MonthlySummaryServiceImpl implements MonthlySummaryService {
	
	private final MufgBankRepository mufgBankRepository;
	
	public MonthlySummaryServiceImpl(
			MufgBankRepository mufgBankRepository) {
		this.mufgBankRepository = mufgBankRepository;
	}
	
	//表示金額の計算
	@Override
	public SummaryDto getSummary(YearMonth month) {
		YearMonth currentMonth = month;
		YearMonth previousMonth = currentMonth.minusMonths(1);
		
		BigDecimal currentTotal = getAmountTotal(currentMonth);
		BigDecimal previousTotal = getAmountTotal(previousMonth);
		SummaryDto summary = new SummaryDto();
		
		summary.setCurrentMonth(currentMonth);
		summary.setCurrentMonthTotal(currentTotal);
		summary.setPreviousMonthTotal(previousTotal);
		
		boolean hasCurrent = currentTotal != null && currentTotal.compareTo(BigDecimal.ZERO) != 0;
		boolean hasPrev = previousTotal != null && previousTotal.compareTo(BigDecimal.ZERO) != 0;
		
		if (hasCurrent && hasPrev) {
			BigDecimal diffAmount = currentTotal.subtract(previousTotal);
			//増減率(±)
			BigDecimal diffRate = diffAmount.divide(previousTotal, 4, RoundingMode.HALF_UP);
			//倍率
			BigDecimal ratio = currentTotal.divide(previousTotal, 2, RoundingMode.HALF_UP);
			//パーセンテージ
			BigDecimal diffPercent = diffRate.multiply(BigDecimal.valueOf(100)).setScale(1, RoundingMode.HALF_UP);
			
			summary.setDiffAmount(diffAmount);
			summary.setDiffRate(diffRate);
			summary.setDiffPercent(diffPercent);
			summary.setRatio(ratio);
		} else if (currentTotal != BigDecimal.ZERO) {
			summary.setPreviousMonthTotal(null);
			summary.setDiffAmount(null);
			summary.setDiffRate(null);
		} else if (previousTotal != BigDecimal.ZERO) {
			summary.setCurrentMonthTotal(null);
			summary.setDiffAmount(null);
			summary.setDiffRate(null);
		} else {
			summary.setCurrentMonthTotal(null);
			summary.setPreviousMonthTotal(null);
			summary.setDiffAmount(null);
			summary.setDiffRate(null);
		}
		
		return summary;
	}
	
	//銀行明細から合計支出金額を取得
	private BigDecimal getAmountTotal(YearMonth month) {
		LocalDate start = month.atDay(1);
		LocalDate end = month.atEndOfMonth();
		BigDecimal AmountTotal = mufgBankRepository.getAmountTotal(start, end);
		if (AmountTotal == null)
			return null;
		return AmountTotal;
	}
	
	//計算結果-表示用
	@Override
	public viewSummaryDto viewSummary(SummaryDto summary) {
		viewSummaryDto viewLabel = new viewSummaryDto();
		//フォーマットを指定
		DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy年MM月");
		
		String currentMonth = summary.getCurrentMonth().format(Formatter);
		String previousMonth = summary.getCurrentMonth().minusMonths(1).format(Formatter);
		
		String currentMonthTotal = summary.getCurrentMonthTotal() != null
				&& summary.getCurrentMonthTotal().compareTo(BigDecimal.ZERO) != 0
						? NumberFormat.getNumberInstance(Locale.JAPAN).format(summary.getCurrentMonthTotal()) + "円"
						: "ー&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ※ "
								+ currentMonth + "のデータがインポートされていません";
		
		String previousMonthTotal = summary.getPreviousMonthTotal() != null
				&& summary.getPreviousMonthTotal().compareTo(BigDecimal.ZERO) != 0
						? NumberFormat.getNumberInstance(Locale.JAPAN).format(summary.getPreviousMonthTotal()) + "円"
						: "ー&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;※ "
								+ previousMonth + "のデータがインポートされていません";
		
		viewLabel.setCurrentMonthLabel(currentMonth);
		viewLabel.setCurrentMonthTotalLabel(currentMonthTotal);
		viewLabel.setPreviousMonthTotalLabel(previousMonthTotal);
		viewLabel.setDiffAmountLabel(diffMakeLabel(summary.getDiffAmount()));
		viewLabel.setDiffPercentLabel(
				PercentMakeLabel(summary.getDiffRate(), summary.getRatio(), summary.getDiffPercent()));
		
		return viewLabel;
	}
	
	private String diffMakeLabel(BigDecimal diff) {
		
		if (diff == null)
			return "ー";
		
		String diffAmount = NumberFormat.getNumberInstance(Locale.JAPAN).format(diff.abs()) + "円";
		
		if (diff.signum() > 0) {
			return "前月に比べ" + diffAmount + "支出が多くなっています";
		} else if (diff.signum() < 0) {
			return "前月に比べ" + diffAmount + "支出が少なくなっています";
		} else {
			return "前月と同じ支出額です";
		}
	}
	
	private String PercentMakeLabel(BigDecimal rate, BigDecimal ratio, BigDecimal percent) {
		
		if (rate == null || ratio == null || percent == null)
			return "ー";
		
		String Ratio = ratio.toPlainString();
		String Percent = percent.toPlainString();
		
		if (rate.signum() > 0) {
			return Percent + "(前月の約" + Ratio + "倍支出 -増- 📈";
		} else if (rate.signum() < 0) {
			return Percent + "(前月の約" + Ratio + "倍支出 -減- 📉";
		} else {
			return "    〃    ";
		}
		
	}
	
}
