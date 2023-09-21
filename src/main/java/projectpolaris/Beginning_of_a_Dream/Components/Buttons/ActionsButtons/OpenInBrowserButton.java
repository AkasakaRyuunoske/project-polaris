package projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenInBrowserButton extends JButton implements ActionListener {
    public OpenInBrowserButton() {
        setFocusable(false);
        setBackground(new Color(150, 68, 226));

        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setText("Open In Browser");
        setToolTipText("Click here to open in browser selected entity's stuff you know.");

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this){
            System.out.println("Action performed inside openInBrowser button");
        }
    }
}
