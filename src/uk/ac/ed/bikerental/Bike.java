package uk.ac.ed.bikerental;

import java.math.*;
import java.util.*;

public class Bike {
	private int id;
	private BikeType bikeType;
	private boolean inStore = true;
	private BigDecimal replacementValue;
	public List<DateRange> dateRanges = new ArrayList<DateRange>();
	
	public Bike (int id, BikeType bikeType, boolean inStore, BigDecimal replacementValue){
		this.id = id;
		this.bikeType = bikeType;
		this.inStore = inStore;
		this.replacementValue = replacementValue;
		
	}
	
	public boolean getAvailability(DateRange dateRange) {
		for (DateRange D: this.dateRanges) {
			if (D.overlaps(dateRange)) {
				return false;
			}
		}
		return true;
	}
	
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
    
    
	public void changeAvailability(DateRange dateRange) {
		if (this.dateRanges.contains(dateRange)) {
			this.dateRanges.remove(dateRange);
		}else {
			this.dateRanges.add(dateRange);
		}
	}
	
	public void changeLocation(Bike b) {
		if (b.isBikeInStore()==true) {
			b.inStore = false;
		} else {
			b.inStore = true;
		}
	}
	
	public Provider getProvider() {
		return Provider.providerBikes.get(this);
	}
}