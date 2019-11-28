package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Provider extends SystemClass{
	
	private String name = "Chandler Bing"; 
	private Location address = new Location("000000", "central perks"); 
	private String phoneNum = "12345678"; 
	private BigDecimal depositRate = BigDecimal.ZERO; 
	public Collection<Provider> partners = new ArrayList<>(); 
	public Collection<Bike> empty = new ArrayList<>();
	
	/**
	 * Constructor
	 * @param name Provider's name
	 * @param address Provider's address of type Location (post code, address)
	 * @param phoneNum Provider's phone number
	 * @param depositRate Provider's own deposit rate.
	 */
	public Provider(String name, Location address, String phoneNum, BigDecimal depositRate) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.depositRate = depositRate;
		providerBikes.put(this, empty);
		super.providers.add(this);
	}
	
    public String getName() {
		return name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public BigDecimal getDepositRate() {
		return depositRate;
	}

	public Collection<Provider> getPartners() {
		return partners;
	}

	public Collection<Bike> getBikes() {
		return providerBikes.get(this);
	}
	
    public Location getAddress() {
    	return this.address;
    }
    
    public String getPostCode() {
    	return this.address.getPostcode();
    }
 
    
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
     * @param bikes to be added
     */
    public void addBikes(Collection<Bike> bikes) {
    	Collection<Bike> newBikes = providerBikes.get(this);
    	for (Bike b: bikes) {
    		newBikes.add(b);
    	}
    	providerBikes.put(this,newBikes);
    }
    
    /**
     * This method allows the provider to add a single bike to the providerBikes map
     * @param bike to be added 
     */
    
    public void addBike(Bike bike) {
    	Collection<Bike> newBikes = providerBikes.get(this);
    	newBikes.add(bike);
    	providerBikes.put(this,newBikes);
    }
    
    /**
     * This method allows the provider to delete bikes from the providerBikes map
     * @param bikes to removed
     */
    public void deleteBikes(Collection<Bike> bikes) {
    	Collection<Bike> newBikes = providerBikes.get(this);
    	for (Bike b: bikes) {
    		if (newBikes.contains(b)) {
    			newBikes.remove(b);
        	}
    	}
    	providerBikes.put(this,newBikes);
    }
    
    /**
     * This method allows the provider to remove a bike from the providerBikes map
     * @param bike to be removed
     */
    public void deleteBike(Bike bike) {
    	Collection<Bike> original = providerBikes.get(this);
    	if (original.contains(bike)) {
    		original.remove(bike);
    		providerBikes.put(this, original);
    	}
    }
    
    
    /** return bikes to provider and changes location status of the bikes
     * 
     * @param bookingId is the booking id of the returned booking
     */
    public void returnBikes(int bookingNum) {
    	Booking booking = bookingId.get(bookingNum);
    	
    	if (booking.getProvider() == this) {
    		Collection<Bike> bikes = booking.getBikes();
        	for (Bike b: bikes) {
        		b.changeLocation();
        	}
    	}else {
    		//implement the delivery of bike to original provider
    		Collection<Bike> bikes = booking.getBikes();
        	for (Bike b: bikes) {
        		b.changeLocation();
        	}
    	}
    	
    }
    
    @Override
    public String toString() {
    	return String.format(name);
    }
}
