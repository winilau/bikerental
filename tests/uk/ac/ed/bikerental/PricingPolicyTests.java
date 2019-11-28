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
	private Provider provider = new Provider("name", null, "", null);

	@BeforeEach
	void setUp() throws Exception {
		// Put setup here
		this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 7));
		this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 27));
		this.dateRange3 = new DateRange(LocalDate.of(2018, 1, 7), LocalDate.of(2018, 1, 10));

		this.mountain = new BikeType("mountain",BigDecimal.ONE);
		this.road = new BikeType("road",BigDecimal.TEN);
		this.bmx = new BikeType("bmx", BigDecimal.ZERO);

		this.bikes1 = new ArrayList<Bike>() {
			{
				add(new Bike(mountain, true, provider));
				add(new Bike(road, true, provider));
				add(new Bike(bmx, true, provider));
				add(new Bike(mountain, true, provider));
			}
		};

		this.bikes2 = new ArrayList<Bike>() {};

		this.tester.setDailyRentalPrice(mountain, BigDecimal(150));
		this.tester.setDailyRentalPrice(road, BigDecimal(250));
		this.tester.setDailyRentalPrice(bmx, BigDecimal(180));

	}

	private BigDecimal BigDecimal(double i) {
		BigDecimal result = new BigDecimal(i);
		return result;
	}

	@Test
	//testing without discount
	void testCalculatePrice1() {
		BigDecimal result = this.tester.calculatePrice(bikes1, dateRange3).stripTrailingZeros();
		assertEquals(BigDecimal(2920).stripTrailingZeros(), result);
	}
	@Test
	//testing without discount
	void testCalculatePrice2() {
		BigDecimal result = this.tester.calculatePrice(bikes1, dateRange1).stripTrailingZeros();
		assertEquals(BigDecimal(730).stripTrailingZeros(), result);
	}
	@Test
	//testing without discount
	void testCalculatePrice3() {
		BigDecimal result = this.tester.calculatePrice(bikes1, dateRange2).stripTrailingZeros();
		assertEquals(BigDecimal(16790).stripTrailingZeros(), result);
	}
	@Test
	//no bikes so should return zero
	void testCalculatePrice4() {
		BigDecimal result = this.tester.calculatePrice(bikes2, dateRange2).stripTrailingZeros();
		assertEquals(BigDecimal(0).stripTrailingZeros(), result);
	}
}