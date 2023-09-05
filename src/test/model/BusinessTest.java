package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessTest {
    private Rental rental;
    private Rental rental2;
    private Business business;

    @BeforeEach
    public void setUp() {
        Car car = new Car("Honda", "Civic", 2020, "Sedan", "White",
                5, "Gas", "ABC");
        Car car2 = new Car("Toyota","RAV4",2012,"SUV","Black",5,"Gas",
                "JG745S");
        rental = new Rental(car, 220.5,"123 ABC rd, VAN");
        rental2 = new Rental(car2, 300, "456 DEF rd, VAN");
        business = new Business();
    }

    @Test
    public void testBusinessConstructor() {
        assertNotNull(business.getListings());
        assertEquals(0, business.getListings().size());
    }

    @Test
    public void testAddRental(){
        business.addRental(rental);
        business.addRental(rental2);
        assertEquals(2, business.getListings().size());
        assertEquals(rental, business.getListings().get(0));
        assertEquals(rental2, business.getListings().get(1));
    }

    @Test
    public void testRemoveRentalWithInvalidPos(){
        business.addRental(rental);
        business.addRental(rental2);
        assertFalse(business.removeRental(0));
        assertFalse(business.removeRental(3));
    }

    @Test
    public void testRemoveRentalWithValidPos(){
        business.addRental(rental);
        business.addRental(rental2);
        assertTrue(business.removeRental(1));
        assertEquals(rental2, business.getListings().get(0));
    }

    @Test
    public void testGetRentalOverviewsWithListings(){
        business.addRental(rental);
        business.addRental(rental2);
        String expected = "1. Honda Civic 2020 220.50 per day\n" +
                "2. Toyota RAV4 2012 300.00 per day\n";
        assertEquals(expected, business.getRentalOverviews());
    }

    @Test
    public void testGetRentalOverviewsWithNoListings() {
        String expected = "Sorry, there is no existing listing.";
        assertEquals(expected, business.getRentalOverviews());
    }

    @Test
    public void testGetRentalDetailsUsingInvalidPos(){
        business.addRental(rental);
        assertEquals("Listing not found", business.getRentalDetails(0));
        assertEquals("Listing not found", business.getRentalDetails(2));
    }

    @Test
    public void testGetRentalDetailsUsingValidPos(){
        business.addRental(rental);
        String expected = "Make: Honda\n" +
                "Model: Civic 2020\n" +
                "Vehicle Type: Sedan\n" +
                "Daily Rate: 220.5/day\n" +
                "Fuel Type: Gas\n" +
                "Number of Seats: 5\n" +
                "Car color: White\n" +
                "Plate Number: ABC\n" +
                "Pickup Location: 123 ABC rd, VAN\n";
        assertEquals(expected, business.getRentalDetails(1));
    }

    @Test
    public void testGetRental(){
        business.addRental(rental);
        business.addRental(rental2);
        assertEquals(rental2, business.getRental(2));
    }

    @Test
    public void testGetListingsWithFilter() {
        business.addRental(rental);
        business.addRental(rental2);
        ArrayList<Rental> result = business.getListingsWithFilter("Sedan");
        assertEquals(1, result.size());
        assertEquals(rental, result.get(0));
    }
}
