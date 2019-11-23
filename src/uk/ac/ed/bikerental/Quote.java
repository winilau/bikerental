package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class Quote {
	private Provider provider;
	private Collection<Bike> bikes;
	private DateRange duration;
	private BigDecimal price;
	private BigDecimal deposit;
	private boolean collection;
	public Quote (Provider provider, Collection<Bike> bikes, DateRange duration, 
			BigDecimal price, BigDecimal deposit, boolean collection) {
		this.provider = provider;
		this.bikes = bikes;
		this.duration = duration;
		this.price = price;
		this.deposit = deposit;
		this.collection = collection;
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
	
	public boolean isItForCollection() {
		return collection;
	}
	
	public Booking bookQuote(Quote q) {
		BigDecimal totalPrice = q.getDeposit().add(q.getPrice());
		DateRange duration = q.getDuration();
		boolean isItCollected = q.isItForCollection();
		Booking booked = new Booking(count, duration, totalPrice, isItCollected,me);
		return booked;
	}
}
