package projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons;

import javax.swing.*;
import java.awt.*;

public class OpenLogsButton extends JButton {
    public OpenLogsButton() {
        setFocusable(false);
        setBackground(new Color(150, 68, 226));

        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setText("Open logs");
        setToolTipText("Click here to open logs of selected entity.");
    }
}
