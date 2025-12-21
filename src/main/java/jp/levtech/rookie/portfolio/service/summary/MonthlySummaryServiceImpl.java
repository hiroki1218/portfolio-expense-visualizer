package jp.levtech.rookie.portfolio.service.summary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;

import org.springframework.stereotype.Service;

import jp.levtech.rookie.portfolio.dto.MonthlySummaryDto;
import jp.levtech.rookie.portfolio.repository.mufg.MufgBankRepository;

@Service
public class MonthlySummaryServiceImpl implements MonthlySummaryService {
	
	private final MufgBankRepository mufgBankRepository;
	
	public MonthlySummaryServiceImpl(
			MufgBankRepository mufgBankRepository) {
		this.mufgBankRepository = mufgBankRepository;
	}
	
	@Override
	public MonthlySummaryDto getSummary(YearMonth targetMonth) {
		
		YearMonth thisMonth = targetMonth;
		YearMonth prevMonth = targetMonth.minusMonths(1);
		
		BigDecimal thisTotal = sumMonth(thisMonth);
		BigDecimal prevTotal = sumMonth(prevMonth);
		MonthlySummaryDto dto = new MonthlySummaryDto();
		
		dto.setMonth(thisMonth);
		dto.setThisMonthTotal(thisTotal);
		dto.setPrevMonthTotal(prevTotal);
		
		// 差分計算
		if (thisTotal != null && prevTotal != null) {
			BigDecimal diff = thisTotal.subtract(prevTotal);
			dto.setDiffAmount(diff);
			
			if (prevTotal.signum() != 0) {
				BigDecimal diffRate = diff.divide(prevTotal, 4, RoundingMode.HALF_UP);
				dto.setDiffRate(diffRate);
			} else {
				dto.setDiffRate(null); // 前月が0円なら率は出さない
			}
		} else {
			dto.setDiffAmount(null);
			dto.setDiffRate(null);
		}
		
		return dto;
	}
	
	private BigDecimal sumMonth(YearMonth ym) {
		LocalDate start = ym.atDay(1);
		LocalDate end = ym.atEndOfMonth();
		
		BigDecimal bank = mufgBankRepository.sumSpending(start, end);
		
		// 銀行もクレカも1件もない → この月は「データなし」
		if (bank == null) {
			return null;
		}
		
		return bank;
	}
}
