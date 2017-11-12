package dsim.model;

import dsim.util.Util;

import java.awt.*;

/**
 * Created by Dag on 30.09.2017.
 */
public class TurtleBase implements Agent {
    private Position position;
    private double headingInRads;
    private Color color;
    private int id;
    protected TurtleBase(int id) {
        this.id = id;
        position = new Position(0, 0);
        headingInRads = toRadiansMod360(Util.nextRandomInt(0, 360));
        color = Colors.randomColorNotBlack();
        getPatch().placeTurtle(this);
    }

    protected double getHeadingInRads() {
        return headingInRads;
    }

    protected void setHeadingInRads(double headingInRads) {
        this.headingInRads = headingInRads;
    }

    protected void jump(double distance) {
        getPatch().removeTurtle(this);
        position = World.patches().jump(position, distance, headingInRads);
        getPatch().placeTurtle(this);
    }

    protected void setX(double x) {
        getPatch().removeTurtle(this);
        position = new Position(x, position.getY());
        getPatch().placeTurtle(this);
    }
    protected Color getPatchColor() {
        return getPatch().getColor();
    }

    protected void setPatchColor(Color color) {
        getPatch().setColor(color);
    }

    protected synchronized Color getColor() {
        return color;
    }

    protected synchronized void setColor(Color color) {
        this.color = color;
    }

    protected PatchBase getPatch() {
        return World.patches().getPatchAt(position);
    }

    @Override
    public String toString() {
        return "T" + id + "/" + position + "/" + headingInRads + "/" + color + "/" + getPatch().toString();
    }

    Position getPosition() {
        return position;
    }

    protected void turnRight(double deltaInRads) {
        headingInRads = (headingInRads + deltaInRads) % (2 * Math.PI);
    }


    int getId() {
        return id;
    }


    @Override
    public boolean equals(Object obj) {
        return id == ((TurtleBase) obj).id;
    }

}
