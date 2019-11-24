package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Customer {
	private String name;
	private Location address;
	private int phoneNum;
	private String email;

	public Customer(String name, Location address, int phoneNum, String email) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public ArrayList<Quote> getQuote(DateRange dateRange, int numOfBikes, BikeType type) {
		MultidayRate calc = new MultidayRate();
		Deposit calcDeposit = new Deposit();
		Set<Bike> allBikes = Provider.providerBikes.keySet();
		ArrayList<Quote> result = new ArrayList<>();
		Map<Provider, Collection<Bike>> temp = new HashMap<>();
		Collection<Bike> initialiser = new ArrayList<>();

		for (Bike b : allBikes) {
			temp.put(b.getProvider(), initialiser);
			if (b.getAvailability(dateRange)) {
				Collection<Bike> temporary = temp.get(b.getProvider());
				temporary.add(b);
				temp.put(b.getProvider(), temporary);
			}
		}

		Set<Provider> providers = temp.keySet();
		for (Provider p : providers) {
			if (temp.get(p).size() >= numOfBikes) {
				Collection<Bike> matches = temp.get(p);
				BigDecimal totalPrice = calc.calculatePrice(matches, dateRange).stripTrailingZeros();
				BigDecimal totalDeposit = calcDeposit.calculateAllValue(matches).stripTrailingZeros();
				Quote q = new Quote(p, matches, dateRange, totalPrice, totalDeposit);
				result.add(q);
			}
		}
		return result;
	}

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
