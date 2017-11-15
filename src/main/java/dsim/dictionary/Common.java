package dsim.dictionary;

import dsim.model.CommonBase;
import dsim.model.Position;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dag on 12.11.2017.
 */
public class Common extends CommonBase {
    public Common(int seed) {
        super(seed);
    }

    public int random(int maxExclusive) {
        return nextRandomInt(0, maxExclusive);
    }

    public Patch patch(int pxcor, int pycor) {
        return super.getPatchAt(new Position(pxcor, pycor));
    }

    public List<Patch> patches() {
        return super.patches(PatchPredicate.TRUE);
    }

    public List<Turtle> turtles() {
        return turtles(TurtlePredicate.TRUE);
    }

    public List<Patch> patches(PatchPredicate predicate) {
        return super.patches(predicate);
    }

    public int count(List<Patch> patches, PatchPredicate predicate) {
        return super.count(patches, predicate);
    }

    public void ask(List<Patch> patches, PatchCommand command) {
        super.ask(patches, command);
    }

    public void ask(List<Turtle> turtles, TurtleCommand command) {
        super.ask(turtles, command);
    }

    public void ask(Patch patch, PatchCommand patchCommand) {
        super.ask(Arrays.asList(patch), patchCommand);
    }

    public int minPxcor() {
        return super.minPxcor();
    }

    public int maxPxcor() {
        return super.maxPxcor();
    }

    public int minPycor() {
        return super.minPycor();
    }

    public int maxPycor() {
        return super.maxPycor();
    }

    public int randomPxcor() {
        return super.randomPxcor();
    }

    public int randomPycor() {
        return super.randomPycor();
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }
}
