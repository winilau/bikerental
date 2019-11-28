package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class TestMultidayRate {
	MultidayRate tester = new MultidayRate();
	MultidayRate tester2 = new MultidayRate();
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
      
      this.mountain = new BikeType("mountain", BigDecimal.ONE);
      this.road = new BikeType("road",BigDecimal.ONE);
      this.bmx = new BikeType("bmx",BigDecimal.ONE);
      
      this.bikes1 = new ArrayList<Bike>(){
      {
        add(new Bike(0, mountain, true));
        add(new Bike(1, road, true));
        add(new Bike(2, bmx, true));
        add(new Bike(3, mountain, true));
      }};
      
      this.bikes2 = new ArrayList<Bike>() {};
      
      this.tester.setDiscount(1,2,BigDecimal.ZERO);
      this.tester.setDiscount(3,6,BigDecimal(0.05));
      this.tester.setDiscount(7,13,BigDecimal(0.10));
      this.tester.setDiscount(14, BigDecimal(0.15));
      
      this.tester.setDailyRentalPrice(mountain, BigDecimal(150));
      this.tester.setDailyRentalPrice(road,BigDecimal(250));
      this.tester.setDailyRentalPrice(bmx,BigDecimal(180));
      
      this.tester2.setDailyRentalPrice(mountain, BigDecimal(150));
      this.tester2.setDailyRentalPrice(road,BigDecimal(250));
      this.tester2.setDailyRentalPrice(bmx,BigDecimal(180));
 
    }
	


	private BigDecimal BigDecimal(double i) {
		BigDecimal result = new BigDecimal(i);
		return result;
	}



	@Test
	void testCalculatePrice1() {
		BigDecimal result1 = this.tester.calculatePrice(bikes1, dateRange1);
		assertEquals(BigDecimal(730).stripTrailingZeros(), result1.stripTrailingZeros());
	}
	
	@Test
	void testCalculatePrice2() {
		BigDecimal result2 = this.tester.calculatePrice(bikes1, dateRange2);		
		assertEquals(BigDecimal(14271.5).stripTrailingZeros(), result2.stripTrailingZeros());
	}
	
	@Test
	void testCalculatePrice3() {
		BigDecimal result3 = this.tester.calculatePrice(bikes1, dateRange3);
		assertEquals(BigDecimal(2774).stripTrailingZeros(), result3.stripTrailingZeros());
	}
	
	@Test
	void testCalculatePrice4() {
		assertEquals(BigDecimal.ZERO, this.tester.calculatePrice(bikes2, dateRange3));
	}
	
}
