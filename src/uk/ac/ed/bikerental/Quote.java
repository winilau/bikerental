package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class Quote extends System {
	private Provider provider;
	private Collection <Bike> bikes;
	private DateRange duration;
	private BigDecimal price;
	private BigDecimal deposit;
	private int bookingNum = 0;
	
	public Collection<Quote> availableQuotes = new ArrayList<>();

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
	
	public Collection<Quote> getQuotes(DateRange dateRange, Map<BikeType, Integer> preference) {
		for (Provider p: providers) {
			Collection<Bike> matched = new ArrayList<>();
			for (Map.Entry<BikeType, Integer> entry: preference.entrySet()) {
				BikeType type = entry.getKey();
				Integer amount = entry.getValue();
				int count = 0;
				Collection<Bike> bikes = p.getBikes();
				for (Bike b:bikes) {
					if (b.getType() == type && b.getAvailability(dateRange)) {
						if (count < amount) {
							matched.add(b);
						}
						count++;
					}
				}
				if (count < amount) {
					break;
				}
			}
			BigDecimal totalPrice = calcP.calculatePrice(matched, dateRange).stripTrailingZeros();
			BigDecimal totalDeposit = calcDeposit.calculateAllValue(matched).stripTrailingZeros();
			Quote result = new Quote(p, matched, dateRange, totalPrice, totalDeposit);
			availableQuotes.add(result);
		}
		
		return availableQuotes;
	}

	/**
	 * 
	 * @param q          chosen quote by customer
	 * @param customer   the customer booking
	 * @param pickUp     if false, customer doesn't pick up bike hence delivery
	 * 					 if true, customer picks up bike
	 * @return			 the booking of the chosen quote
	 */
	public Booking bookQuote(Quote q, Customer customer, boolean pickUp) {
		BigDecimal totalPrice = (q.getDeposit().add(q.getPrice())).stripTrailingZeros();  
	   																					
		BigDecimal separateDeposit = q.getDeposit(); 
		DateRange duration = q.getDuration();    
		Collection <Bike> wantedBikes = q.getBike();   
		Provider p = q.getProvider(); 
		Booking booked = new Booking(this.bookingNum, duration, totalPrice, pickUp, customer,wantedBikes, p, separateDeposit);
		bookingId.put(bookingNum, booked); 
		this.bookingNum++;            
		for (Bike b: wantedBikes) {   
			b.changeAvailability(duration);
		}
		//have to implement the delivery service
		if (!pickUp) {
			
		}
		return booked;

	}
}
