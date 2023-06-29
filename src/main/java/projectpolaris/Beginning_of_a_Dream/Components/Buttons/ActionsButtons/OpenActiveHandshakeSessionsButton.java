package projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons;

import javax.swing.*;
import java.awt.*;

public class OpenActiveHandshakeSessionsButton extends JButton {
    public OpenActiveHandshakeSessionsButton() {
        setFocusable(false);
        setBackground(new Color(150, 68, 226));

        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setText("Open handshake sessions");
        setToolTipText("Click here to see currently active handshake sessions.");
    }
}
