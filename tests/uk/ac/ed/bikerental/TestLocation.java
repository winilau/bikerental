package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class TestLocation {	
    private String pc1,pc2,pc3,pc4,pc5,pc6,pc7;
    private Location lo1,lo2,lo3,lo4,lo5,lo6,lo7;
    
    @BeforeEach
    void setUp() throws Exception {
        
        pc1 = "EH81PE";
        pc2 = "EH13PR";
        pc3 = "EH106QU";
        pc4 = "DA81PE";
        pc5 = "EH81PE";
        pc6 = "SR81PE";
        pc7 = "AT4WAL";
        
        
        lo1 = new Location(pc1,"Slytherin");
        lo2 = new Location(pc2, "Griffyndor");
        lo3 = new Location(pc3, "Hogwarts School of Witchcraft and Wizardry");
        lo4 = new Location(pc4, "Hufflepuff");
        lo5 = new Location(pc5, "Ravenclaw");
        lo6 = new Location(pc6, "Forbidden Forest");
        lo7 = new Location(pc7, "Diagon Alley");
        

    }
    
    @Test
	void testIsNear1() {
    	assertEquals(true, lo1.isNearTo(lo2));
	}
    
    @Test
	void testIsNear2() {
    	assertEquals(true, lo2.isNearTo(lo1));
	}
    
    @Test
	void testIsNear3() {
    	assertEquals(true, lo3.isNearTo(lo5));
	}
    
    @Test
	void testIsNear4() {
    	assertEquals(false, lo6.isNearTo(lo4));
	}
    
    @Test
   	void testIsNear5() {
       	assertEquals(false, lo4.isNearTo(lo6));
   	}
    
	void testIsNear6() {
       	assertEquals(false, lo7.isNearTo(lo3));
   	}
	
    
}
