package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class BikeType {
	private enum Type {
		MOUNTAIN,
		CYCLOCROSS,
		TOURING,
		TANDEM,
		ELECTRIC,
		DOWNHILL,
		BMX,
		FOLDING,
		RECUMBENT,
		CRUISER,
		HYBRID,
		ROAD,
		TRIATHLON,
		COMMUTING,
		TRACK,
		KIDS,	
	};
	
	private final Type typeValue;
	
	public BikeType (String type) {
		if (type == null) {
			throw new IllegalArgumentException("Given type must not be null.");
		}
		typeValue = Type.valueOf(type.toUpperCase());
	}
	
    public String typeValue() { return typeValue.name(); }

    public BigDecimal getReplacementValue() {
    	int size = Bike.bikeDB.size();
    	for (int i = 0; i < size; i++ ) {
    		Bike bike = Bike.bikeDB.get(i);
    		String bikeType = bike.getType().typeValue();
    		if (bikeType == this.typeValue()) {
    			bike.getReplacementValue();
    		}
    	}
    	return null;
    }
}