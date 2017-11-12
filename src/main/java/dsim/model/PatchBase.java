package dsim.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dag on 02.10.2017.
 */
public class PatchBase implements Agent {
    private final List<TurtleBase> turtlesHere;
    private int x;
    private int y;
    private Color color;

    protected PatchBase(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
        this.turtlesHere = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "P:" + x + "/" + y + "/" + turtlesHere.size() + "/" + getDisplayColor();
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected synchronized Color getColor() {
        return color;
    }

    protected synchronized void setColor(Color color) {
        this.color = color;
    }

    protected synchronized void placeTurtle(TurtleBase turtle) {
        synchronized (turtlesHere) {
            turtlesHere.add(turtle);
        }
    }

    protected synchronized void removeTurtle(TurtleBase turtle) {
        synchronized (turtlesHere) {
            boolean removed = turtlesHere.remove(turtle);
            if (!removed) {
                throw new RuntimeException("Ikke fjernet; " + this);
            }
        }
    }

    public synchronized Color getDisplayColor() {
        Color c;
        if (turtlesHere.isEmpty()) {
            c = color;
        } else {
            c = turtlesHere.get(turtlesHere.size() - 1).getColor();
        }
        return c;
    }


}
