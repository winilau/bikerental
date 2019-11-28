package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateRange {
    private DateRange dateRange1, dateRange2, dateRange3,dateRange4, dateRange5;

    @BeforeEach
    void setUp() throws Exception {
        // Setup resources before each test
        this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
                LocalDate.of(2019, 1, 10));
        this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
        this.dateRange4 = new DateRange(LocalDate.of(2019, 1, 11), 
        		LocalDate.of(2019, 1, 14));
        this.dateRange5 = new DateRange(LocalDate.of(2019, 1, 8),
        		LocalDate.of(2019, 1, 12));
    }

    // Sample JUnit tests checking toYears works
    @Test
    void testToYears1() {
        assertEquals(0, this.dateRange1.toYears());
    }

    @Test
    void testToYears3() {
        assertEquals(3, this.dateRange3.toYears());
    }
    //JUnit tests checking overlaps work:
    
   //Case 1 with overlap
    // 	      A
    // |--------------|
    //		  B
    //    |-------|
    @Test
    void testOverlapsTrue1() {
        assertEquals(true, this.dateRange1.overlaps(dateRange2));
    }
    
  //Case 2 with overlap
    // 	      A
    // |--------------|
    //		  B
    // |--------------|
    @Test
    void testOverlapsTrue2() {
        assertEquals(true, this.dateRange1.overlaps(dateRange1));
    }
    
  //Case 3 with overlap
    // 	      A
    // |--------------|
    //		 		 	  B
    //    		   |-------------|
    @Test
    void testOverlapsTrue3() {
        assertEquals(true, this.dateRange1.overlaps(dateRange5));
    }
    
   //Case 4 with overlap
    // 	      B
    // |--------------|
    //		 		  	  A
    //    		   |-------------|
    @Test
    void testOverlapsTrue4() {
        assertEquals(true, this.dateRange5.overlaps(dateRange1));
    }

   //Case 1 for no overlap
    // 	      A				   B
    // |--------------| |-------------|
    @Test
    void testOverlapsFalse1() {
        assertEquals(false, this.dateRange1.overlaps(dateRange4));
    }
    
   //Case 2 for no overlap
    // 	      B				   A
    // |--------------| |-------------|
    
    @Test
    void testOverlapsFalse2() {
        assertEquals(false, this.dateRange4.overlaps(dateRange1));
    }
    

}
