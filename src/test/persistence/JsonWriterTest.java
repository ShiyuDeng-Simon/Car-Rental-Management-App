package persistence;


import model.Car;
import model.Business;
import model.Rental;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Attributes to JsonSerializationDemo project.
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Business business = new Business();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListing.json");
            writer.open();
            writer.write(business);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListing.json");
            business = reader.read();
            assertEquals(0, business.getListings().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterBusiness() {
        try {
            Business business = new Business();
            Car car1 = new Car("Honda","Civic",2022,"Sedan","White",5,"Gas",
                    "D48HP9");
            business.getListings().add(new Rental(car1,120, "VAN"));
            Car car2 = new Car("Toyota","RAV4",2012,"SUV","Black",5,"Gas",
                    "JG745S");
            business.getListings().add(new Rental(car2,150, "VAN"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(business);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            business = reader.read();

            List<Rental> listings = business.getListings();
            assertEquals(2, listings.size());
            checkRental(listings.get(0), 120.00, "VAN", "Honda", "Civic", 2022,
                    "Sedan", "White", 5, "Gas", "D48HP9");
            checkRental(listings.get(1), 150.00, "VAN", "Toyota", "RAV4", 2012,
                    "SUV", "Black", 5, "Gas", "JG745S");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
