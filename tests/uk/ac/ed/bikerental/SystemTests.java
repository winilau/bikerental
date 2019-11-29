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
	private Customer Harry, Draco, Ron, Hermione;
	private String pc1, pc2, pc3, pc4, pc5, pc6, pc7, pc8, pc9;
	private Location lo1, lo2, lo3, lo4, lo5, lo6, lo7, lo8, lo9;
	private BikeType mountain, road, bmx, commute, hybrid, cyclocross, kid, track;
	private Map<BikeType, Integer> request1 = new HashMap<>();
	private Map<BikeType, Integer> request2 = new HashMap<>();
	private Map<BikeType, Integer> request3 = new HashMap<>();
	private Map<BikeType, Integer> request4 = new HashMap<>();
	private Quote testQuote, testQuote1, testQuote2, testQuote4;

	@BeforeEach
	void setUp() throws Exception {
		// Setup resources before each test

		// Date Ranges
		this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 10));
		this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5), LocalDate.of(2019, 1, 23));
		this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7), LocalDate.of(2018, 1, 10));
		this.dateRange4 = new DateRange(LocalDate.of(2019, 1, 11), LocalDate.of(2019, 1, 14));
		this.dateRange5 = new DateRange(LocalDate.of(2019, 1, 8), LocalDate.of(2019, 1, 12));

		// Post codes
		pc1 = "EH81PE";
		pc2 = "AB13PR";
		pc3 = "RF106QU";
		pc4 = "EH81PE";
		pc5 = "EH81PE";
		pc6 = "EH74HJ";
		pc7 = "AB7053";
		pc8 = "RF6789";
		pc9 = "QRW568";

		// Locations
		lo1 = new Location(pc1, "Slytherin");
		lo2 = new Location(pc2, "Griffyndor");
		lo3 = new Location(pc3, "Hogwarts School of Witchcraft and Wizardry");
		lo4 = new Location(pc4, "Hufflepuff");
		lo5 = new Location(pc5, "Ravenclaw");
		lo6 = new Location(pc6, "Central Perks");
		lo7 = new Location(pc7, "Chandler's office");
		lo8 = new Location(pc8, "Ross's Museum");
		lo9 = new Location(pc9, "Bloomingdale's");

		// Initialising Providers
		Rachel = new Provider("Rachel Green", lo1, "07136086345", BigDecimal(0.5));
		Ross = new Provider("Ross Geller", lo2, "07365823912", BigDecimal(0.6));
		Monica = new Provider("Monica Geller", lo3, "07394619363", BigDecimal(0.50));
		Joey = new Provider("Joey Tribbiani", lo4, "07019368294", BigDecimal(0.5));
		Phobee = new Provider("Phobee Buffay", lo5, "07527307123", BigDecimal(0.5));
		
		Joey.addPartner(Phobee);
		
		// Customers
		Harry = new Customer("Harry Potter", lo6, "Hedwig", "theguywholived@gmail.com");
		Ron = new Customer("Ron Weasley", lo7, "Pigwidgeon", "bloodyhell@gmail.com");
		Hermione = new Customer("Hermione Granger", lo8, "Crookshanks", "knowitall@gmail.com");
		Draco = new Customer("Draco Malfoy", lo9, "Lucius", "myfatherwillhearaboutthis@gmail.com");

		// Adding providers to the system
		this.test.addProvider(Rachel);
		this.test.addProvider(Ross);
		this.test.addProvider(Monica);
		this.test.addProvider(Joey);
		this.test.addProvider(Phobee);

		// Bike Types and Replacement Values
		this.mountain = new BikeType("mountain", BigDecimal(300));
		this.road = new BikeType("road", BigDecimal(200));
		this.bmx = new BikeType("bmx", BigDecimal(450));
		this.commute = new BikeType("commute", BigDecimal(150));
		this.hybrid = new BikeType("hybrid", BigDecimal(200));
		this.cyclocross = new BikeType("cyclocross", BigDecimal(350));
		this.kid = new BikeType("kid", BigDecimal(100));
		this.track = new BikeType("track", BigDecimal(375));

		// Daily Rental Prices
		this.test.calcP.setDailyRentalPrice(mountain, BigDecimal(15));
		this.test.calcP.setDailyRentalPrice(road, BigDecimal(25));
		this.test.calcP.setDailyRentalPrice(bmx, BigDecimal(18));
		this.test.calcP.setDailyRentalPrice(commute, BigDecimal(10));
		this.test.calcP.setDailyRentalPrice(hybrid, BigDecimal(15));
		this.test.calcP.setDailyRentalPrice(cyclocross, BigDecimal(20));
		this.test.calcP.setDailyRentalPrice(kid, BigDecimal(10));
		this.test.calcP.setDailyRentalPrice(track, BigDecimal(20));

		// Bikes
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

		// Customer searches
		this.request1.put(road, 2);
		this.request1.put(bmx, 1);

		this.request2.put(commute, 3);
		this.request2.put(hybrid, 7);
		this.request2.put(cyclocross, 1);

		this.request3.put(kid, 1);

		this.request4.put(track, 1);

	}

	// Helper functions
	private BigDecimal BigDecimal(double i) {
		BigDecimal result = new BigDecimal(i);
		return result;
	}

	// check if 2 collection of quote are equal (order does not matter)
	public boolean quoteEqualsAll(Collection<Quote> q1, Collection<Quote> q2) {
		int count = 0;
		for (Quote q : q1) {
			for (Quote t : q2) {
				if (quotesEqual(q, t)) {
					count++;
				}
			}
		}
		if (count == q1.size() && count == q2.size()) {
			return true;
		}
		return false;
	}

	// check if 2 quotes are equal (order of bikes does not matter)
	public boolean quotesEqual(Quote q1, Quote q2) {
		boolean providerEquals = q1.getProvider().toString().equals(q2.getProvider().toString());
		boolean bikesEquals = q1.getBikes().containsAll(q2.getBikes()) && q2.getBikes().containsAll(q1.getBikes());
		boolean dateRangeEquals = q1.getDuration().toString().equals(q2.getDuration().toString());
		boolean priceEquals = q1.getPrice().equals(q2.getPrice());
		boolean depositEquals = q1.getDeposit().equals(q2.getDeposit());

		return providerEquals && bikesEquals && dateRangeEquals && priceEquals && depositEquals;
	}
	
	//check if 2 bookings are equal (order of bikes does not matter)
	public boolean bookingEqual(Booking b1, Booking b2) {
		boolean bookingNum = b1.getBookingNum() == b2.getBookingNum();
		boolean dateRange = b1.getDuration().toString().equals(b2.getDuration().toString());
		boolean totalPrice = b1.getTotalPrice().equals(b2.getTotalPrice());
		boolean pickUp = b1.isToBePickedUp() == b2.isToBePickedUp();
		boolean customer = b1.getCustomer().toString().equals(b2.getCustomer().toString());
		boolean bikes = b1.getBikes().containsAll(b2.getBikes()) && b2.getBikes().containsAll(b1.getBikes());
		boolean provider = b1.getProvider().toString().equals(b2.getProvider().toString());
		boolean deposit = b1.getDeposit().equals(b2.getDeposit());

		return bookingNum && dateRange && totalPrice && pickUp && customer && bikes && provider && deposit;
	}

	/**
	 * This test is for the Get quote use case. This is a simple case: gets bikes
	 * from one provider.
	 */
	@Test
	void testgetQuote1() {
		Collection<Quote> quotes1 = test.getQuotes(dateRange1, request1);
		Collection<Quote> results = new ArrayList<>();
		Collection<Bike> bikes = new ArrayList<>();
		bikes.add(bike2);
		bikes.add(bike3);
		bikes.add(bike4);

		Quote q = new Quote(Rachel, bikes, dateRange1, BigDecimal(272), BigDecimal(425));
		results.add(q);
		assertEquals(true, quoteEqualsAll(results, quotes1));
	}

	/**
	 * This test is for the get quote use case too. This time it checks if the
	 * system is able to get quotes from different providers.
	 */
	@Test
	void testgetQuote2() {
		Collection<Quote> quotes1 = test.getQuotes(dateRange1, request3);
		Collection<Bike> bikes = new ArrayList<>();
		bikes.add(bike7);
		Collection<Bike> bikes1 = new ArrayList<>();
		bikes1.add(bike11);

		Quote q = new Quote(Joey, bikes, dateRange1, BigDecimal(40).stripTrailingZeros(),
				BigDecimal(50).stripTrailingZeros());
		Quote q1 = new Quote(Ross, bikes1, dateRange1, BigDecimal(40).stripTrailingZeros(),
				BigDecimal(60).stripTrailingZeros());
		Collection<Quote> results = new ArrayList<Quote>();
		results.add(q);
		results.add(q1);

		assertEquals(true, quoteEqualsAll(results, quotes1));
	}

	/**
	 * This test is also for the get quote use case. It should return an empty
	 * collection as no providers can match the request.
	 */
	@Test
	void testgetQuote3() {
		Collection<Quote> quotes1 = test.getQuotes(dateRange1, request2);
		Collection<Quote> empty = new ArrayList<>();
		assertEquals(quotes1.toString(), empty.toString());
	}

	/**
	 * This test is for the Book quote use case. It checks if the customer is able
	 * to use the system to book a quote.
	 */
	@Test
	void testBookQuote1() {
		Collection<Bike> testBikes = new ArrayList<Bike>();
		testBikes.add(bike11);
		testQuote = new Quote(Ross, testBikes, dateRange1, BigDecimal(40), BigDecimal(60));

		Booking booking1 = test.bookQuote(testQuote, Draco, true);
		BigDecimal totalPrice = testQuote.getDeposit().add(testQuote.getPrice());
		Booking expected = new Booking(0, testQuote.getDuration(), totalPrice, true, Draco, testQuote.getBikes(),
				testQuote.getProvider(), testQuote.getDeposit());
		assertEquals(true, bookingEqual(expected, booking1));
	}

	/**
	 * This checks that our system is able to Deliver bikes correctly.
	 */
	@Test
	void testBookQuotes() {
		DeliveryServiceFactory.setupMockDeliveryService();
		MockDeliveryService ds = (MockDeliveryService) DeliveryServiceFactory.getDeliveryService();
		// First booking
		Collection<Bike> testBikes = new ArrayList<Bike>();
		testBikes.add(bike8);
		testQuote = new Quote(Phobee, testBikes, dateRange1, BigDecimal(80), BigDecimal(187.5));

		Booking booking1 = test.bookQuote(testQuote, Harry, false);
		BigDecimal totalPrice = testQuote.getDeposit().add(testQuote.getPrice());
		Booking expected = new Booking(0, testQuote.getDuration(), totalPrice, false, Harry, testQuote.getBikes(),
				testQuote.getProvider(), testQuote.getDeposit());
		ds.scheduleDelivery(bike8, Phobee.getAddress(), Harry.getAddress(), dateRange1.getStart());

		assertEquals(true, bookingEqual(booking1, expected));

		// Second Booking
		Collection<Bike> testBikes1 = new ArrayList<Bike>();
		testBikes.add(bike9);
		testQuote1 = new Quote(Monica, testBikes1, dateRange1, BigDecimal(72), BigDecimal(225));

		Booking booking2 = test.bookQuote(testQuote1, Hermione, false);
		BigDecimal totalPrice2 = testQuote1.getDeposit().add(testQuote1.getPrice());
		Booking expected2 = new Booking(1, testQuote1.getDuration(), totalPrice2, false, Hermione, testQuote1.getBikes(),
				testQuote1.getProvider(), testQuote1.getDeposit());
		ds.scheduleDelivery(bike9, Monica.getAddress(), Hermione.getAddress(), dateRange1.getStart());

		assertEquals(true, bookingEqual(booking2, expected2));

		// ThirdBooking
		Collection<Bike> testBikes2 = new ArrayList<Bike>();
		testBikes.add(bike10);
		testQuote2 = new Quote(Ross, testBikes2, dateRange4, BigDecimal(40), BigDecimal(90));

		Booking booking3 = test.bookQuote(testQuote2, Ron, false);
		BigDecimal totalPrice3 = testQuote2.getDeposit().add(testQuote2.getPrice());
		Booking expected3 = new Booking(2, testQuote2.getDuration(), totalPrice3, false, Ron, testQuote2.getBikes(),
				testQuote2.getProvider(), testQuote2.getDeposit());
		ds.scheduleDelivery(bike10, Ross.getAddress(), Ron.getAddress(), dateRange4.getStart());

		assertEquals(true, bookingEqual(booking3, expected3));

		// Checking Pick ups
		Collection<Deliverable> picks = ds.getPickupsOn(dateRange1.getStart());
		Collection<Deliverable> expectedPicks = new ArrayList<Deliverable>();
		expectedPicks.add(bike8);
		expectedPicks.add(bike9);

		boolean picksEquals = picks.containsAll(expectedPicks) && expectedPicks.containsAll(picks);
		assertEquals(true, picksEquals);

		// We now carry out all the pick ups
		ds.carryOutPickups(dateRange1.getStart());
		ds.carryOutPickups(dateRange4.getStart());

		// And check the drop offs
		Collection<Deliverable> expectedDropoffs = new ArrayList<Deliverable>();
		expectedDropoffs.add(bike8);
		expectedDropoffs.add(bike9);
		expectedDropoffs.add(bike10);
		Collection<Deliverable> drops = ds.getDropoffs();

		boolean deliverableEquals = drops.containsAll(expectedDropoffs) && expectedDropoffs.containsAll(drops);
		assertEquals(true, deliverableEquals);

	}

	/**
	 * This test checks if the system updates the availability correctly. We start
	 * by booking a bike for a certain date range. We then look for a similar bike
	 * for the same date range. The book bike should not be given in a quote.
	 */
	@Test
	void testGetAndBookQuote() {

		Collection<Bike> testBikes = new ArrayList<Bike>();
		testBikes.add(bike11);

		testQuote = new Quote(Ross, testBikes, dateRange1, BigDecimal(40), BigDecimal(60));
		test.bookQuote(testQuote, Draco, true);

		Collection<Quote> quotes1 = test.getQuotes(dateRange1, request3);
		Collection<Bike> bikes = new ArrayList<Bike>();
		bikes.add(bike7);

		Quote q = new Quote(Joey, bikes, dateRange1, BigDecimal(40).stripTrailingZeros(),
				BigDecimal(50).stripTrailingZeros());

		Collection<Quote> qs = new ArrayList<>();
		qs.add(q);

		assertEquals(true, quoteEqualsAll(qs, quotes1));
	}
	
	/**
	 * same as above but with overlapping date range instead of the same one to see if 
	 * the system can check the availability
	 */
	@Test
	void testGetAndBookQuote2() {

		Collection<Bike> testBikes = new ArrayList<Bike>();
		testBikes.add(bike11);

		testQuote = new Quote(Ross, testBikes, dateRange2, BigDecimal(40), BigDecimal(60));
		test.bookQuote(testQuote, Draco, true);

		Collection<Quote> quotes1 = test.getQuotes(dateRange1, request3);
		Collection<Bike> bikes = new ArrayList<Bike>();
		bikes.add(bike7);

		Quote q = new Quote(Joey, bikes, dateRange1, BigDecimal(40).stripTrailingZeros(),
				BigDecimal(50).stripTrailingZeros());

		Collection<Quote> qs = new ArrayList<>();
		qs.add(q);

		assertEquals(true, quoteEqualsAll(qs, quotes1));
	}

	@Test
	/**
	 * test returning bikes and see if location is correction after picking up and returning
	 */
	void testReturnBikes() {
		DeliveryServiceFactory.setupMockDeliveryService();
		MockDeliveryService ds1 = (MockDeliveryService) DeliveryServiceFactory.getDeliveryService();
		Collection<Bike> testBikes = new ArrayList<Bike>();
		testBikes.add(bike15);
		testQuote4 = new Quote(Joey, testBikes, dateRange4, BigDecimal(60), BigDecimal(150));
		
		test.bookQuote(testQuote4, Ron, true);
		bike15.onPickup();
		
		assertEquals(false,bike15.isBikeInStore());

		test.returnBikes(0, Phobee);
		ds1.scheduleDelivery(bike15, Phobee.getAddress(),Joey.getAddress(), dateRange4.getEnd());
		ds1.carryOutPickups(dateRange4.getEnd());
		ds1.carryOutDropoffs();
		
		assertEquals(true,bike15.isBikeInStore());

	}
}
