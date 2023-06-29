package projectpolaris.Beginning_of_a_Dream.Components.Panels;

import projectpolaris.Beginning_of_a_Dream.Components.Labels.CurrentActivityImage;
import projectpolaris.Beginning_of_a_Dream.Components.Labels.CurrentActivityLabel;

import javax.swing.*;
import java.awt.*;

public class ActivityPanel extends JPanel {
    public ActivityPanel() {

        setLayout(null);
        setBackground(Color.BLACK);

        System.out.println(getSize());

        add(new CurrentActivityPanel());
        add(new CurrentActivityImage());
    }
}
