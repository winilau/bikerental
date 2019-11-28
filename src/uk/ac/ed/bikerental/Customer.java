package uk.ac.ed.bikerental;

public class Customer {
	private String name; 
	private Location address; 
	private String phoneNum; 
	private String email;

	/**
	 * Constructor
	 * @param name is the customer's name
	 * @param address is the customer's address of type Location (post code, address)
	 * @param phoneNum is the customer's phone Number.
	 * @param email is the customer's email
	 */
	public Customer(String name, Location address, String phoneNum, String email) {
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public String getEmail() {
		return email;
	}


}
