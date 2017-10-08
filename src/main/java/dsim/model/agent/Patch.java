package dsim.model.agent;

import java.awt.*;

/**
 * Created by Dag on 02.10.2017.
 */
public class Patch implements Agent {
    private int x;
    private int y;
    private Color color;
    Patch(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
     }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getDisplayColor() {
//        System.out.println("patch paint " + x + "/" + y);
        return color;
    }
}
