package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Booking {
	private int bookingNum; //Booking Number
	private DateRange duration; // Booking length
	private BigDecimal totalPrice; //Daily rental price + deposit
	private boolean pickUp; //Method of collection
	private Customer customer; //Person who ordered
	private Collection<Bike> bikes = new ArrayList<>(); //Bikes ordered
	private Provider provider; //Bikes' owner
	private BigDecimal deposit; //total deposit

	/**
	 * Constructor
	 * @param bookingNum is the booking Number
	 * @param duration is the length of the booking
	 * @param totalPrice is the price including deposit
	 * @param pickUp is a boolean that checks whether the customer is picking up the bike
	 * true: Store collection
	 * false: Delivery
	 * @param customer is The customer that did the booking
	 * @param bikes is the Collection of bikes ordered
	 * @param provider is the owner of the selected bikes
	 * @param deposit is the deposit amount for the booking
	 */
	public Booking(int bookingNum, DateRange duration, BigDecimal totalPrice,
			boolean pickUp, Customer customer, Collection<Bike> bikes, Provider provider, BigDecimal deposit) {
		this.bookingNum = bookingNum;
		this.duration = duration;
		this.totalPrice = totalPrice;
		this.pickUp = pickUp;
		this.customer = customer;
		this.bikes = bikes;
		this.provider = provider;
		this.deposit = deposit;
		
	}
	
	@Override
	public String toString() {
		return String.format(bookingNum+ "," + duration + "," + totalPrice.toPlainString() + "," + pickUp + "," + customer + "," +bikes +
				","+ provider + ","+ deposit.toPlainString() );
	}

	//Below are getter functions.
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
	
	public BigDecimal getDeposit() {
		return deposit;
	}
}