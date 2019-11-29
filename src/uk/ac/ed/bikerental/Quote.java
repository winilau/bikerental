package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class Quote extends SystemClass {
	private Provider provider;
	private Collection <Bike> bikes = new ArrayList<>();
	private DateRange duration;
	private BigDecimal price;
	private BigDecimal deposit;
	
	/** constructor for the quote class
	 * 
	 * @param provider 
	 * @param bikes
	 * @param duration
	 * @param price
	 * @param deposit
	 */
	public Quote(Provider provider, Collection <Bike> bikes, 
			DateRange duration, BigDecimal price, BigDecimal deposit) {
		this.provider = provider;
		this.bikes = bikes;
		this.duration = duration;
		this.price = price;
		this.deposit = deposit;
	}

	//below are getter methods for this class
	@Override
	public String toString() {
		return String.format(provider + "," + bikes + "," + duration + "," + price.toPlainString() + "," + deposit.toPlainString());
	}
	public Provider getProvider() {
		return provider;
	}

	public Collection<Bike> getBikes() {
		return bikes;
	}

	public DateRange getDuration() {
		return duration;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}
	
}
