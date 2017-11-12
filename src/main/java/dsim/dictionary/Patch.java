package dsim.dictionary;

import dsim.model.PatchBase;

import java.awt.*;

/**
 * Created by Dag on 02.10.2017.
 */
public class Patch extends PatchBase {
    public Patch(int x, int y) {
        super(x, y);
    }

    public int pxcor() {
        return getX();
    }

    public int pycor() {
        return getY();
    }

    public void pcolor(Color color) {
        setColor(color);
    }

    public Color pcolor() {
        return getColor();
    }
}
