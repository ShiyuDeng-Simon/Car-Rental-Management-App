package ui;

import model.Business;
import model.Event;
import model.EventLog;
import ui.components.MainMenu;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

// Application GUI
public class CarRentalGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private Business business;

    // Effects: create a Car Rental GUI
    public CarRentalGUI() {
        super("Car Rental App");
        business = new Business();
        playAudio();
        initializeGraphics();
    }

    // Effects: Set the formats
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                printLog();
            }
        });

        setLocationRelativeTo(null);
        createMenu();
        setVisible(true);
    }

    // Effects: Plays an audio when the application runs
    private void playAudio() {
        try {
            //sound source: https://quicksounds.com/library/sounds/sports-car
            File audio = new File("./data/audio/audio.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to play sound");
        }

    }

    // Effects: add menu to panel
    private void createMenu() {
        JPanel mainMenu = new JPanel();
        mainMenu.setVisible(true);
        add(new MainMenu(this, business));
    }

    // Effects: print the log to the console.
    private void printLog() {
        EventLog el = EventLog.getInstance();
        System.out.println("Event logs:  \n");
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }
}
