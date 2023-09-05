package persistence;

import model.Rental;
import model.Business;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Attributes to JsonSerializationDemo project.
public class JsonReaderTest extends  JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistent.json");
        try {
            Business business = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBusiness() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListing.json");
        try {
            Business business = reader.read();
            assertEquals(0, business.getListings().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderBusiness() {
        JsonReader reader = new JsonReader("./data/testReaderBusiness.json");
        try {
            Business business = reader.read();
            List<Rental> listings = business.getListings();
            assertEquals(2, listings.size());
            checkRental(listings.get(0), 120.00, "VAN", "Honda", "Civic", 2022,
                    "Sedan", "White", 5, "Gas", "D48HP9");
            checkRental(listings.get(1), 150.00, "VAN", "Toyota", "RAV4", 2012,
                    "SUV", "Black", 5, "Gas", "JG745S");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
