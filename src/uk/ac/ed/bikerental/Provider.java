package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Provider {
	
	private String name;
	private Location address;
	private int phoneNum;
	private BigDecimal depositRate;
	public ArrayList<Provider> partners;
	
	public Provider(String name, Location address, int phoneNum, BigDecimal depositRate) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.depositRate = depositRate;
	}
	
    public void addPartner(Provider partner) {
    	this.partners.add(partner);
    }
    
    public void deletePartner(Provider partner) {
    	if (this.partners.contains(partner)) {
    		this.partners.remove(partner);
    	}
    }
    
    public void changeDepositRate(BigDecimal depositRate) {
    	this.depositRate = depositRate;
    }
    
    public Location getAddress() {
    	return this.address;
    }
    
    public String getPostCode() {
    	return this.address.getPostcode();
    }
	
}
