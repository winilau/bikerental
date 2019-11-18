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
	}
	
	private final Type typeValue;
	
	public BikeType (String type) {
		typeValue = Type.valueOf(type.toUpperCase());
	}
    public BigDecimal getReplacementValue() {
        // TODO: Implement Bike.getReplacementValue
        assert false;
        return null;
    }
}