package ui.components;

import model.Business;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// A panel of main menu
public class MainMenu extends JPanel implements ActionListener {
    JButton viewListingsBtn;
    JButton addListingBtn;
    JButton saveBtn;
    JButton loadBtn;
    JFrame parent;

    Business business;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Effects: Construct a main menu
    public MainMenu(JFrame parent, Business business)  {
        jsonWriter = new JsonWriter("./data/business.json");
        jsonReader = new JsonReader("./data/business.json");
        this.business = business;
        this.parent = parent;
        setLayout(null);
        initializeMenu();

        JLabel image = new JLabel(new ImageIcon("./data/image/image1.jpeg"));
        image.setBounds(60,80,600,500);
        add(image);

        add(viewListingsBtn);
        add(addListingBtn);
        add(saveBtn);
        add(loadBtn);
        setVisible(true);
    }

    // Effects: render the check mark
    private void renderCompletionUI() {
        //https://icons8.com/icons/set/checkmark
        ImageIcon icon = new ImageIcon("./data/image/checkmark.gif");
        JLabel label = new JLabel(icon);
        label.setBounds(860, 600, 50,50);
        add(label);
        revalidate();
        repaint();
        Timer timer = new Timer(650, e -> {
            remove(label);
            revalidate();
            repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    // Modifies: this
    // Effects: initialize all the components
    private void initializeMenu() {
        viewListingsBtn = new JButton("View My Listings");
        addListingBtn = new JButton("Add New Listing");
        saveBtn = new JButton("Save Rental Listings");
        loadBtn = new JButton("Load Listings From File");
        viewListingsBtn.setPreferredSize(new Dimension(180, 50));
        viewListingsBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        addListingBtn.setPreferredSize(new Dimension(180, 50));
        addListingBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        saveBtn.setPreferredSize(new Dimension(180, 50));
        saveBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        loadBtn.setPreferredSize(new Dimension(180, 50));
        loadBtn.setFont(new Font("Arial", Font.PLAIN, 16));

        viewListingsBtn.setBounds(680,100,200,50);
        addListingBtn.setBounds(680,200,200,50);
        saveBtn.setBounds(680,300,200,50);
        loadBtn.setBounds(680,400,200,50);

        viewListingsBtn.addActionListener(this);
        addListingBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        loadBtn.addActionListener(this);
    }

    // Effects: Respond to actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewListingsBtn) {
            new ListingOverview(parent, business);
        } else if (e.getSource() == addListingBtn) {
            new AddNewRentalForm(parent, business);
        } else if (e.getSource() == saveBtn) {
            saveBusiness();
        } else if (e.getSource() == loadBtn) {
            loadBusiness();
        }
    }

    // Effects: Save the current state.
    private void saveBusiness() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(this.business);
            this.jsonWriter.close();
            System.out.println("Saved business listings to ./data/business.json");
            renderCompletionUI();
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
            renderCompletionUI();
        } catch (IOException var2) {
            System.out.println("Unable to read from file: ./data/business.json");
        }
    }
}
