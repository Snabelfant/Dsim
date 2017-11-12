package dsim.model;

import dsim.dictionary.Patch;
import dsim.dictionary.PatchCommand;
import dsim.dictionary.PatchPredicate;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CommonBase {
    private static final double EPSILON = 0.00001;
    private final Random random;

    protected CommonBase(int seed) {
        random = new Random(seed);
    }

    protected static boolean equals(double d1, double d2) {
        return java.lang.Math.abs(d1 - d2) < EPSILON;
    }

    protected int nextRandomInt(int minInclusive, int maxExclusive) {
        return minInclusive + random.nextInt(maxExclusive - minInclusive);
    }

    protected int count(List<Patch> patches, PatchPredicate predicate) {
        return (int) patches
                .stream()
                .filter(p -> predicate.evaluate(p, World.getCommon()))
                .count();
    }

    protected List<Patch> patches(PatchPredicate predicate) {
        List<Patch> patches = World.patches().getPatchesAsList();
        if (predicate == PatchPredicate.TRUE) {
            return patches;
        }

        return patches
                .stream()
                .filter(p -> predicate.evaluate(p, World.getCommon()))
                .collect(Collectors.toList());
    }

    protected void ask(List<Patch> patches, PatchCommand patchCommand) {
        patches.forEach(p -> patchCommand.run(p, World.getCommon()));
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
