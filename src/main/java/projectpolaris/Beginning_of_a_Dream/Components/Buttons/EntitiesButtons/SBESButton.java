package projectpolaris.Beginning_of_a_Dream.Components.Buttons.EntitiesButtons;

import javax.swing.*;
import java.awt.*;

public class SBESButton extends JButton {
    public SBESButton() {
        setBackground(new Color(47, 117, 50));
        setFocusable(false);

        setText("SBES");
        setForeground(Color.BLACK);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setToolTipText("Click here to select Secondary Back End Spring(SBES) as active entity.");
    }
}
