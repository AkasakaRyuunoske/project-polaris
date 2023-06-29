package projectpolaris.Beginning_of_a_Dream.Components.Labels;

import projectpolaris.Beginning_of_a_Dream.CurrentEvent;

import javax.swing.*;

public class CurrentActivityLabel extends JLabel {
    public CurrentActivityLabel() {
        setText(CurrentEvent.currentEntity + ":");
        setToolTipText("Here will be presented current server");

        setBounds(0, 0, 1000, 50);

        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
