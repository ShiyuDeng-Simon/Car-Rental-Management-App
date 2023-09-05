package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalTest {
    private Car car;
    private Rental rental;

    @BeforeEach
    public void setUp() {
        car = new Car("Honda","Civic",2020,"Sedan","White",
                5,"Gas","ABC");
        rental = new Rental(car,150,"123 ABC");
    }

    @Test
    public void testRentalConstructor(){
        assertEquals(car,rental.getCar());
        assertEquals(150,rental.getPrice());
        assertEquals("123 ABC", rental.getPickupLocation());
    }

    @Test
    public void testGetRentalDetails(){
        String expected = "Make: Honda\n" +
                "Model: Civic 2020\n" +
                "Vehicle Type: Sedan\n" +
                "Daily Rate: 150.0/day\n" +
                "Fuel Type: Gas\n" +
                "Number of Seats: 5\n" +
                "Car color: White\n" +
                "Plate Number: ABC\n" +
                "Pickup Location: 123 ABC\n";
        assertEquals(expected,rental.getRentalDetails());
    }

    @Test
    public void testSetPrice(){
        rental.setPrice(100.50);
        assertEquals(100.50,rental.getPrice());
    }

}
