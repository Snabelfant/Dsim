package dsim.model;

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

    private World(int cols, int rows, boolean wrapX, boolean wrapY) {
        turtles = new Turtles();
        patches = new Patches(cols, rows, wrapX, wrapY);
    }

    public static void create(int cols, int rows, boolean wrapX, boolean wrapY) {
        world = new World(cols, rows, wrapX, wrapY);
    }

    public static List<? extends TurtleBase> getTurtles() {
        return world.turtles.getTurtles();
    }

    public static void setPatchColor(int physicalCol, int physicalRow, Color color) {
        world.patches.getPatchByPhysicalIndex(physicalCol, physicalRow).setColor(color);
    }

    public static Color getPatchColor(int physicalCol, int physicalRow) {
        return world.patches.getDisplayColor(physicalCol, physicalRow);
    }

    public static PatchBase getPatchByPhysicalIndex(int physicalCol, int physicalRow) {
        return world.patches.getPatchByPhysicalIndex(physicalCol, physicalRow);
    }

    public static void tick() {
        View.repaintView();
    }

    public static Patches patches() {
        return world.patches;
    }

    static Turtles turtles() {
        return world.turtles;
    }
}
