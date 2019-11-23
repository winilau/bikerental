package uk.ac.ed.bikerental;

public class Location {
    private String postcode;
    private String address;
    
    /** 
     * This is a constructor for the Location class.
     * @param postcode is the post code of the Location as a String.
     * We're assuming the string length is greater or equal to 6.
     * @param address gives the address of the Location as a String.
     */
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    /**
     * This method checks whether the location is near enough to another location
     * to allow for collection/delivery of bikes. It compares locations by checking
     * if they are in the same postal area.
     * @param other is the other Location we're comparing to
     * @return The method returns a boolean that is true if the two location have
     * the same first two digits.
     */
    
    public boolean isNearTo(Location other) {
        // TODO: Implement Location.isNearTo
    	String firstTwoCurrent = this.postcode.substring(0, 2);
    	String firstTwoOther= other.getPostcode().substring(0, 2);
    	return(firstTwoCurrent.equals(firstTwoOther));
    }
    
    /**
     * This is a getter method.
     * @return It returns the post code parameter of a Location.
     */
    
    public String getPostcode() {
        return postcode;
    }
    
    /**
     * This is a getter method.
     * @return It returns the address parameter of a Location.
     */

    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here
}
