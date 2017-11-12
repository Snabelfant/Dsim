package dsim.util;

import java.util.Random;

/**
 * Created by Dag on 02.10.2017.
 */
public class Util {
    private static final Random random = new Random(200);
    private static final double EPSILON = 0.00001;

     public static boolean equals(double d1, double d2) {
        return java.lang.Math.abs(d1 - d2) < EPSILON;
    }

    public static int nextRandomInt(int minInclusive, int maxExclusive) {
        return minInclusive + random.nextInt(maxExclusive - minInclusive);
    }
}
