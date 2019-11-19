package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class TestLocation {	
    private String pc1,pc2,pc3,pc4;
    private Location lo1,lo2,lo3,lo4;
    
    @BeforeEach
    void setUp() throws Exception {
        
        pc1 = "EH81PE";
        pc2 = "AB13PR";
        pc3 = "EH106QU";
        pc4 = "DA81PE";
        
        lo1 = new Location(pc1,"happyland");
        lo2 = new Location(pc2, "sadkingdom");
        lo3 = new Location(pc3, "place");
        lo4 = new Location(pc4, "place");

    }
    
    @Test
	void testIsNear1() {
    	assertEquals(true, lo1.isNearTo(lo3));
	}
    
    @Test
	void testIsNear2() {
    	assertEquals(false, lo2.isNearTo(lo3));
	}
    
    @Test
	void testIsNear3() {
    	assertEquals(false, lo4.isNearTo(lo3));
	}
    
    @Test
	void testIsNear4() {
    	assertEquals(false, lo1.isNearTo(lo2));
	}
    
    
}
