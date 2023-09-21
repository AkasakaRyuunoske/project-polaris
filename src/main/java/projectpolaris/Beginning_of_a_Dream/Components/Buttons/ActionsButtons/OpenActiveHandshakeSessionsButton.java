package projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenActiveHandshakeSessionsButton extends JButton implements ActionListener {
    public OpenActiveHandshakeSessionsButton() {
        setFocusable(false);
        setBackground(new Color(150, 68, 226));

        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setText("Open handshake sessions");
        setToolTipText("Click here to see currently active handshake sessions.");

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            System.out.println("Neeeeeeeee open active handshake sessions button is active too");

        }
    }
}
