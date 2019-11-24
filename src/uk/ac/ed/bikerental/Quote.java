package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class Quote {
	private Provider provider;
	private Collection <Bike> bikes;
	private DateRange duration;
	private BigDecimal price;
	private BigDecimal deposit;
	private int bookingNum = 0;

	public Quote(Provider provider, Collection <Bike> bikes, DateRange duration, BigDecimal price, BigDecimal deposit) {
		this.provider = provider;
		this.bikes = bikes;
		this.duration = duration;
		this.price = price;
		this.deposit = deposit;
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

	/**
	 * 
	 * @param q          chosen quote by customer
	 * @param customer   the customer booking
	 * @param Collection if false, customer doesn't pick up bike hence delivery
	 * 					 if true, customer picks up bike
	 * @return			 the booking of the chosen quote
	 */
	public Booking book(Quote q, Customer customer, boolean pickUp) {
		BigDecimal totalPrice = (q.getDeposit().add(q.getPrice())).stripTrailingZeros();
		DateRange duration = q.getDuration();
		Collection <Bike> wantedBikes = q.getBike();
		Provider p = q.getProvider();
		Booking booked = new Booking(this.bookingNum, duration, totalPrice, pickUp, customer,wantedBikes, p);
		this.bookingNum++;
		return booked;

	}
}
