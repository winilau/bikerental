package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Set;

//this class classifies the bike types 
public class BikeType {
	
	//used the object enumerated type to define a limited amount of types available 
	private enum Type {
		MOUNTAIN, CYCLOCROSS, TOURING, TANDEM, ELECTRIC, DOWNHILL, BMX, FOLDING, RECUMBENT, CRUISER, HYBRID, ROAD,
		TRIATHLON, COMMUTING, TRACK, KIDS,
	};
	
	private final Type typeValue;
	//converts user input(string) to Type (the enum above)
	public BikeType(String type) {
		if (type == null) {
			throw new IllegalArgumentException("Given type must not be null.");
		}
		typeValue = Type.valueOf(type.toUpperCase());
	}

	//return the enum in String
	public String typeValue() {
		return typeValue.name();
	}
	
	//getter for replacement value
	public BigDecimal getReplacementValue() {
		Set<Bike> bikeDB = Provider.providerBikes.keySet();
		for (Bike bike : bikeDB) {
			String bikeType = bike.getType().typeValue();
			if (bikeType == this.typeValue()) {
				bike.getReplacementValue();
			}
		}
		return null;
	}
}