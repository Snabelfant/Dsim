package dsim.util;

/**
 * Created by Dag on 02.10.2017.
 */
public class Util {
    private static final double EPSILON = 0.00001;
    private static final double PI180 = java.lang.Math.PI / 180;

    public static double toRadians(double degrees) {
        return (degrees % 360) * PI180 ;
    }

    public static boolean equals(double d1, double d2) {
        return java.lang.Math.abs(d1 - d2) < EPSILON;
    }

    public static double toDegrees(double radians) {
        return radians / PI180;
    }


}
