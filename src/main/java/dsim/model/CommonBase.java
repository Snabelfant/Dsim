package dsim.model;

import dsim.dictionary.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class CommonBase {
    private static final Color[] COLORS = new Color[]
            {
                    Color.RED,
                    Color.BLUE,
                    Color.YELLOW,
                    Color.GREEN,
                    Color.BLACK,
                    Color.PINK,
                    Color.ORANGE,
                    Color.MAGENTA,
                    Color.DARK_GRAY,
                    Color.CYAN,
                    Color.WHITE,
                    Color.GRAY,
                    Color.LIGHT_GRAY
            };
    private static final double EPSILON = 0.00001;
    private static Random random = new Random(67777);

    protected CommonBase(int seed) {
        random = new Random(67777);
    }

    protected static Color randomColor() {
        return COLORS[nextRandomInt(0, COLORS.length)];
    }

    protected static int nextRandomInt(int minInclusive, int maxExclusive) {
        return minInclusive + random.nextInt(maxExclusive - minInclusive);
    }

    protected Color randomColorNotBlack() {
        Color color;
        while ((color = randomColor()) == Color.BLACK) ;
        return color;
    }

    protected int count(List<Patch> patches, PatchPredicate predicate) {
        return (int) patches
                .stream()
                .filter(predicate)
                .count();
    }

    protected List<Patch> patches(PatchPredicate predicate) {
        return World.patches().filter(predicate);
    }

    protected List<Turtle> turtles(TurtlePredicate predicate) {
        return World.turtles().filter(predicate);
    }

    protected void ask(List<Patch> patches, PatchCommand command) {
        patches.forEach(command);
    }

    protected void ask(List<Turtle> turtles, TurtleCommand command) {
        turtles.forEach(command);
    }

    protected Patch getPatchAt(Position position) {
        return World.patches().getPatchAt(position);
    }

    protected int randomPxcor() {
        return nextRandomInt(minPxcor(), maxPxcor() + 1);
    }

    protected int minPxcor() {
        return World.patches().getMinIndexX();
    }

    protected int maxPxcor() {
        return World.patches().getMaxIndexX();
    }

    protected int randomPycor() {
        return nextRandomInt(minPycor(), maxPycor() + 1);
    }

    protected int minPycor() {
        return World.patches().getMinIndexY();
    }

    protected int maxPycor() {
        return World.patches().getMaxIndexY();
    }
}
