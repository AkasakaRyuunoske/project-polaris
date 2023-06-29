package projectpolaris.Beginning_of_a_Dream.Components.Buttons.EntitiesButtons;

import javax.swing.*;
import java.awt.*;

public class CoronaButton extends JButton {
    public CoronaButton() {
        setFocusable(false);
        setBackground(new Color(47, 117, 50));

        setText("Corona");
        setForeground(Color.BLACK);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setToolTipText("Click here to select Corona as active entity.");
    }
}
