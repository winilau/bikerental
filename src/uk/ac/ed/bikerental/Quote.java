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
	public static Map<Integer,Booking> bookingId = new HashMap<>();

	/** constructor for the quote class
	 * 
	 * @param provider 
	 * @param bikes
	 * @param duration
	 * @param price
	 * @param deposit
	 */
	public Quote(Provider provider, Collection <Bike> bikes, DateRange duration, BigDecimal price, BigDecimal deposit) {
		this.provider = provider;
		this.bikes = bikes;
		this.duration = duration;
		this.price = price;
		this.deposit = deposit;
	}

	//below are getter methods for this class
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
	public Booking bookQuote(Quote q, Customer customer, boolean pickUp) {
		BigDecimal totalPrice = (q.getDeposit().add(q.getPrice())).stripTrailingZeros();  //calculate the total price by adding deposit and 
																							//rental price together
		DateRange duration = q.getDuration();    //gets the date range from the queue wanted
		Collection <Bike> wantedBikes = q.getBike();   //gets the collection of bikes from the queue wanted
		Provider p = q.getProvider();  //gets the provider from the queue wanted
		Booking booked = new Booking(this.bookingNum, duration, totalPrice, pickUp, customer,wantedBikes, p); //put all the info into a booking object
		bookingId.put(bookingNum, booked); //put the booking number and the booking into a static map so the booking can be referenced by the booking Id
		this.bookingNum++;            //creates the unique booking number for each booking
		for (Bike b: wantedBikes) {   //changes availability for the collection of bikes booked for the give date range
			b.changeAvailability(duration);
		}
		return booked;

	}
}
