package model;


import org.json.JSONObject;
import persistence.Writable;

// Represents a rental with a car, price and pick up location.
public class Rental implements Writable {
    private Car car;
    private double price;
    private String pickupLocation;

    // creates a rental with given car, price and pick up location.
    public Rental(Car car, double price, String pickupLocation) {
        this.car = car;
        this.price = price;
        this.pickupLocation = pickupLocation;
    }

    // Effects: Returns all the information of the rental as a string.
    public String getRentalDetails() {
        return "Make: " + car.getMake() + "\n" + "Model: " + car.getModel() + " " + car.getYear() + "\n"
                + "Vehicle Type: " + car.getType() + "\n"
                + "Daily Rate: " + this.price + "/day" + "\n" + "Fuel Type: " + car.getFuelType() + "\n"
                + "Number of Seats: " + car.getSeats() + "\n" + "Car color: " + car.getColor() + "\n"
                + "Plate Number: " + car.getPlateNum() + "\n" + "Pickup Location: " + this.getPickupLocation()
                + "\n";
    }

    // Effects: Returns the rental overview as a string.
    public String getRentalOverview() {
        return String.format("%s %.2f per day", car.getCarOverview(), this.price);
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public double getPrice() {
        return price;
    }

    public Car getCar() {
        return car;
    }

    // Modifies: this
    // Effects: change the price to the given value.
    public void setPrice(Double newPrice) {
        EventLog.getInstance().logEvent(new Event("Updating price from " + this.price + " to " + newPrice));
        this.price = newPrice;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("price",price);
        json.put("pickupLocation", pickupLocation);
        json.put("car", car.toJson());
        return json;
    }
}
