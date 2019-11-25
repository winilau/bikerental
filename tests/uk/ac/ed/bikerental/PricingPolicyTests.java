package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.*;


public class PricingPolicyTests {
	MultidayRate tester = new MultidayRate();
	private DateRange dateRange1, dateRange2, dateRange3;
	private Collection<Bike> bikes1, bikes2;
	private BikeType mountain, road, bmx;

	@BeforeEach
	void setUp() throws Exception {
		// Put setup here
		this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 7));
		this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 27));
		this.dateRange3 = new DateRange(LocalDate.of(2018, 1, 7), LocalDate.of(2018, 1, 10));

		this.mountain = new BikeType("mountain");
		this.road = new BikeType("road");
		this.bmx = new BikeType("bmx");

		this.bikes1 = new ArrayList<Bike>() {
			{
				add(new Bike(0, mountain, true, BigDecimal(100)));
				add(new Bike(1, road, true, BigDecimal(200)));
				add(new Bike(2, bmx, true, BigDecimal(300)));
				add(new Bike(3, mountain, true, BigDecimal(100)));
			}
		};

		this.bikes2 = new ArrayList<Bike>() {
		};

		this.tester.setDailyRentalPrice(mountain, BigDecimal(150));
		this.tester.setDailyRentalPrice(road, BigDecimal(250));
		this.tester.setDailyRentalPrice(bmx, BigDecimal(180));

	}

	private BigDecimal BigDecimal(double i) {
		BigDecimal result = new BigDecimal(i);
		return result;
	}

	@Test
	void testCalculatePrice1() {
		BigDecimal result = this.tester.calculatePrice(bikes1, dateRange3);
		assertEquals(BigDecimal(2920).stripTrailingZeros(), result.stripTrailingZeros());
	}
}