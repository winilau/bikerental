package uk.ac.ed.bikerental;

import java.math.*;
import java.util.*;

public class Bike {
	private int id;
	private BikeType bikeType;
	private boolean available = true;
	private boolean inStore = true;
	private BigDecimal replacementValue;
	public static List<Bike> bikeDB = new ArrayList<Bike>();
	
	public Bike (int id, BikeType bikeType, boolean available, boolean inStore, BigDecimal replacementValue){
		this.id = id;
		this.bikeType = bikeType;
		this.available = available;
		this.inStore = inStore;
		this.replacementValue = replacementValue;
		
	}
	
	public boolean getAvailability() {
		return available;
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
    
    public void info() {
    	System.out.println("Bike information:\nId: " + id + "\nType: " + bikeType + "\n Available?: " +
    available + "\nBike in store?: " + inStore + "\nReplacement value: " + replacementValue);
    }
    
	public void addBike(Bike b) {
		if (bikeDB.contains(b)) {
			System.out.println("Bike is already in the Database");
		} else {
			bikeDB.add(b);
		}

	}
	
	public void deleteBike(Bike b) {
		if (bikeDB.contains(b)) {
			bikeDB.remove(b);
		} else {
			System.out.println("Bike not found!");
		}
	}
	
	public void changeAvailability(Bike b) {
		if (b.getAvailability()==true) {
			b.available = false;
		} else {
			b.available = true;
		}
	}
	
	public void changeLocation(Bike b) {
		if (b.isBikeInStore()==true) {
			b.inStore = false;
		} else {
			b.inStore = true;
		}
	}
}