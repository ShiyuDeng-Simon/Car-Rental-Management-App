package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a car with a make, model, year, type, color, number of seats,
// fuel type, plate number.
public class Car implements Writable {
    private final String make;
    private final String model;
    private final int year;
    private final String type;
    private final String color;
    private final int seats;
    private final String fuelType;
    private String plateNum;

    // Effect: Create a car object with the given make, model, year, type, color,
    //         number of seats, fuel type and plate number.
    public Car(String make, String model, int year, String type, String color,
               int seats, String fuelType, String plateNum) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.color = color;
        this.seats = seats;
        this.fuelType = fuelType;
        this.plateNum = plateNum;
    }

    // Effect: return the make, model, year of the car as a string.
    public String getCarOverview() {
        return String.format("%s %s %d", make, model, year);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public int getSeats() {
        return seats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getPlateNum() {
        return plateNum;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("make", make);
        json.put("model", model);
        json.put("year", year);
        json.put("type", type);
        json.put("color", color);
        json.put("seats", seats);
        json.put("fuelType", fuelType);
        json.put("plateNum", plateNum);
        return json;
    }
}
