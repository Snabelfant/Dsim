package dsim.model;

import dsim.util.Util;

/**
 * Created by Dag on 08.10.2017.
 */
public class Position {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }

        Position pos = (Position) obj;
        return Util.equals(x,pos.x) && Util.equals(y, pos.y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
