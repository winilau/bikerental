package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Deposit implements ValuationPolicy {
	

	@Override
	public BigDecimal calculateValue(Bike bike, LocalDate date) {
		Provider provider = bike.getProvider();
		BigDecimal depositRates = provider.getDepositRate();
		BigDecimal result =  (bike.getReplacementValue().multiply(depositRates)).stripTrailingZeros();
		return result;
	}
}
