package dsim.model;

import dsim.util.Util;

import java.awt.*;

/**
 * Created by Dag on 12.11.2017.
 */
public class Colors {
    private static final Color[] COLORS = new Color[]
            {
                    Color.RED,
                    Color.BLUE,
                    Color.YELLOW,
                    Color.GREEN,
                    Color.BLACK,
                    Color.PINK,
                    Color.ORANGE,
                    Color.MAGENTA,
                    Color.DARK_GRAY,
                    Color.CYAN,
                    Color.WHITE,
                    Color.GRAY,
                    Color.LIGHT_GRAY
            };


    public static Color randomColorNotBlack() {
        Color color;
        while ((color = randomColor()) == Color.BLACK) ;
        return color;
    }

    public static Color randomColor() {
        return COLORS[Util.nextRandomInt(0, COLORS.length)];
    }

}
