package dsim.model;

import dsim.model.agent.Patch;
import dsim.model.agent.Patches;
import dsim.model.agent.Turtle;
import dsim.model.agent.Turtles;
import dsim.view.ViewX;
import dsim.view.View2;

import java.awt.*;
import java.util.List;

/**
 * Created by Dag on 01.10.2017.
 */
public class World {
    private static World world;
    private Turtles turtles;
    private Patches patches;

    private World(int cols,int rows, boolean wrapX, boolean wrapY) {
        turtles = new Turtles();
        patches = new Patches(cols,rows, wrapX, wrapY);
    }

    public static void create(int cols,int rows,  boolean wrapX, boolean wrapY) {
        world = new World(cols,rows,  wrapX, wrapY);
    }

    public static List<Turtle> getTurtles() {
        return world.turtles.getTurtles();
    }

    public static Turtle createTurtle() {
        return world.turtles.createTurtle();
    }

    public static void setPatchColor(int physicalCol,int physicalRow,  Color color) {
        world.patches.getPatchByPhysicalIndex( physicalCol, physicalRow).setColor(color);
    }

    public static Color getPatchDisplayColor( int physicalCol, int physicalRow) {
        return world.patches.getDisplayColor(physicalCol, physicalRow);
    }

    public static Patch getPatchByPhysicalIndex(int physicalCol, int physicalRow) {
        return world.patches.getPatchByPhysicalIndex(physicalCol, physicalRow);
    }

    public static Patch getPatchAt(Position position) {
        return world.patches.getPatchAt(position);
    }

    public static void tick() {
        View2.repaintView();
    }

    public static Position jump(Position from, double distance, double headingInRads) {
        return world.patches.jump(from, distance, headingInRads);
    }
}
