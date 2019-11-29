package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SystemClass {
	public Map<Integer,Booking> bookingId = new HashMap<>();
	private int bookingNum = 0;
	public Collection<Quote> availableQuotes = new ArrayList<>();
	public Map<Provider, Collection<Bike>> providerBikes = new HashMap<>();
	public Collection<Provider> providers = new ArrayList<>(); 
	public MultidayRate calcP = new MultidayRate();  //for calculation
	public Deposit calcDeposit = new Deposit();    	 //for calculation
	
	
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
					matched.clear();
					break;
				}
				
			}
			if (!matched.isEmpty() ) {
				BigDecimal totalPrice = calcP.calculatePrice(matched, dateRange).stripTrailingZeros();
				BigDecimal totalDeposit = calcDeposit.calculateAllValue(matched).stripTrailingZeros();
				Quote result = new Quote(p, matched, dateRange, totalPrice, totalDeposit);
				//System.out.println(result.getProvider().getName());
				//printBikes(result.getBike());
				//System.out.println(totalPrice.toPlainString());
				//System.out.println(totalDeposit.toPlainString());
				//System.out.println();
				availableQuotes.add(result);
			}			
		}
		//for (Quote q: availableQuotes) {
		//	System.out.println(q.toString());
		//}
		return availableQuotes;
		
	}
	
	public void printProviders() {
		for (Provider p:providers) {
			System.out.println(p.getName());
		}
	}
	
	public void printBikes(Collection<Bike> bikes) {
		for (Bike b:bikes) {
			System.out.println(b.getType().getString() + " ");
		}
	}
	
	public void addProvider(Provider provider) {
		providers.add(provider);
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
		//System.out.println("+++++" + q.toString());
		BigDecimal totalPrice = (q.getDeposit().add(q.getPrice())).stripTrailingZeros();  																				
		BigDecimal separateDeposit = q.getDeposit(); 
		DateRange duration = q.getDuration();    
		Collection <Bike> wantedBikes = q.getBike();   
		Provider p = q.getProvider(); 
		Booking booked = new Booking(this.bookingNum, duration, totalPrice, pickUp, customer,wantedBikes, p, separateDeposit);
		bookingId.put(bookingNum, booked); 
		this.bookingNum++;
		//System.out.println(wantedBikes.toString());
		for (Bike b: wantedBikes) {   
			b.changeAvailability(duration);
		}
		//have to implement the delivery service
		if (!pickUp) {
		DeliveryServiceFactory.getDeliveryService();
		}
		return booked;

	}
	
    /** return bikes to provider and changes location status of the bikes
     * 
     * @param bookingId is the booking id of the returned booking
     */
    public void returnBikes(int bookingNum, Provider p) {
    	Booking booking = bookingId.get(bookingNum);
    	System.out.println(booking.toString());
    	if (booking.getProvider() == p) {
    		Collection<Bike> bikes = booking.getBikes();
        	for (Bike b: bikes) {
        		b.changeLocation();
        	}
    	}else {
    		Collection<Bike> bikes = booking.getBikes();
    		DeliveryServiceFactory.getDeliveryService();
        	for (Bike b: bikes) {
        		b.changeLocation();
        	}
    	}
    	
    }
	
}
