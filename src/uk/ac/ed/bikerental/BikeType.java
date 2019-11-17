package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class BikeType {
	List<BikeType> types = new ArrayList<BikeType>();
	public void addType(BikeType bikeType) {
		types.add(bikeType);
	}
    public BigDecimal getReplacementValue() {
        // TODO: Implement Bike.getReplacementValue
        assert false;
        return null;
    }
}