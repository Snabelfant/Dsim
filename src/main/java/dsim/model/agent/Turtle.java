package dsim.model.agent;

import dsim.model.Position;
import dsim.model.World;

/**
 * Created by Dag on 30.09.2017.
 */
public class Turtle implements Agent {
    private Position position;
    private double headingInRads;
    private int id;
    Turtle(int id) {
        this.id = id;
        position = new Position(0,0);
        headingInRads = 0;
    }

    public Position getPosition() {
        return position;
    }

    public double getHeadingInRads() {
        return headingInRads;
    }

    public void setHeading(double headingInDegs) {
        assert headingInDegs >= 0 && headingInDegs <= 360;
        this.headingInRads = Math.toRadians(headingInDegs) ;
    }

    public double getHeading() {
        return Math.toDegrees(headingInRads);
    }
    public int getId() {
        return id;
    }

    public void jump(double distance) {
        position = World.jump(position, distance, headingInRads);
    }
}
