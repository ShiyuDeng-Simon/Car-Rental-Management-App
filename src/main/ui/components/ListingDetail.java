package ui.components;

import model.Car;
import model.Rental;

import javax.swing.*;

// A dialog that allows users to view details of an existing listing
public class ListingDetail extends JDialog {
    Rental rental;
    JPanel panel;

    // Effects: Construct listing detail
    public ListingDetail(JFrame parent, Rental rental) {
        super(parent, "Rental Details");
        this.rental = rental;
        setLayout(null);
        setSize(300,600);

        panel = new JPanel();
        panel.setSize(600,800);
        panel.setLayout(null);
        addComponentsToPanel();
        add(panel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Effects: Create components to panel
    private void addComponentsToPanel() {
        Car car = rental.getCar();

        createLabel(30, 40,300, "Make:     " + car.getMake());
        createLabel(30, 80,300, "Model:    " + car.getModel());
        createLabel(30, 120,300, "Daily Rate: :    $" + rental.getPrice());
        createLabel(30, 160,300, "Year:   " + car.getYear());
        createLabel(30, 200,300, "Type:   " + car.getType());
        createLabel(30, 240,300, "Color:   " + car.getColor());
        createLabel(30, 280,300, "Seats:   " + car.getSeats());
        createLabel(30, 320,300, "Fuel Type:   " + car.getFuelType());
        createLabel(30, 360,300, "Pickup Location:   " + rental.getPickupLocation());
    }

    // Effects: Create label.
    private void createLabel(int x, int y, int width, String content) {
        JLabel label = new JLabel(content);
        label.setBounds(x,y,width,30);
        panel.add(label);
    }

}
