package projectpolaris.Beginning_of_a_Dream.Components.Buttons.ActionsButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpButton extends JButton implements ActionListener {
    public StartUpButton(){
        setForeground(Color.BLUE);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setText("Start the application");
        setToolTipText("Click here to startup the application");

        setBackground(new Color(150, 68, 226));

        setFocusable(false);
        setVisible(true);

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this){
            System.out.println("Yey source works");
        }
    }
}
