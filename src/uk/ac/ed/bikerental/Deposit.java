package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

public class Deposit implements ValuationPolicy {
	

	@Override
	public BigDecimal calculateValue(Bike bike, LocalDate date) {
		Provider provider = bike.getProvider();
		BigDecimal depositRates = provider.getDepositRate();
		BigDecimal result =  (bike.getReplacementValue().multiply(depositRates)).stripTrailingZeros();
		return result;
	}
	
	public BigDecimal calculateValue(Bike bike) {
		Provider provider = bike.getProvider();
		BigDecimal depositRates = provider.getDepositRate();
		BigDecimal result =  (bike.getReplacementValue().multiply(depositRates)).stripTrailingZeros();
		return result;
	}
	
	public BigDecimal calculateAllValue(Collection<Bike> bikes) {
		BigDecimal totalDeposit = BigDecimal.ZERO;
		for (Bike b:bikes) {
			totalDeposit = totalDeposit.add(calculateValue(b));
		}
		return totalDeposit;
	}
}
