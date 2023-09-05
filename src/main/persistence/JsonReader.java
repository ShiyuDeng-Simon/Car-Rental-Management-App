package persistence;

import model.Business;
import model.Car;
import model.Rental;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads business from JSON data stored in file
// Attributes to JsonSerializationDemo project.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads business from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Business read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBusiness(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // Effects: parse business from JSON object and return it.
    private Business parseBusiness(JSONObject jsonObject) {
        Business business = new Business();
        addListings(business, jsonObject);
        return business;
    }

    // Modifies: business
    // Effects: parses listings from JSON object and adds them to business
    private void addListings(Business business, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listings");
        for (Object json : jsonArray) {
            JSONObject nextListing = (JSONObject) json;
            addListing(business, nextListing);
        }
    }

    // Modifies: business
    // Effects: parses listing from JSON object and adds it to business.
    private void addListing(Business business, JSONObject jsonObject) {
        String pickupLocation = jsonObject.getString("pickupLocation");
        Double price = jsonObject.getDouble("price");
        Car car = parseCar(jsonObject.getJSONObject("car"));
        Rental rental = new Rental(car, price, pickupLocation);
        business.addRental(rental);
    }

    // Effects: parses car and returns it.
    private Car parseCar(JSONObject jsonObject) {
        String make = jsonObject.getString("make");
        String model = jsonObject.getString("model");
        int year = jsonObject.getInt("year");
        String type = jsonObject.getString("type");
        String color = jsonObject.getString("color");
        int seats = jsonObject.getInt("seats");
        String fuelType = jsonObject.getString("fuelType");
        String plateNum = jsonObject.getString("plateNum");
        return new Car(make, model, year, type, color, seats,fuelType, plateNum);
    }
}
