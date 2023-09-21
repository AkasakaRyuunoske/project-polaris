package projectpolaris.Beginning_of_a_Dream.Components.Labels;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OptionsLabel extends JLabel {
    public OptionsLabel() {

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("D:\\ProjectPolarisMBE\\ProjectPolaris\\src\\main\\java\\projectpolaris\\Beginning_of_a_Dream\\Components\\Labels\\OldEnglishTextMT.ttf"));
            Font sizedFont = font.deriveFont(Font.BOLD, 28f);
            setFont(sizedFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        setText("Options:");
        setToolTipText("Below are options that can be used against selected entity.");

        setBorder(new BasicBorders.ButtonBorder(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
        setBackground(Color.WHITE);
        setOpaque(true);

        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}