package projectpolaris.Beginning_of_a_Dream;

import org.springframework.context.ConfigurableApplicationContext;
import projectpolaris.Beginning_of_a_Dream.Components.CustomWindowListener;
import projectpolaris.Beginning_of_a_Dream.Components.Panels.ActivityPanel;
import projectpolaris.Beginning_of_a_Dream.Components.Panels.ButtonsPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    JFrame mainFrame;

    ConfigurableApplicationContext context;

    //#Todo All hardcoded values must be taken from application.properties
    //#Todo clean the code
    public MainFrame() {
        // Layout
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // Frame
        mainFrame = new JFrame();
        mainFrame.setTitle("The Beginning of a Dream"); // Okame-P ♥ ==> https://youtu.be/WJRkkABP9Xo
        mainFrame.addWindowListener(new CustomWindowListener());
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setSize(1280, 720);

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1; // Panel takes 30% of the screen width
        gridBagConstraints.weighty = 1.0; // Both panels take full vertical space

        mainFrame.add(new ButtonsPanel(), gridBagConstraints);

        gridBagConstraints.weightx = 0.7; // Panel takes 70% of the screen width
        gridBagConstraints.weighty = 1.0;

        mainFrame.add(new ActivityPanel(), gridBagConstraints);

        mainFrame.setVisible(true);
    }

//    public MainFrame() {
//        // Layout
//        GridBagConstraints gridBagConstraints = new GridBagConstraints();
//
//        // Frame
//        mainFrame = new JFrame();
//        mainFrame.setTitle("The Beginning of a Dream"); // Okame-P ♥ ==> https://youtu.be/WJRkkABP9Xo
////        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        mainFrame.addWindowListener(new CustomWindowListener());
//        mainFrame.setLayout(new GridBagLayout());
//        mainFrame.setSize(1280, 720);
//
//        // Buttons Panel
//        buttonsPanel = new JPanel();
//        buttonsPanel.setLayout(new GridLayout(15, 1));
//        buttonsPanel.setBackground(Color.MAGENTA);
//
//        // Activity Panel
//        activityPanel = new JPanel();
//        activityPanel.setLayout(new GridBagLayout());
//        activityPanel.setBackground(Color.GREEN);
//
//        // Current Activity Label
//        currentActivityLabel = new JLabel();
//        currentActivityLabel.setText("Selected Server: Selected Activity");
////        currentActivityLabel.setOpaque(true);
//
//        // Current Activity Panel
//        currentActivityPanel = new JPanel();
//        currentActivityLabel.setLayout(null);
//        currentActivityPanel.setBackground(Color.BLUE);
//
//        // Label
//        mainLabel = new JLabel();
//        mainLabel.setText("This is what you will use to effectively use ProjectPolaris");
//
//        // button to activate spring boot app
//        startUpButton = new JButton();
//        startUpButton.setText("Start the application");
//        startUpButton.setBackground(Color.MAGENTA);
//        startUpButton.setToolTipText("Click here to startup the application");
//        startUpButton.setFocusable(false);
//        startUpButton.setVisible(true);
//
//        startUpButton.addActionListener(this);
//
//        // button to deactivate spring boot app
//        shutDownButton = new JButton();
//        shutDownButton.setText("Shut down the application");
//        shutDownButton.setBackground(Color.MAGENTA);
//        shutDownButton.setToolTipText("Click here to shut down the application");
//        shutDownButton.setFocusable(false);
//        shutDownButton.setEnabled(false);
//        shutDownButton.setVisible(true);
//
//        shutDownButton.addActionListener(this);
//
//        // Adding stuff and setting frame visible
//        buttonsPanel.add(startUpButton);
//        buttonsPanel.add(shutDownButton);
//        buttonsPanel.add(mainLabel);
////        mainFrame.add(startUpButton);
////        mainFrame.add(shutDownButton);
////        mainFrame.add(mainLabel);
//
//        gridBagConstraints.fill = GridBagConstraints.BOTH;
//        gridBagConstraints.weightx = 0.1; // Panel takes 30% of the screen width
//        gridBagConstraints.weighty = 1.0; // Both panels take full vertical space
//
//        mainFrame.add(buttonsPanel, gridBagConstraints);
//
////        gridBagConstraints.anchor = GridBagConstraints.CENTER;
//        gridBagConstraints.anchor = GridBagConstraints.ABOVE_BASELINE;
//        gridBagConstraints.weightx = 1.0;
//        gridBagConstraints.weighty = 0.2;
//
//        activityPanel.add(currentActivityLabel, gridBagConstraints);
//
//        gridBagConstraints.weightx = 0.7; // Panel takes 70% of the screen width
//        gridBagConstraints.weighty = 1.0;
//
//        mainFrame.add(activityPanel, gridBagConstraints);
//
////        mainFrame.add(currentActivityPanel, gridBagConstraints);
//
//        mainFrame.setVisible(true);
//    }
}
