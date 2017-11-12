package dsim.model;

import dsim.dictionary.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dag on 30.09.2017.
 */
public class Turtles {
    private static AtomicInteger nextTurtleId = new AtomicInteger(0);
    private List<TurtleBase> turtles = new ArrayList<>();

    public List<TurtleBase> getTurtles() {
        return turtles;
    }

    public TurtleBase createTurtle() {
        Turtle turtle = new Turtle(nextTurtleId.getAndIncrement());
        turtles.add(turtle);
        return turtle;
    }
}
