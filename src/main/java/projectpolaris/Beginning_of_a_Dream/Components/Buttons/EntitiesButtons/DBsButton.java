package projectpolaris.Beginning_of_a_Dream.Components.Buttons.EntitiesButtons;

import javax.swing.*;
import java.awt.*;

public class DBsButton extends JButton {
    public DBsButton() {
        setFocusable(false);
        setBackground(new Color(47, 117, 50));

        setText("DBs");
        setForeground(Color.BLACK);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setToolTipText("Click here to select a DB as active entity.");
    }
}
