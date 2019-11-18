package uk.ac.ed.bikerental;

import java.util.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

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
				System.out.println(result);
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
		System.out.println(result);
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
	
	public static void main(String[] args) {
		MultidayRate tester = new MultidayRate();
		 DateRange dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
	              LocalDate.of(2019, 1, 20));
		 		 
	      BigDecimal discount1 = new BigDecimal(0.05);
	      BigDecimal discount2 = new BigDecimal(0.10);
	      BigDecimal discount3 = new BigDecimal(0.15);
	      
	      BigDecimal price1 = new BigDecimal(150);
	      BigDecimal price2 = new BigDecimal(250);
	      BigDecimal price3 = new BigDecimal(180);
	      
	      BikeType mountain = new BikeType("mountain");
	      BikeType road = new BikeType("road");
	      BikeType bmx = new BikeType("bmx");
	      
	      BigDecimal hundred = new BigDecimal(100);
	      BigDecimal twoHundred = new BigDecimal(200);
	      BigDecimal threeHundred = new BigDecimal(300);
	      
	      Collection<Bike> bikes1 = new ArrayList<Bike>(){
	          {
	            add(new Bike(0, mountain, true, true, hundred));
	            add(new Bike(1, road, true, true, twoHundred));
	            add(new Bike(2, bmx, true, true, threeHundred));
	            add(new Bike(3, mountain, true, true, hundred));
	          }};
	          
	      
	      tester.setDiscount(1,2,BigDecimal.ZERO);
	      tester.setDiscount(3,6,discount1);
	      tester.setDiscount(7,13,discount2);
	      tester.setDiscount(14, discount3);
	      
	      tester.setDailyRentalPrice(mountain, price1);
	      tester.setDailyRentalPrice(road,price2);
	      tester.setDailyRentalPrice(bmx,price3);

	      
	      tester.calculatePrice(bikes1, dateRange1);
	      
	}

}