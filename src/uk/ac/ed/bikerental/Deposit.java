package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Deposit implements ValuationPolicy {
	

	@Override
	public BigDecimal calculateValue(Bike bike, LocalDate date) {
		BigDecimal result;
		result =  bike.getReplacementValue();
		return null;
	}
}
