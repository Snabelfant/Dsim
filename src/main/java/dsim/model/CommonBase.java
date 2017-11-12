package dsim.model;

import dsim.dictionary.Patch;
import dsim.dictionary.PatchCommand;
import dsim.dictionary.PatchPredicate;

import java.util.Random;

/**
 * Created by Dag on 12.11.2017.
 */
public class CommonBase {
    private static final double EPSILON = 0.00001;
    private final Random random;

    protected CommonBase(int seed) {
        random = new Random(seed);
    }


    public static boolean equals(double d1, double d2) {
        return java.lang.Math.abs(d1 - d2) < EPSILON;
    }

    public int nextRandomInt(int minInclusive, int maxExclusive) {
        return minInclusive + random.nextInt(maxExclusive - minInclusive);
    }

    public int countPatches(PatchPredicate predicate) {
        int count = 0;
        for (Patch patch : World.patches().getPatchesAsList()) {
            if (predicate.evaluate(patch, World.getCommon())) {
                count++;
            }
        }

        return count;
    }

    public void askPatches(PatchCommand patchCommand) {
        for (Patch patch : World.patches().getPatchesAsList()) {
            patchCommand.run(patch, World.getCommon());
        }
    }
}
