package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

public class Deposit implements ValuationPolicy {
	

	@Override
	
	/** calculates the deposit value
	 * 
	 * @param bike the bike that needs the deposit to be calculated
	 * @param date 
	 * @return the deposit value
	 */
	public BigDecimal calculateValue(Bike bike, LocalDate date) {
		Provider provider = bike.getProvider();
		BigDecimal depositRates = provider.getDepositRate();
		BigDecimal result =  (bike.getReplacementValue().multiply(depositRates)).stripTrailingZeros();
		return result;
	}
	
	//since we don't need date because we didn't do the extension module, this is a method without the date 
	//parameter so we can easily call this method 
	public BigDecimal calculateValue(Bike bike) {
		Provider provider = bike.getProvider();
		BigDecimal depositRates = provider.getDepositRate();
		BigDecimal result =  (bike.getReplacementValue().multiply(depositRates)).stripTrailingZeros();
		return result;
	}
	
	/** calculates the total deposit value for a collection of bikes
	 * 
	 * @param bikes the collection of bikes
	 * @return the total deposit value
	 */
	public BigDecimal calculateAllValue(Collection<Bike> bikes) {
		BigDecimal totalDeposit = BigDecimal.ZERO;
		for (Bike b:bikes) {
			totalDeposit = totalDeposit.add(calculateValue(b));
		}
		return totalDeposit;
	}
}
