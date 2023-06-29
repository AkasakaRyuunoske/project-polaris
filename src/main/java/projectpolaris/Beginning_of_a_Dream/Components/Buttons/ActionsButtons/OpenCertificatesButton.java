package projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons;

import javax.swing.*;
import java.awt.*;

public class OpenCertificatesButton extends JButton {
    public OpenCertificatesButton() {
        setFocusable(false);
        setBackground(new Color(150, 68, 226));

        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setText("Open Certificates");
        setToolTipText("Click here to open incoming/out-coming certificates/keys of selected entity.");
    }
}
