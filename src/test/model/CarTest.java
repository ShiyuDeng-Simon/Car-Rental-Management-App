package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// Unit tests for Car class
class CarTest {
    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car("Honda","Civic",2020,"Sedan","White",
                5,"Gas","ABC");
    }

    @Test
    public void testCarConstructor(){
        assertEquals("Honda", car.getMake());
        assertEquals("Civic", car.getModel());
        assertEquals(2020, car.getYear());
        assertEquals("Sedan", car.getType());
        assertEquals("White", car.getColor());
        assertEquals(5, car.getSeats());
        assertEquals("Gas", car.getFuelType());
        assertEquals("ABC", car.getPlateNum());
    }

    @Test
    public void testGetCarOverview(){
        String expected = "Honda Civic 2020";
        assertEquals(expected, car.getCarOverview());
    }
}