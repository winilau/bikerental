package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class Quote {
	private Provider provider;
	private Bike bike;
	private DateRange duration;
	private BigDecimal price;
	private BigDecimal deposit;
	private boolean collection;
	private int bookingNum = 0;
	
	public Quote (Provider provider, Bike bike, DateRange duration, 
			BigDecimal price, BigDecimal deposit, boolean collection) {
		this.provider = provider;
		this.bike = bike;
		this.duration = duration;
		this.price = price;
		this.deposit = deposit;
		this.collection = collection;
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
	
	public boolean isItForCollection() {
		return collection;
	}
	
	public Booking bookQuote(Collection <Quote> quotes) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (Quote q : quotes) {
			totalPrice = totalPrice.add(q.getDeposit().add(q.getPrice())).stripTrailingZeros();
			DateRange duration = q.getDuration();
			boolean isItCollected = q.isItForCollection();
			Booking booked = new Booking(this.bookingNum, duration, totalPrice, isItCollected,me);
			this.bookingNum++;
			return booked;
		}
	
	}
}
