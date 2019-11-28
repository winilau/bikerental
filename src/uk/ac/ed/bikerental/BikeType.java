package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//this class classifies the bike types 
public class BikeType {
	
	public Map<String,BigDecimal> bikeTypeValue = new HashMap<>();
	public String bikeType;
	public BigDecimal replacementValue;
	
	public BikeType(String bikeType,BigDecimal replacementValue) {
		bikeType.toLowerCase();
		this.bikeType = bikeType;
		this.replacementValue = replacementValue;
		this.bikeTypeValue.put(bikeType,replacementValue);
	}
	
	public void addBikeType(String type, BigDecimal replacementValue) {
		this.bikeTypeValue.put(type.toLowerCase(),replacementValue);
	}
	
	public Set<String> getBikeTypes(){
		return this.bikeTypeValue.keySet();
	}
	
	public BigDecimal getReplacementValue() {
		return this.replacementValue;
	}
	
}