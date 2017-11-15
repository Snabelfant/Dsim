package dsim.model;

import dsim.util.Util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dag on 30.09.2017.
 */
public class TurtleBase extends Agent {
    private final Map<String, Object> variables = new HashMap<>();
    private Position position;
    private double headingInRads;
    private Color color;
    private int id;


    protected TurtleBase(int id) {
        this.id = id;
        position = new Position(0, 0);
        headingInRads = Util.toRadiansMod360(Util.nextRandomInt(0, 360));
        color = Colors.randomColorNotBlack();
        getPatch().placeTurtle(this);
    }

    protected void define(String name) {
        super.defineVariable(name, variables);
    }

    protected void set(String name, int value) {
        super.setVariable(name, value, variables);
    }

    protected int getInt(String name) {
        return super.getInt(name, variables);
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

    protected void setY(double y) {
        getPatch().removeTurtle(this);
        position = new Position(position.getX(), y);
        getPatch().placeTurtle(this);
    }

    protected void setXY(double x, double y) {
        getPatch().removeTurtle(this);
        position = new Position(x, y);
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
        return "T" + id + "/" + position + "/" + headingInRads + "/" + color + "/" + getPatch().toString() + "/" + variables;
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
