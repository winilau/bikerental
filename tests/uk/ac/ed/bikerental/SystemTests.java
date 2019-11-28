package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class SystemTests {
	SystemClass test = new SystemClass();
	private Bike bike1, bike2, bike3, bike4, bike5, bike6, bike7, bike8, bike9, bike10, bike11, bike12, bike13, bike14,
			bike15;
	private DateRange dateRange1, dateRange2, dateRange3, dateRange4, dateRange5;
	private Provider Rachel, Ross, Monica, Phobee, Joey;
	private String pc1, pc2, pc3, pc4, pc5;
	private Location lo1, lo2, lo3, lo4, lo5;
	private BikeType mountain, road, bmx, commute, hybrid, cyclocross, kid, track;
	private Map<BikeType, Integer> request1 = new HashMap<>();
	private Map<BikeType, Integer> request2 = new HashMap<>();
	private Map<BikeType, Integer> request3 = new HashMap<>();
	private Map<BikeType, Integer> request4 = new HashMap<>();

	@BeforeEach
	void setUp() throws Exception {
		// Setup resources before each test
		
		DeliveryServiceFactory.setupMockDeliveryService();
		
		this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10));
		this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23));
		this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10));
		this.dateRange4 = new DateRange(LocalDate.of(2019, 1, 11), LocalDate.of(2019, 1, 14));
		this.dateRange5 = new DateRange(LocalDate.of(2019, 1, 8), LocalDate.of(2019, 1, 12));

		pc1 = "EH81PE";
		pc2 = "EH13PR";
		pc3 = "EH106QU";
		pc4 = "DA81PE";
		pc5 = "EH81PE";

		lo1 = new Location(pc1, "Slytherin");
		lo2 = new Location(pc2, "Griffyndor");
		lo3 = new Location(pc3, "Hogwarts School of Witchcraft and Wizardry");
		lo4 = new Location(pc4, "Hufflepuff");
		lo5 = new Location(pc5, "Ravenclaw");

		Rachel = new Provider("Rachel Green", lo1, "07136086345", BigDecimal(0.5));
		Ross = new Provider("Ross Geller", lo2, "07365823912", BigDecimal(0.6));
		Monica = new Provider("Monica Geller", lo3, "07394619363", BigDecimal(0.50));
		Joey = new Provider("Joey Tribbiani", lo4, "07019368294", BigDecimal(0.5));
		Phobee = new Provider("Phobee Buffay", lo5, "07527307123", BigDecimal(0.5));

		this.test.addProvider(Rachel);
		this.test.addProvider(Ross);
		this.test.addProvider(Monica);
		this.test.addProvider(Joey);
		this.test.addProvider(Phobee);

		this.mountain = new BikeType("mountain", BigDecimal(300));
		this.road = new BikeType("road", BigDecimal(200));
		this.bmx = new BikeType("bmx", BigDecimal(450));
		this.commute = new BikeType("commute", BigDecimal(150));
		this.hybrid = new BikeType("hybrid", BigDecimal(200));
		this.cyclocross = new BikeType("cyclocross", BigDecimal(350));
		this.kid = new BikeType("kid", BigDecimal(100));
		this.track = new BikeType("track", BigDecimal(375));

		this.test.calcP.setDailyRentalPrice(mountain, BigDecimal(15));
		this.test.calcP.setDailyRentalPrice(road, BigDecimal(25));
		this.test.calcP.setDailyRentalPrice(bmx, BigDecimal(18));
		this.test.calcP.setDailyRentalPrice(commute, BigDecimal(10));
		this.test.calcP.setDailyRentalPrice(hybrid, BigDecimal(15));
		this.test.calcP.setDailyRentalPrice(cyclocross, BigDecimal(20));
		this.test.calcP.setDailyRentalPrice(kid, BigDecimal(10));
		this.test.calcP.setDailyRentalPrice(track, BigDecimal(20));

		this.bike1 = new Bike(mountain, true, Rachel);
		this.bike2 = new Bike(road, true, Rachel);
		this.bike3 = new Bike(road, true, Rachel);
		this.bike4 = new Bike(bmx, true, Rachel);
		this.bike5 = new Bike(mountain, true, Joey);
		this.bike6 = new Bike(commute, true, Ross);
		this.bike7 = new Bike(kid, true, Joey);
		this.bike8 = new Bike(track, true, Phobee);
		this.bike9 = new Bike(bmx, true, Monica);
		this.bike10 = new Bike(commute, true, Ross);
		this.bike11 = new Bike(kid, true, Ross);
		this.bike12 = new Bike(track, true, Ross);
		this.bike13 = new Bike(road, true, Joey);
		this.bike14 = new Bike(bmx, true, Rachel);
		this.bike15 = new Bike(mountain, true, Joey);

		Rachel.addBike(bike1);
		Rachel.addBike(bike2);
		Rachel.addBike(bike3);
		Rachel.addBike(bike4);

		this.request1.put(road, 2);
		this.request1.put(bmx, 1);

		this.request2.put(commute, 3);
		this.request2.put(hybrid, 7);
		this.request2.put(cyclocross, 1);

		this.request3.put(kid, 1);

		this.request4.put(bmx, 2);
		this.request4.put(hybrid, 3);
		this.request4.put(mountain, 4);

	}

	private BigDecimal BigDecimal(double i) {
		BigDecimal result = new BigDecimal(i);
		return result;
	}

	@Test
	void testgetQuote1() {
		Collection<Quote> quotes1 = test.getQuotes(dateRange1, request1);
		Collection<Quote> results = new ArrayList<>();
		Collection<Bike> bikes = new ArrayList<Bike>() {
			{
				add(bike2);
				add(bike3);
				add(bike4);
			}
		};

		Quote q = new Quote(Rachel, bikes, dateRange1, BigDecimal(272).stripTrailingZeros(),
				BigDecimal(425).stripTrailingZeros());
		results.add(q);

		boolean equalCollections = (results.toString()).equals(quotes1.toString());
		assertEquals(true, equalCollections);
	}

	@Test
	void testgetQuote2() {
		Collection<Quote> quotes1 = test.getQuotes(dateRange1, request3);
		Collection<Bike> bikes = new ArrayList<Bike>() {{add(bike7);}};
		Collection<Bike> bikes1 = new ArrayList<Bike>() {{add(bike11);}};

		Quote q = new Quote(Joey, bikes, dateRange1, BigDecimal(40).stripTrailingZeros(),
				BigDecimal(50).stripTrailingZeros());
		Quote q1 = new Quote(Ross, bikes1, dateRange1, BigDecimal(40).stripTrailingZeros(),
				BigDecimal(60).stripTrailingZeros());
		Collection<String> quotesInString = new ArrayList<String>() {
			{
				add(q.toString());
				add(q1.toString());
			}
		};
		Collection<String> resultsInString = new ArrayList<>();
		for (Quote y: quotes1) {
			resultsInString.add(y.toString());
		}
		boolean equalCollections = quotesInString.containsAll(resultsInString) && resultsInString.containsAll(quotesInString);
		assertEquals(true, equalCollections);
	}
	
	@Test
	//request 3 does not get any quotes so returns an empty collection
	void testgetQuote3() {
		Collection<Quote> quotes1 = test.getQuotes(dateRange1, request2);
		Collection<Quote> empty = new ArrayList<>();
		assertEquals(quotes1.toString(), empty.toString());
	}


}
