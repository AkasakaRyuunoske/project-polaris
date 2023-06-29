package projectpolaris.Beginning_of_a_Dream.Components.Labels;

import javax.swing.*;
import java.awt.*;

public class CurrentActivityImage extends JLabel {
    public CurrentActivityImage() {
        ImageIcon img = new ImageIcon("D:/ProjectPolarisMBE/ProjectPolaris/src/main/java/projectpolaris/Beginning_of_a_Dream/Components/Labels/yey.png");

        setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        setBackground(Color.MAGENTA);
        setOpaque(true);
        setIcon(img);
    }
}
