package projectpolaris.Beginning_of_a_Dream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import projectpolaris.ProjectPolarisApplication;
import projectpolaris.Utilities.StartUpManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame implements ActionListener {
    JFrame mainFrame;
    JLabel mainLabel;
    JButton startUpButton;
    JButton shutDownButton;

    ConfigurableApplicationContext context;

    //#Todo All hardcoded values must be taken from application.properties
    //#Todo clean the code
    public MainFrame() {
        // Frame
        mainFrame = new JFrame();
        mainFrame.setTitle("The Beginning of a Dream"); // Okame-P ♥ ==> https://youtu.be/WJRkkABP9Xo
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(1280, 720);

        // Label
        mainLabel = new JLabel();
        mainLabel.setText("This is what you will use to effectively use ProjectPolaris");

        // button to activate spring boot app
        startUpButton = new JButton();
        startUpButton.setText("Start the application");
        startUpButton.setBackground(Color.MAGENTA);
        startUpButton.setToolTipText("Click here to startup the application");
        startUpButton.setBounds(0, 0, 160, 50);
        startUpButton.setFocusable(false);
        startUpButton.setVisible(true);

        startUpButton.addActionListener(this);

        // button to deactivate spring boot app
        shutDownButton = new JButton();
        shutDownButton.setText("Shut down the application");
        shutDownButton.setBackground(Color.MAGENTA);
        shutDownButton.setToolTipText("Click here to shut down the application");
        shutDownButton.setBounds(0, 50, 160, 50);
        shutDownButton.setFocusable(false);
        shutDownButton.setEnabled(false);
        shutDownButton.setVisible(true);

        shutDownButton.addActionListener(this);

        // Adding stuff and setting frame visible
        mainFrame.add(startUpButton);
        mainFrame.add(shutDownButton);
        mainFrame.add(mainLabel);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startUpButton) {
            context = new SpringApplicationBuilder(ProjectPolarisApplication.class)
                    .listeners(new StartUpManager()).run();

            shutDownButton.setEnabled(true);

            System.out.println("Nee Nee you finally back huh?");
        }

        if (e.getSource() == shutDownButton) {
            context.close();
            System.out.println("ShutDownAttempted");
        }
    }
}
