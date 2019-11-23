package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class Quote {
	private Provider provider;
	private Bike bike;
	private DateRange duration;
	private BigDecimal price;
	private BigDecimal deposit;
	private int bookingNum = 0;
	
	public Quote (Provider provider, Bike bike, DateRange duration, 
			BigDecimal price, BigDecimal deposit) {
		this.provider = provider;
		this.bike = bike;
		this.duration = duration;
		this.price = price;
		this.deposit = deposit;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	public Bike getBike() {
		return bike;
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
	
	
	public Booking bookQuote(Collection <Quote> quotes) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (Quote q : quotes) {
			totalPrice = totalPrice.add(q.getDeposit().add(q.getPrice())).stripTrailingZeros();
			DateRange duration = q.getDuration();
			Booking booked = new Booking(this.bookingNum, duration, totalPrice, true,me);
			this.bookingNum++;
			return booked;
		}
	
	}
}
