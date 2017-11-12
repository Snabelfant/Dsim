package dsim.dictionary;

import dsim.model.ObserverBase;

/**
 * Created by Dag on 11.11.2017.
 */
public class Observer extends ObserverBase {
    private Observer() {
    }

    public static void createTurtles(int count, TurtleCommand command) {
        for (int i = 0; i < count; i++) {
            createTurtle(command);
        }
    }

    public static void createTurtles(int count) {
        createTurtles(count, TurtleCommand.NONE);
    }
}
