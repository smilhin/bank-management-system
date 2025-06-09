package window;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;

/**
 * This application simulates a multi-panel user interface for a bank system, including
 * basic authentication (no encryption or persistence) and navigation, but it does not
 * connect to an actual backend or database server.
 */

public class Launcher {
    public static void main(String[] args) {
        System.out.println(">> Launcher started <<");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.out.println("Failed to initialize LAF");
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BankJFrame frame = new BankJFrame();
            }
        });

    }
}