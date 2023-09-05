package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// represents a business with rental listings as an arbitrary sized array that contains rentals.
public class Business implements Writable {
    ArrayList<Rental> listings;

    // Effects: create a Business object with no elements.
    public Business() {
        listings = new ArrayList<>();
    }

    // Modifies: this
    // Effects: add the given rental to the listings.
    public void addRental(Rental rental) {
        listings.add(rental);
        EventLog.getInstance().logEvent(new Event("A new listing has been added"));
    }

    // Modifies: this
    // Effects: remove rental from listings and returns true,
    //          returns false if the given pos not found.
    public boolean removeRental(int pos) {
        if (pos <= listings.size() && pos > 0) {
            listings.remove(pos - 1);
            EventLog.getInstance().logEvent(new Event("The selected listing has been removed"));
            return true;
        } else {
            return false;
        }
    }

    // Effect: Returns rental overviews of all the rentals, if none
    //         exists, returns "Sorry, there is no existing listing."
    public String getRentalOverviews() {
        int i = 1;
        String overviews = "";

        if (listings.size() == 0) {
            return "Sorry, there is no existing listing.";
        } else {
            for (Rental rental : listings) {
                overviews += String.format("%d. %s\n",
                        i, rental.getRentalOverview());
                i++;
            }
            return overviews;
        }
    }


    // Effects: Returns rental details at the given pos, if
    //          rental does not exist, returns "listing not found".
    public String getRentalDetails(int pos) {
        if (pos <= listings.size() && pos > 0) {
            EventLog.getInstance().logEvent(new Event("Showing rental details of selected listing"));

            return this.getRental(pos).getRentalDetails();
        } else {
            return "Listing not found";
        }
    }

    // Requires: pos >= 0 and pos < size of listings.
    // Effects: Returns the rental at the given pos.
    public Rental getRental(int pos) {
        return listings.get(pos - 1);
    }

    public ArrayList<Rental> getListings() {
        return listings;
    }


    // Effects: filter rentals by car type.
    public ArrayList<Rental> getListingsWithFilter(String type) {
        ArrayList<Rental> results = new ArrayList<>();
        for (Rental r: this.listings) {
            if (r.getCar().getType().equals(type)) {
                results.add(r);
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtering listings by type -- " + type));
        return results;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listings", listingsToJson());
        return json;
    }

    // EFFECTS: returns listings as JSON array
    private JSONArray listingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Rental rental : listings) {
            jsonArray.put(rental.toJson());
        }
        return jsonArray;
    }
}
