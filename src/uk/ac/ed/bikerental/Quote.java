package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class Quote extends SystemClass {
	private Provider provider;
	private Collection <Bike> bikes;
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
		return String.format(provider + "," + bikes + "," + duration + "," + price + "," + deposit);
	}
	public Provider getProvider() {
		return provider;
	}

	public Collection<Bike> getBike() {
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
