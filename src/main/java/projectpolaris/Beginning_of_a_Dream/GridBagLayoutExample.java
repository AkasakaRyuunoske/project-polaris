package projectpolaris.Beginning_of_a_Dream;

import javax.swing.*;
import java.awt.*;

public class GridBagLayoutExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GridBagLayoutExample::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create the JFrame
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the panels
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        panel1.setBackground(Color.MAGENTA);
        panel2.setBackground(Color.GREEN);
        // Set the layout manager for the JFrame
        frame.setLayout(new GridBagLayout());

        // Create GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.2; // Panel1 takes 20% of the screen width
        constraints.weighty = 1.0; // Both panels take full vertical space

        // Add panel1 to the JFrame
        frame.add(panel1, constraints);

        // Update the GridBagConstraints for panel2
        constraints.gridx = 1; // Move to the next column
        constraints.weightx = 0.7; // Panel2 takes 70% of the screen width

        // Add panel2 to the JFrame
        frame.add(panel2, constraints);

        // Set the JFrame visible
        frame.setVisible(true);
    }
}
