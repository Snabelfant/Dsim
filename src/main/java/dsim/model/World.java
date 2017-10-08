package dsim.model;

import dsim.model.agent.Patch;
import dsim.model.agent.Patches;
import dsim.model.agent.Turtle;
import dsim.model.agent.Turtles;
import dsim.view.View;

import java.awt.*;
import java.util.List;

/**
 * Created by Dag on 01.10.2017.
 */
public class World {
    private static World world;
    private Turtles turtles;
    private Patches patches;

    private World(int rows, int cols, boolean wrapX, boolean wrapY) {
        turtles = new Turtles();
        patches = new Patches(rows, cols, wrapX, wrapY);
    }

    public static void create(int rows, int cols, boolean wrapX, boolean wrapY) {
        world = new World(rows, cols, wrapX, wrapY);
    }

    public static List<Turtle> getTurtles() {
        return world.turtles.getTurtles();
    }

    public static Turtle createTurtle() {
        return world.turtles.createTurtle();
    }

    public static void setPatchColor(int physicalRow, int physicalCol, Color color) {
        world.patches.getPatchByPhysicalIndex(physicalRow, physicalCol).setColor(color);
    }

    public static Color getPatchDisplayColor(int physicalRow, int physicalCol) {
        return world.patches.getDisplayColor(physicalRow, physicalCol);
    }

    public static Patch getPatchAt(int physicalRow, int physicalCol) {
        return world.patches.getPatchByPhysicalIndex(physicalRow, physicalCol);
    }

    public static void tick() {
        View.repaintView();
    }

    public static Position jump(Position from, double distance, double headingInRads) {
        return world.patches.jump(from, distance, headingInRads);
    }
}
