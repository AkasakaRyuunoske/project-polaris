package projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShutDownButton extends JButton implements ActionListener {
    public ShutDownButton(){
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setText("Shut down the application");
        setToolTipText("Click here to shut down the application");

        setBackground(new Color(150, 68, 226));

        setFocusable(false);
        setEnabled(false);
        setVisible(true);

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this){
            System.out.println("Yay another source works too");
        }
    }
}
