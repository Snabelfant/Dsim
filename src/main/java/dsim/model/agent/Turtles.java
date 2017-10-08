package dsim.model.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dag on 30.09.2017.
 */
public class Turtles {
    private static AtomicInteger nextTurtleId = new AtomicInteger(0);
    private List<Turtle> turtles = new ArrayList<>();

    public List<Turtle> getTurtles() {
        return turtles;
    }

    public Turtle createTurtle() {
        Turtle turtle = new Turtle(nextTurtleId.getAndIncrement());
        turtles.add(turtle);
        return turtle;
    }
}
