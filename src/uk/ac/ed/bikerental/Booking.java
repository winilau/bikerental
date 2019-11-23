package uk.ac.ed.bikerental;

import java.math.BigDecimal;

public class Booking {
	private int bookingNum;
	private DateRange duration;
	private BigDecimal totalPrice;
	private boolean collection;
	private Customer customer;

	public Booking(int bookingNum, DateRange duration, BigDecimal totalPrice, boolean collection, Customer customer) {
		this.bookingNum = bookingNum;
		this.duration = duration;
		this.totalPrice = totalPrice;
		this.collection = collection;
		this.customer = customer;

	}
}