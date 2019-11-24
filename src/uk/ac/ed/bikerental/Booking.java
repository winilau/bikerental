package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Booking {
	private int bookingNum;
	private DateRange duration;
	private BigDecimal totalPrice;
	private boolean pickUp;
	private Customer customer;
	private Collection<Bike> bikes = new ArrayList<>();
	private Provider provider;

	public Booking(int bookingNum, DateRange duration, BigDecimal totalPrice,
			boolean pickUp, Customer customer, Collection<Bike> bikes, Provider provider) {
		this.bookingNum = bookingNum;
		this.duration = duration;
		this.totalPrice = totalPrice;
		this.pickUp = pickUp;
		this.customer = customer;
		this.bikes = bikes;
		this.provider = provider;
		
	}

	public int getBookingNum() {
		return bookingNum;
	}

	public DateRange getDuration() {
		return duration;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public boolean isToBePickedUp() {
		return pickUp;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public Collection<Bike> getBikes(){
		return bikes;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
}