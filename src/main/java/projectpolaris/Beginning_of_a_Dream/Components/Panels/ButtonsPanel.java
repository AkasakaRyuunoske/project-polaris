package projectpolaris.Beginning_of_a_Dream.Components.Panels;

import projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons.*;
import projectpolaris.Beginning_of_a_Dream.Components.Buttons.EntitiesButtons.*;
import projectpolaris.Beginning_of_a_Dream.Components.Labels.EntitiesLabel;
import projectpolaris.Beginning_of_a_Dream.Components.Labels.OptionsLabel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel(){
        // Layout
        GridLayout gridLayout = new GridLayout(15, 1);
        gridLayout.setVgap(2);

        setLayout(gridLayout);

        setBackground(new Color(17,17,17));
        setBorder(new BasicBorders.ButtonBorder(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));

        // Entities:
            // Label
        add(new EntitiesLabel());

            //Buttons
        add(new MBESButton());
        add(new SBESButton());
        add(new SBEPButton());
        add(new CoronaButton());
        add(new KafkaButton());
        add(new DBsButton());

        // Options:
            // Label
        add(new OptionsLabel());

            // Action Buttons
        add(new StartUpButton());
        add(new ShutDownButton());
        add(new ChangeConfigurationButton());
        add(new OpenLogsButton());
        add(new OpenCertificatesButton());
        add(new OpenActiveHandshakeSessionsButton());
        add(new OpenInBrowserButton());
    }
}
