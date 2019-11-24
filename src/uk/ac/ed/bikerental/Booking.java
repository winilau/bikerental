package uk.ac.ed.bikerental;

import java.math.BigDecimal;

public class Booking {
	private int bookingNum;
	private DateRange duration;
	private BigDecimal totalPrice;
	private boolean pickUp;
	private Customer customer;

	public Booking(int bookingNum, DateRange duration, BigDecimal totalPrice, boolean pickUp, Customer customer) {
		this.bookingNum = bookingNum;
		this.duration = duration;
		this.totalPrice = totalPrice;
		this.pickUp = pickUp;
		this.customer = customer;

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
	
	
}