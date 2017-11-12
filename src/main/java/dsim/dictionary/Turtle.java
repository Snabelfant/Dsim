package dsim.dictionary;

import dsim.model.TurtleBase;

import java.awt.*;

/**
 * Created by Dag on 30.09.2017.
 */
public class Turtle extends TurtleBase {

    public Turtle(int id) {
        super(id);
    }

    public void jump(double distance) {
        super.jump(distance);
    }

    public double heading() {
        return Math.toDegrees(getHeadingInRads());
    }

    public void heading(double headingInDegs) {
        setHeadingInRads(toRadiansMod360(headingInDegs));
    }

    public void right(double degrees) {
        turnRight(toRadiansMod360(degrees));
    }

    public Color color() {
        return getColor();
    }

    public void color(Color color) {
        setColor(color);
    }

    public Color pcolor() {
        return getPatchColor();
    }

    public void pcolor(Color color) {
        setPatchColor(color);
    }

    public void stamp(Color color) {
        setPatchColor(color);
    }

    public void xcor(double x) {
        setX(x);
    }

    public Patch patchHere() {
        return (Patch) getPatch();
    }

    public void fd(double distance) {
        forward(distance);
    }

    public void forward(double distance) {
        int steps = (int) Math.round(distance);
        double rest = distance - steps;

        for (int step = 0; step < steps; step++) {
            super.jump(1);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }

        super.jump(rest);
    }
}
