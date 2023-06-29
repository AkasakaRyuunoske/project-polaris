package projectpolaris.Beginning_of_a_Dream.Components.Buttons.EntitiesButtons;

import javax.swing.*;
import java.awt.*;

public class KafkaButton extends JButton {
    public KafkaButton() {
        setBackground(new Color(47, 117, 50));
        setFocusable(false);

        setText("Kafka");
        setForeground(Color.BLACK);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        setToolTipText("Click here to select Kafka as active entity.");
    }
}
