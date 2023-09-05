package ui.components;

import model.Business;
import model.Car;
import model.Rental;

import javax.swing.*;

// A dialog that allows user to add new rental
public class AddNewRentalForm extends JDialog {
    private static final int HEIGHT = 30;
    JPanel panel;
    JTextField make;
    JTextField model;
    JTextField year;
    JComboBox type;
    JComboBox color;
    JComboBox seats;
    ButtonGroup fuelType;
    JTextField plateNum;
    JTextField price;
    JTextField pickupLocation;
    ButtonGroup bg;
    JButton addBtn;

    Business business;

    // Effects: Construct an add new rental form
    public AddNewRentalForm(JFrame parent, Business business) {
        super(parent, "New Listing Form");
        this.business = business;
        setLayout(null);
        setSize(600,600);

        panel = new JPanel();
        panel.setSize(600,800);
        panel.setLayout(null);
        initializeComponents();

        addLabelsToPanel();

        createTextFields();
        createComboBoxes();
        addRadioButtons();
        addSubmitBtn();
        addComponentsToPanel();
        add(panel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Modifies: this
    // Effects: initialize component
    private void initializeComponents() {
        make = new JTextField();
        model = new JTextField();
        year = new JTextField();
        type = new JComboBox();
        color = new JComboBox();
        seats = new JComboBox();
        fuelType = new ButtonGroup();
        plateNum = new JTextField();
        price = new JTextField();
        pickupLocation = new JTextField();
    }

    // Modifies: this
    // Effects: create combo boxes
    private void createComboBoxes() {
        type = createComboBox(340,60,150, new String[]{"SUV", "Sedan", "Minivan", "Truck", "Sports Car"});
        color = createComboBox(90, 100, 120,
                new String[]{"Black", "White", "Blue", "Silver", "Red", "Other"});
        seats = createComboBox(340,100,100, new String[]{"2", "4", "5", "6", "7"});
    }

    // Effects: add radio buttons
    private void addRadioButtons() {
        JRadioButton gas = new JRadioButton();
        JRadioButton hybrid = new JRadioButton();
        JRadioButton electric = new JRadioButton();


        gas.setBounds(100,160,30,20);
        hybrid.setBounds(160,160,30,20);
        electric.setBounds(220,160,30,20);
        gas.setActionCommand("Gas");
        hybrid.setActionCommand("Hybrid");
        electric.setActionCommand("Electric");
        gas.setSelected(true);
        bg = new ButtonGroup();
        bg.add(gas);
        bg.add(hybrid);
        bg.add(electric);
        panel.add(gas);
        panel.add(hybrid);
        panel.add(electric);

    }

    // Effects: add submit button
    private void addSubmitBtn() {
        addBtn = new JButton("Add New Listing");
        addBtn.setBounds(320,450,180,50);

        addBtn.addActionListener(e -> {
            String makeValue = make.getText();
            String modelValue = model.getText();
            int yearValue = Integer.parseInt(year.getText());
            String typeValue = (String) type.getSelectedItem();
            String colorValue = (String) color.getSelectedItem();
            int seatsValue = Integer.parseInt((String) seats.getSelectedItem());
            String fuelTypeValue = bg.getSelection().getActionCommand();
            String plateValue = plateNum.getText();
            Car car = new Car(makeValue, modelValue, yearValue, typeValue, colorValue, seatsValue,
                    fuelTypeValue, plateValue);

            Double priceValue = Double.parseDouble(price.getText());
            String locationValue = pickupLocation.getText();
            Rental newListing = new Rental(car,priceValue,locationValue);
            business.addRental(newListing);
            dispose();
        });
        panel.add(addBtn);
    }

    // Effects: Create combo box
    private JComboBox createComboBox(int x, int y, int width, String[] items) {
        JComboBox cb = new JComboBox<>(items);
        cb.setBounds(x, y, width, HEIGHT);
        return cb;
    }

    // Effects: add label to panel
    private void addLabelsToPanel() {
        createLabel(20, 20, 120, "Car Make:");
        createLabel(300, 20, 100, "Model:");
        createLabel(40,60,100,"Year:");
        createLabel(300, 60,100, "Type:");
        createLabel(40, 100,100, "Colour:");
        createLabel(300, 100,100, "Seats:");
        createLabel(20, 140,120, "Fuel Type:");
        createLabel(300, 140,120, "Plate#:");
        createLabel(20, 180,120, "Daily Rate:");
        createLabel(300, 180,140, "Pickup Location:");

        createLabel(100,140, 60, "Gas");
        createLabel(150,140, 60, "Hybrid");
        createLabel(210,140, 60, "Electric");
    }

    // Modifies: this
    // Effects: Create text fields
    private void createTextFields() {
        make = createTextField(90,20,160);
        model = createTextField(360, 20, 120);
        year = createTextField(90,60,100);
        plateNum = createTextField(350,140,160);
        price = createTextField(90,180,120);
        pickupLocation = createTextField(400, 180, 160);
    }

    // Effects: add components to panel
    private void addComponentsToPanel() {
        panel.add(make);
        panel.add(model);
        panel.add(year);

        panel.add(type);
        panel.add(color);
        panel.add(seats);
        panel.add(plateNum);
        panel.add(price);
        panel.add(pickupLocation);
    }

    // Effects: Create text field
    private JTextField createTextField(int x, int y, int width) {
        JTextField textField = new JTextField();
        textField.setBounds(x,y,width,HEIGHT);
        return textField;
    }

    // Effects: Create label
    private void createLabel(int x, int y, int width, String content) {
        JLabel label = new JLabel(content);
        label.setBounds(x,y,width,HEIGHT);
        panel.add(label);
    }
}
