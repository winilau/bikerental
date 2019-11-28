package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class System {
	public Map<Integer,Booking> bookingId = new HashMap<>();
	public Collection<Provider> providers = new ArrayList<>(); 
	public MultidayRate calcP = new MultidayRate();  //for calculation
	public Deposit calcDeposit = new Deposit();    		 //for calculation
	
}
