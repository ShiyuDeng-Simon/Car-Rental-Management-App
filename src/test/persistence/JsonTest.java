package persistence;

import model.Car;
import model.Rental;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRental(Rental rental, Double price, String pickupLocation, String make, String model, int year,
                               String type, String color, int seats, String fuelType, String plateNum) {
        assertEquals(price, rental.getPrice());
        assertEquals(pickupLocation, rental.getPickupLocation());
        Car car = rental.getCar();
        assertEquals(make, car.getMake());
        assertEquals(model, car.getModel());
        assertEquals(year, car.getYear());
        assertEquals(type, car.getType());
        assertEquals(color, car.getColor());
        assertEquals(seats, car.getSeats());
        assertEquals(fuelType, car.getFuelType());
        assertEquals(plateNum, car.getPlateNum());
    }
}