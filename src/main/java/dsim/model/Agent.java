package dsim.model;

/**
 * Created by Dag on 30.09.2017.
 */
public interface Agent {
    Object NOBODY = new Object();

    default double toRadiansMod360(double degs) {
        return Math.toRadians(degs % 360);
    }
}
