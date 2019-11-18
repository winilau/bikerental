package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class MultidayRateTests {
	MultidayRate tester = new MultidayRate();
	private DateRange dateRange1, dateRange2, dateRange3;
	private Collection<Bike> bikes1, bikes2;
	private BikeType mountain, road, bmx;

	@BeforeEach
    void setUp() throws Exception {
      // Setup resources before each test
		
      this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
              LocalDate.of(2019, 1, 7));
      this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
              LocalDate.of(2019, 1, 27));
      this.dateRange3 = new DateRange(LocalDate.of(2018, 1, 7),
              LocalDate.of(2018, 1, 10));
      
      this.mountain = new BikeType("mountain");
      this.road = new BikeType("road");
      this.bmx = new BikeType("bmx");
      
      this.bikes1 = new ArrayList<Bike>(){
      {
        add(new Bike(0, mountain, true, true, BigDecimal(100)));
        add(new Bike(1, road, true, true, BigDecimal(200)));
        add(new Bike(2, bmx, true, true, BigDecimal(300)));
        add(new Bike(3, mountain, true, true, BigDecimal(100)));
      }};
      
      this.bikes2 = new ArrayList<Bike>(){};
      
      this.tester.setDiscount(1,2,BigDecimal.ZERO);
      this.tester.setDiscount(3,6,BigDecimal(0.05));
      this.tester.setDiscount(7,13,BigDecimal(0.10));
      this.tester.setDiscount(14, BigDecimal(0.15));
      
      this.tester.setDailyRentalPrice(mountain, BigDecimal(150));
      this.tester.setDailyRentalPrice(road,BigDecimal(250));
      this.tester.setDailyRentalPrice(bmx,BigDecimal(180));
 
    }
	


	private BigDecimal BigDecimal(double i) {
		BigDecimal result = new BigDecimal(i);
		return result;
	}



	@Test
	void testCalculatePrice1() {
		BigDecimal result1 = this.tester.calculatePrice(bikes1, dateRange1);
		double doubleResult1 = result1.doubleValue();
		
		assertEquals((double) 730, doubleResult1);
	}
	
	@Test
	void testCalculatePrice2() {
		BigDecimal result2 = this.tester.calculatePrice(bikes1, dateRange2);
		double doubleResult2 = result2.doubleValue();
		
		assertEquals((double) 14271.5, doubleResult2);
	}
	
	@Test
	void testCalculatePrice3() {
		BigDecimal result3 = this.tester.calculatePrice(bikes1, dateRange3);
		double doubleResult3 = result3.doubleValue();
		assertEquals((double) 2774 , doubleResult3);
	}
	
	@Test
	void testCalculatePrice4() {
		assertEquals(BigDecimal.ZERO, this.tester.calculatePrice(bikes2, dateRange3));
	}
	

}
