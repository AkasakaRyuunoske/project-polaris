package projectpolaris.Beginning_of_a_Dream.Components.Labels;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class EntitiesLabel extends JLabel {
    public EntitiesLabel(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("D:\\ProjectPolarisMBE\\ProjectPolaris\\src\\main\\java\\projectpolaris\\Beginning_of_a_Dream\\Components\\Labels\\OldEnglishTextMT.ttf"));
            Font sizedFont = font.deriveFont(Font.BOLD, 28f);
            setFont(sizedFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        setText("Entities:");

        setToolTipText("Below are entities that can be selected to smth idk idfc.");

        setBorder(new BasicBorders.ButtonBorder(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
        setBackground(Color.WHITE);
        setOpaque(true);

        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
