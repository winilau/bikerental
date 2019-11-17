package uk.ac.ed.bikerental;

import java.util.*;
import java.math.BigDecimal;
import java.util.Collection;



public class MultidayRate implements PricingPolicy {
  
  private Map<BikeType, BigDecimal> bikeMap = new HashMap<BikeType, BigDecimal>();
  private Map<Integer, BigDecimal> discountMap = new HashMap<Integer, BigDecimal>();
  private int MaxDays;
  private BigDecimal MaxDiscount;

	@Override
	public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
		this.bikeMap.put (bikeType, dailyPrice);
	}

	@Override
	public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
    BigDecimal result = BigDecimal.ZERO;
    int bookingLength = (int) duration.toDays();
    if (bookingLength >= this.MaxDays){
      bikes = new ArrayList<Bike>();
      for (Bike b:bikes){
      BikeType bikeType = b.getType();
      BigDecimal dailyPrice = bikeMap.get(bikeType);
      BigDecimal lengthOfBooking = BigDecimal.valueOf(bookingLength);
      BigDecimal pricePaid = BigDecimal.ONE.subtract(MaxDiscount);
      result = result.add(dailyPrice.multiply(lengthOfBooking).multiply(pricePaid));
      }
    }
    if (discountMap.get(bookingLength)!=null) {
    	bikes = new ArrayList<Bike>();
    	for (Bike b:bikes) {
    		BikeType bikeType = b.getType();
    		BigDecimal dailyPrice = bikeMap.get(bikeType);
    		BigDecimal lengthOfBooking = BigDecimal.valueOf(bookingLength);
    		BigDecimal appliedDiscount = discountMap.get(bookingLength);
    		BigDecimal pricePaid = BigDecimal.ONE.subtract(appliedDiscount);
    		result = result.add(dailyPrice.multiply(lengthOfBooking).multiply(pricePaid));
    	}
    } else {
    	bikes = new ArrayList<Bike>();
    	for (Bike b: bikes) {
    		BikeType bikeType = b.getType();
    		BigDecimal dailyPrice = bikeMap.get(bikeType);
    		BigDecimal lengthOfBooking = BigDecimal.valueOf(bookingLength);
    		result = result.add(dailyPrice.multiply(lengthOfBooking));
    	}
    }
		return result;
	}
  
  public void setDiscount(int start, int end, BigDecimal discount) {
    for (int i = start; i <= end; i++){
    	discountMap.put(i,discount);
    }
  }
  
  public void setDiscount(int start, BigDecimal discount) {
    this.MaxDays = start;
    this.MaxDiscount = discount;
  }
}