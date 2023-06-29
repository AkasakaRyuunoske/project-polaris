package projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons;

import javax.swing.*;
import java.awt.*;

public class ChangeConfigurationButton extends JButton {
    public ChangeConfigurationButton() {
        setFocusable(false);
        setBackground(new Color(150, 68, 226));

        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setText("Change Configuration");
        setToolTipText("Click here to change configuration of selected entity.");
    }
}
