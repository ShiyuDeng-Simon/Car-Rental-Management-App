package ui.components;

import model.Business;
import model.Car;
import model.Rental;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// A dialog that allows users to view existing listings
public class ListingOverview extends JDialog {
    Business business;
    JPanel panel;
    JFrame parent;
    String filter;
    ArrayList<Rental> filteredResults;
    Map<Rental,Integer> map;

    // Effects: Construct listing overview
    public ListingOverview(JFrame parent, Business business) {
        super(parent, "My Rental Listings");
        this.business = business;
        this.filteredResults = (ArrayList)business.getListings().clone();
        this.parent = parent;
        this.filter = "All";
        setLayout(null);
        setSize(680,860);
        createMap();
        createContent();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Effects: create a hashmap that stores the index of each rental.
    private void createMap() {
        map = new HashMap<>();
        for (int i = 0; i < business.getListings().size(); i++) {
            map.put(business.getListings().get(i),i + 1);
        }
    }

    // Effects: Create content
    private void createContent() {
        panel = new JPanel();
        panel.setSize(680,860);
        panel.setLayout(null);
        createFilters();
        createListing();
        createDeleteButtons();
        add(panel);
        panel.revalidate();
        panel.repaint();
    }

    // Effect: create listing
    private void createListing() {
        handleNoResultCase();
        for (int i = 0; i < filteredResults.size(); i++) {
            Rental r = filteredResults.get(i);
            Car c = r.getCar();
            JLabel label = new JLabel(c.getCarOverview() + "  Daily Rate: " + r.getPrice());
            JButton btn = new JButton("View Details");
            int listingID = map.get(r);
            btn.setActionCommand(Integer.toString(listingID));
            btn.addActionListener(e -> {
                int index = Integer.parseInt(e.getActionCommand());
                new ListingDetail(parent, business.getRental(index));
            });
            btn.setBounds(420,150 + 50 * i,120,35);
            label.setBounds(60, 150 + 50 * i,400,30);
            panel.add(label);
            panel.add(btn);
        }
    }

    // Effects: handle no result case
    private void handleNoResultCase() {
        if (business.getListings().size() == 0 && filter.equals("All")) {
            JLabel label = new JLabel("You Don't Have Any Rental Listing Yet");
            label.setBounds(200,100,320,20);
            panel.add(label);
        } else if (filteredResults.size() == 0) {
            JLabel label = new JLabel("No Listings Matching the Filter");
            label.setBounds(200,100,320,20);
            panel.add(label);
        }
    }

    // Effects: create delete button
    private void createDeleteButtons() {
        for (int i = 0; i < filteredResults.size(); i++) {
            JButton btn = new JButton("X");
            btn.setBounds(560,150 + 50 * i,50,35);
            int id = map.get(filteredResults.get(i));
            btn.setActionCommand(Integer.toString(id));
            btn.addActionListener(e -> {
                int listingID = Integer.parseInt(e.getActionCommand());
                business.removeRental(listingID);
                updateResultsWithFilter();
                panel.removeAll();
                createMap();
                createContent();
            });
            panel.add(btn);
        }
    }

    // Effects: update results with filter
    private void updateResultsWithFilter() {
        if (filter.equals("All")) {
            filteredResults = (ArrayList)business.getListings().clone();
        } else {
            filteredResults = business.getListingsWithFilter(filter);
        }
    }

    // Effects: Create filter
    private void createFilters() {
        if (business.getListings().size() != 0) {
            JLabel label = new JLabel("Filter by Car Type");
            label.setBounds(350,20,200,30);
            JComboBox cb = new JComboBox<>(new String[]{"All","SUV", "Sedan", "Minivan", "Truck", "Sports Car"});
            cb.setSelectedItem(filter);
            cb.setBounds(480, 20,100,30);
            cb.addActionListener(e -> {
                filter = (String) cb.getSelectedItem();

                updateResultsWithFilter();
                panel.removeAll();
                createContent();
            });
            panel.add(cb);
            panel.add(label);
        }
    }
}
