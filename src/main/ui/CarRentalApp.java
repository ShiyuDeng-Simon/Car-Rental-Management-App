package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Car rental application
public class CarRentalApp {

    private Scanner scanner;
    private Business business;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Effects: Run car rental application
    public CarRentalApp() {
        scanner = new Scanner(System.in);
        business = new Business();
        jsonWriter = new JsonWriter("./data/business.json");
        jsonReader = new JsonReader("./data/business.json");
        runApp();
    }

    // Effects: Process user input.
    public void runApp() {
        System.out.println("Hi, welcome to XXX car rental service. Are you a customer or a business?");
        int opt = 0;
        while (opt != 1 && opt != 2) {
            System.out.println("Are you a customer or a business?");
            System.out.println("1. Customer");
            System.out.println("2. Business");
            opt = scanner.nextInt();
        }
        if (opt == 1) {
            System.out.println(business.getRentalOverviews());
        } else {
            runBusiness();
        }
    }

    // Process business user input.
    private void runBusiness() {
        Boolean runApp = true;
        int opt;
        while (runApp) {
            displayBusinessOperations();
            opt = scanner.nextInt();
            if (opt == 7) {
                runApp = false;
            } else {
                handleBusinessOperation(opt);
            }
        }
        System.out.println("Thank you for using the app. Have a nice day!");
        printLog();
    }

    private void printLog() {
        EventLog el = EventLog.getInstance();
        System.out.println("Event logs:  \n");
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

    private void displayBusinessOperations() {
        System.out.println("Please select from the following operations.");
        System.out.println("\t1. Add a rental.");
        System.out.println("\t2. Remove a rental.");
        System.out.println("\t3. View my listings.");
        System.out.println("\t4. Update listing price");
        System.out.println("\t5. Save business to file");
        System.out.println("\t6. Load business from file");
        System.out.println("\t7. Quit the app");
    }

    private void handleBusinessOperation(int opt) {
        if (opt == 1) {
            businessGetRentalInfo();
        } else if (opt == 2) {
            businessRemoveRental();
        } else if (opt == 3) {
            overViewOptions();
        }  else if (opt == 4) {
            updateListing();
        } else if (opt == 5) {
            saveBusiness();
        }  else if (opt == 6) {
            loadBusiness();
        } else {
            System.out.println("invalid input");
        }
    }

    // Modifies: this
    // Effects: Conduct a rental removal.
    private void businessRemoveRental() {
        if (business.getListings().size() == 0) {
            System.out.println("No rental found");
        } else {
            System.out.println("Choose the rental you would like to remove");
            System.out.println(business.getRentalOverviews());
            int opt = scanner.nextInt();
            if (business.removeRental(opt)) {
                System.out.println("Successfully removed");
                overViewOptions();
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    // Modifies: this
    // Effects: Get user input on rental details and create a rental.
    private void businessGetRentalInfo() {
        System.out.println("What is your car make?");
        String make = scanner.next();

        System.out.println("What is the model of your car?");
        String model = scanner.next();

        System.out.println("What is the year of your car?");
        int year = scanner.nextInt();

        System.out.println("What is the type of your car?");
        String type = scanner.next();

        System.out.println("What is the color of your car?");
        String color = scanner.next();

        System.out.println("How many seats does your car have?");
        int seats = scanner.nextInt();

        System.out.println("What is the fuel type of your car?");
        String fuelType = scanner.next();

        System.out.println("What is the plate number of your car?");
        String plateNum = scanner.next();

        System.out.println("What is your expected daily rate of the rental?");
        double price = scanner.nextInt();

        System.out.println("Where is the pick up location of the rental?");
        String pickUpLocation = scanner.next();

        Rental rental = new Rental(new Car(make, model, year, type, color, seats, fuelType, plateNum),
                price, pickUpLocation);
        businessAddRental(rental);
    }

    // Modifies: this
    // Effects: add rental to rental listings.
    private void businessAddRental(Rental rental) {
        business.addRental(rental);
        System.out.println("Congrats! You Successfully created a rental!");
        overViewOptions();
    }

    // Modifies: this
    // Effects: update rental info.
    private void updateListing() {
        System.out.println("Select a rental to update it's rate");
        System.out.println(business.getRentalOverviews());
        System.out.println("Enter \"m\" to go back to main menu");
        String opt = scanner.next();
        if (!opt.equals("m")) {
            System.out.println("Enter the new rate for this rental");
            Double newRate = scanner.nextDouble();
            Rental rental = business.getRental(Integer.parseInt(opt));
            rental.setPrice(newRate);
            System.out.println(String.format("You have updated rental rate of %s to %.2f per day",
                    rental.getCar().getCarOverview(), newRate));
        }
    }

    // Effects: displays menu of options to user
    private void overViewOptions() {
        System.out.println("Select a rental to view more details.");
        System.out.println(business.getRentalOverviews());
        System.out.println("Enter \"m\" to go back to main menu");
        String opt = scanner.next();
        if (!opt.equals("m")) {
            System.out.println(business.getRentalDetails(Integer.parseInt(opt)));
            overViewOptions();
        }
    }

    // EFFECTS: saves the business to file
    // Attributes to JsonSerializationDemo project.
    private void saveBusiness() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(this.business);
            this.jsonWriter.close();
            System.out.println("Saved business listings to ./data/business.json");
        } catch (FileNotFoundException var2) {
            System.out.println("Unable to write to file: ./data/business.json");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads business from file
    // Attributes to JsonSerializationDemo project.
    private void loadBusiness() {
        try {
            this.business = this.jsonReader.read();
            System.out.println("Loaded  business listings from ./data/business.json");
        } catch (IOException var2) {
            System.out.println("Unable to read from file: ./data/business.json");
        }
    }
}
