package dsim.model;

import dsim.dictionary.Turtle;
import dsim.dictionary.TurtlePredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Dag on 30.09.2017.
 */
public class Turtles {
    private static AtomicInteger nextTurtleId = new AtomicInteger(0);
    private List<Turtle> turtles = new ArrayList<>();

    public List<Turtle> asList() {
        return turtles;
    }

    public TurtleBase createTurtle() {
        Turtle turtle = new Turtle(nextTurtleId.getAndIncrement());
        synchronized (turtles) {
            turtles.add(turtle);
        }
        return turtle;
    }

    public List<Turtle> filter(TurtlePredicate predicate) {
        synchronized (turtles) {
            if (predicate == TurtlePredicate.TRUE) {
                return turtles;
            }

            return turtles
                    .stream()
                    .filter(predicate)
                    .collect(Collectors.toList());
        }

    }
}
