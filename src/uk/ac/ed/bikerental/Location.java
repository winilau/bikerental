package uk.ac.ed.bikerental;

public class Location {
    private String postcode;
    private String address;
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    public boolean isNearTo(Location other) {
        // TODO: Implement Location.isNearTo
    	String firstTwoCurrent = this.postcode.substring(0, 1);
    	String firstTwoOther= other.getPostcode().substring(0, 1);
    	return(firstTwoCurrent == firstTwoOther);
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here
}
