package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Provider {
	
	private String name;
	private Location address;
	private int phoneNum;
	private BigDecimal depositRate;
	public ArrayList<Provider> partners;
	public ArrayList<Bike> bikes;
	
	public Provider(String name, Location address, int phoneNum, BigDecimal depositRate) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.depositRate = depositRate;
	}
	
    public String getName() {
		return name;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public BigDecimal getDepositRate() {
		return depositRate;
	}

	public ArrayList<Provider> getPartners() {
		return partners;
	}

	public ArrayList<Bike> getBikes() {
		return bikes;
	}
	
    public Location getAddress() {
    	return this.address;
    }
    
    public String getPostCode() {
    	return this.address.getPostcode();
    }
    
    public void addPartner(ArrayList<Provider> partners) {
    	for (Provider p: partners) {
    		this.partners.add(p);
    	}
    }

	public void deletePartner(Provider partner) {
    	if (this.partners.contains(partner)) {
    		this.partners.remove(partner);
    	}
    }
    
    public void changeDepositRate(BigDecimal depositRate) {
    	this.depositRate = depositRate;
    }
    
    public void addBike(ArrayList<Bike> bikes) {
    	for (Bike b: bikes) {
    		this.bikes.add(b);
    	}
    }
    
    public void deleteBike(Bike bike) {
    	if (this.bikes.contains(bike)) {
    		this.bikes.remove(bike);
    	}
    }
	
}
