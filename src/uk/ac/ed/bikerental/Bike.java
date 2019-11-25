package uk.ac.ed.bikerental;

import java.math.*;
import java.util.*;

public class Bike {
	private int id; //Bike's id
	private BikeType bikeType; // Bike Type
	private boolean inStore = true; //Bike location
	private BigDecimal replacementValue; //Bike's replacement Value
	//below is the list of non-available date ranges
	public List<DateRange> dateRanges = new ArrayList<DateRange>();
	
	/**
	 * Constructor
	 * @param id This is the Bike's id
	 * @param bikeType is the Bike's type.
	 * @param inStore checks if the bike is in Store 
	 * @param replacementValue is the replacement value of the Bike
	 */
	
	
	public Bike (int id, BikeType bikeType, boolean inStore, BigDecimal replacementValue){
		this.id = id;
		this.bikeType = bikeType;
		this.inStore = inStore;
		this.replacementValue = replacementValue;
		
	}
	/**
	 * This method gets the availability of the bike (checks the list)
	 * @param dateRange The availability of the bike depends of the given date range
	 * @return The method returns a boolean:
	 * true: if the bike is available (date range not in the list).
	 * false: if the bike is not available(date range in the list).
	 */
	public boolean getAvailability(DateRange dateRange) {
		for (DateRange D: this.dateRanges) {
			if (D.overlaps(dateRange)) {
				return false;
			}
		}
		return true;
	}
	
	// Below are getter functions.
	public boolean isBikeInStore() {
		return inStore;
	}
	
    public BikeType getType() {
    	return bikeType;
    }
    
    public BigDecimal getReplacementValue() {
    	return replacementValue;
    }
    
    public int getId() {
    	return id;
    }
    // End of getter functions
    
    /**
     * This method changes the Availability of the bike (updates the list).
     * If the given date Range is in the list, it removes it, making the bike available
     * If the given date range is not in the list, it adds it to the list.
     * We don't check if it overlaps because when a customer gets a quote the bike has to be available.
     * The get availability function already checks for overlaps.
     * So we only add a date range to the list. The remove part is for convenience.
     * @param dateRange is the given date range
     */
	public void changeAvailability(DateRange dateRange) {
		if (this.dateRanges.contains(dateRange)) {
			this.dateRanges.remove(dateRange);
		}else {
			this.dateRanges.add(dateRange);
		}
	}
	
	/**
	 * This method changes the location of the bike.
	 *	It changes the truth value of the boolean inStore.
	 */
	public void changeLocation() {
		if (isBikeInStore()==true) {
			inStore = false;
		} else {
			inStore = true;
		}
	}
	
	/**
	 * This getter function gets the bike's provider from the map 
	 * providerBikes is mapping a Provider to the owned bikes.
	 * @return it returns the right provider.
	 */
	public Provider getProvider() {
		return Provider.providerBikes.get(this);
	}
}