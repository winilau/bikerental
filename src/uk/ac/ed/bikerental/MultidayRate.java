package uk.ac.ed.bikerental;

import java.util.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MultidayRate implements PricingPolicy {

	public Map<BikeType, BigDecimal> bikeMap = new HashMap<BikeType, BigDecimal>();
	public Map<Integer, BigDecimal> discountMap = new HashMap<Integer, BigDecimal>();
	private int MaxDays;
	private BigDecimal MaxDiscount;
	MathContext MATH_CTX = new MathContext(3,RoundingMode.HALF_UP);

	@Override
	//Lets owner set the daily rental price per bike type.
	public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
		BigDecimal roundedDailyPrice;
		roundedDailyPrice = dailyPrice.round(MATH_CTX);
		this.bikeMap.put(bikeType, roundedDailyPrice);
	}

	@Override
	// This function calculates the price with the applied discount
	public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
		BigDecimal result = BigDecimal.ZERO;
		int bookingLength = Math.toIntExact(duration.toDays()) + 1; //converting duration to int.
		if (bookingLength >= this.MaxDays) { //checking if we're in the last tier
			for (Bike b : bikes) { //looping through the collection
				BikeType bikeType = b.getType(); //calls the Bike class getter function
				BigDecimal dailyPrice = bikeMap.get(bikeType); //finds the daily price set by the owner for this type of bikes
				BigDecimal lengthOfBooking = BigDecimal.valueOf(bookingLength); //converts the booking length to a BigDecimal (to make calculations easier)
				BigDecimal pricePaid = BigDecimal.ONE.subtract(MaxDiscount); //the price paid is : original price * (1-discount) so we're computing 1-discount
				result = result.add(dailyPrice.multiply(lengthOfBooking).multiply(pricePaid)); //price per bike is : original price * (1-discount)*booking length
			}
		}
		else if (discountMap.get(bookingLength) != null) { //checking if there is a discount set
			for (Bike b : bikes) {
				BikeType bikeType = b.getType(); //same as before
				BigDecimal dailyPrice = bikeMap.get(bikeType);
				BigDecimal lengthOfBooking = BigDecimal.valueOf(bookingLength);
				BigDecimal appliedDiscount = discountMap.get(bookingLength);
				BigDecimal pricePaid = BigDecimal.ONE.subtract(appliedDiscount);
				result = result.add(dailyPrice.multiply(lengthOfBooking).multiply(pricePaid));
			}
		} else { //No discounts are set
			for (Bike b : bikes) { 
				BikeType bikeType = b.getType();
				BigDecimal dailyPrice = bikeMap.get(bikeType);
				BigDecimal lengthOfBooking = BigDecimal.valueOf(bookingLength);
				result = result.add(dailyPrice.multiply(lengthOfBooking)); //No discount sp price per bike is: Original price* booking length
			}
		}
		return result;
	}

	// lets owner sets discount and duration
	public void setDiscount(int start, int end, BigDecimal discount) {
		BigDecimal roundedDiscount;
		if (end < start) {
			throw new IllegalArgumentException("End date cannot be eariler than start date.");
		}
		for (int i = start; i <= end; i++) {
			roundedDiscount = discount.round(MATH_CTX);
			discountMap.put(i, roundedDiscount);
		}
	}

	// the last discount tier where there is no upper bound
	public void setDiscount(int start, BigDecimal discount) {
		BigDecimal roundedDiscount;
		roundedDiscount = discount.round(MATH_CTX);
		this.MaxDays = start;
		this.MaxDiscount = roundedDiscount;
	}
}