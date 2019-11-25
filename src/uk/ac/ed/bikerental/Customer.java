package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Customer {
	private String name; //Customer's name
	private Location address; //Customer's address
	private int phoneNum; //Customer's phone number
	private String email; //Customer's email

	/**
	 * Constructor
	 * @param name is the customer's name
	 * @param address is the customer's address of type Location (post code, address)
	 * @param phoneNum is the customer's phone Number.
	 * @param email is the customer's email
	 */
	public Customer(String name, Location address, int phoneNum, String email) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	/**
	 * This method is for the get quote use case.
	 * @param dateRange is the desired length of booking
	 * @param numOfBikes is the desired number of bikes
	 * @param type is the desired bike type
	 * @return The method returns a list of matching quotes
	 */
	public ArrayList<Quote> getQuote(DateRange dateRange, int numOfBikes, BikeType type) {
		MultidayRate calc = new MultidayRate(); //Creates a new MultidayRate object for price calculations.
		Deposit calcDeposit = new Deposit(); //Creates a new Deposit object for deposit calculations.
		Set<Bike> allBikes = Provider.providerBikes.keySet(); //Gets a set of all the bikes
		ArrayList<Quote> result = new ArrayList<>(); //Creates an ArrayList of Quotes (empty at first)
		Map<Provider, Collection<Bike>> temp = new HashMap<>(); //Creates a map matching 
		Collection<Bike> initialiser = new ArrayList<>(); //is an empty collection used for initialisation

		for (Bike b : allBikes) { //iteration through the set of all bikes
			if (!temp.containsKey(b.getProvider())) { //if the provider's not already in the map
				temp.put(b.getProvider(), initialiser); //initialise the mapped value to the provider as an empty list
			}
			
			//So like that at first we're assuming the provider doesn't have any bikes
			if (b.getAvailability(dateRange) && b.getType() == type) { //if the bike is available and of the right type
				Collection<Bike> temporary = temp.get(b.getProvider()); //We get the actual mapped collection (to the bike's provider) out as a temporary collection
				temporary.add(b); //we add the available bike to the collection
				temp.put(b.getProvider(), temporary); ///we put the collection back in the map
			}
		}

		Set<Provider> providers = temp.keySet(); //We're creating a set of all providers
		for (Provider p : providers) { //iteration through the set of all providers
			if (temp.get(p).size() >= numOfBikes) { //If the associate collection of bikes has a size greater or equal to the desired number of bikes
				Collection<Bike> matches = temp.get(p); //We get the collection of bikes
				BigDecimal totalPrice = calc.calculatePrice(matches, dateRange).stripTrailingZeros(); //We compute the price of the collection (with or without discounts)
				BigDecimal totalDeposit = calcDeposit.calculateAllValue(matches).stripTrailingZeros();//We compute the deposit
				Quote q = new Quote(p, matches, dateRange, totalPrice, totalDeposit); //We create a quote
				result.add(q); //We add it to our matching quotes
			}
		}
		return result; 
	}

	//Below are getter functions.
	public String getName() {
		return name;
	}

	public Location getAddress() {
		return address;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public String getEmail() {
		return email;
	}


}
