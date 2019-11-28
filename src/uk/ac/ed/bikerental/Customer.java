package uk.ac.ed.bikerental;

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
