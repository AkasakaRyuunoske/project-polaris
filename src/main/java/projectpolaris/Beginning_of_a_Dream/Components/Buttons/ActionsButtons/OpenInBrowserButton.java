package projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons;

import javax.swing.*;
import java.awt.*;

public class OpenInBrowserButton extends JButton {
    public OpenInBrowserButton() {
        setFocusable(false);
        setBackground(new Color(150, 68, 226));

        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setText("Open In Browser");
        setToolTipText("Click here to open in browser selected entity's stuff you know.");
    }
}
