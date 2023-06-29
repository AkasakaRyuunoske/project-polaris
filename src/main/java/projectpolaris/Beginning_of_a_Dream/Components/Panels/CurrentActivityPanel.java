package projectpolaris.Beginning_of_a_Dream.Components.Panels;

import projectpolaris.Beginning_of_a_Dream.Components.Labels.CurrentActivityLabel;
import projectpolaris.Beginning_of_a_Dream.CurrentEvent;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class CurrentActivityPanel extends JPanel {
    public CurrentActivityPanel(){
        setLayout(null);
        setBounds(0,0,2000,50); // #Todo not use 2k pixel width but something more responsive

        setBackground(Color.WHITE);
        setBorder(new BasicBorders.ButtonBorder(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));

        JLabel currentActivityLabel = new CurrentActivityLabel();
        currentActivityLabel.setBounds(100,0,1000,50); // #Todo not use 1k pixel width but something more responsive
        currentActivityLabel.setText(CurrentEvent.currentAction);

        add(new CurrentActivityLabel());
        add(currentActivityLabel);
    }
}
