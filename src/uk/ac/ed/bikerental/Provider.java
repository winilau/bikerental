package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Provider {
	
	private String name; //Provider's name
	private Location address; // Provider's address
	private int phoneNum; //Provider's phone Number
	private BigDecimal depositRate; //Provider's deposit rate
	public ArrayList<Provider> partners; //Provider's list of partners
	public static Map<Bike,Provider> providerBikes; //Map mapping a Bike to its Provider
	
	/**
	 * Constructor
	 * @param name Provider's name
	 * @param address Provider's address of type Location (post code, address)
	 * @param phoneNum Provider's phone number
	 * @param depositRate Provider's own deposit rate.
	 */
	public Provider(String name, Location address, int phoneNum, BigDecimal depositRate) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.depositRate = depositRate;
	}
	
	//Bellow are getter functions.
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

	public Set<Bike> getBikes() {
		return providerBikes.keySet();
	}
	
    public Location getAddress() {
    	return this.address;
    }
    
    public String getPostCode() {
    	return this.address.getPostcode();
    }
    //End of getter functions
    
    /**
     * This methods adds partners to the list of partners
     * @param partners are the partners to be added
     */
    public void addPartner(ArrayList<Provider> partners) {
    	for (Provider p: partners) {
    		this.partners.add(p);
    	}
    }
    
    /**
     * This method removes a partner from the list of partners
     * @param partner is the partner to be removes
     */
	public void deletePartner(Provider partner) {
    	if (this.partners.contains(partner)) {
    		this.partners.remove(partner);
    	}
    }
    
	/**
	 * This method allows a provider to change the deposit rate.
	 * @param depositRate is the new deposit rate.
	 */
    public void changeDepositRate(BigDecimal depositRate) {
    	this.depositRate = depositRate;
    }
    
    /**
     * This method allows the provider to add bikes to the providerBikes map
     * @param bikes are the bikes to be added
     */
    public void addBike(ArrayList<Bike> bikes) {
    	for (Bike b: bikes) {
    		Provider.providerBikes.put(b,this);
    	}
    }
    
    /**
     * This method allows the provider to remove a bike from the providerBikes map
     * @param bike is the bike to be deleted
     */
    public void deleteBike(Bike bike) {
    	if (Provider.providerBikes.containsKey(bike)) {
    		Provider.providerBikes.remove(bike);
    	}
    }
	
}
