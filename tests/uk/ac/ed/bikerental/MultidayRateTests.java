package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class MultidayRateTests {
	MultidayRate tester = new MultidayRate();
	private DateRange dateRange1, dateRange2, dateRange3;
	private BigDecimal hundred, twoHundred, threeHundred;
	private BigDecimal discount1, discount2, discount3;
	private BigDecimal price1, price2, price3;
	private BikeType mountain, road, bmx;
	private Collection<Bike> bikes1, bikes2;

	@BeforeEach
    void setUp() throws Exception {
      // Setup resources before each test
		
      this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
              LocalDate.of(2019, 1, 7));
      this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
              LocalDate.of(2019, 1, 27));
      this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
              LocalDate.of(2018, 1, 10));
      
      this.hundred = new BigDecimal(100);
      this.twoHundred = new BigDecimal(200);
      this.threeHundred = new BigDecimal(300);
      
      this.discount1 = new BigDecimal(0.05);
      this.discount2 = new BigDecimal(0.10);
      this.discount3 = new BigDecimal(0.15);
      
      this.price1 = new BigDecimal(150);
      this.price2 = new BigDecimal(250);
      this.price3 = new BigDecimal(180);
        
      this.mountain = new BikeType("mountain");
      this.road = new BikeType("road");
      this.bmx = new BikeType("bmx");
      
      
      this.bikes1 = new ArrayList<Bike>(){
      {
        add(new Bike(0, mountain, true, true, hundred));
        add(new Bike(1, road, true, true, twoHundred));
        add(new Bike(2, bmx, true, true, threeHundred));
        add(new Bike(3, mountain, true, true, hundred));
      }};
      
      this.bikes2 = new ArrayList<Bike>(){};
      
      this.tester.setDiscount(1,2,BigDecimal.ZERO);
      this.tester.setDiscount(3,6,discount1);
      this.tester.setDiscount(7,13,discount2);
      this.tester.setDiscount(14, discount3);
      
      this.tester.setDailyRentalPrice(mountain, price1);
      this.tester.setDailyRentalPrice(road,price2);
      this.tester.setDailyRentalPrice(bmx,price3);
 
    }
	
	@Test
	void testCalculatePrice1() {
		BigDecimal result1 = new BigDecimal(0);
		assertEquals(result1, this.tester.calculatePrice(bikes1, dateRange1));
	}
	
	@Test
	void testCalculatePrice2() {
		BigDecimal result2 = new BigDecimal(13651);
		assertEquals(result2, this.tester.calculatePrice(bikes1, dateRange2));
	}
	
	@Test
	void testCalculatePric3() {
		BigDecimal result3 = new BigDecimal(2080.5);
		assertEquals(result3, this.tester.calculatePrice(bikes1, dateRange3));

	}
	
	@Test
	void testCalculatePric4() {
		assertEquals(BigDecimal.ZERO, this.tester.calculatePrice(bikes1, dateRange3));

	}
	

}
