package dsim.model;

import dsim.dictionary.Turtle;
import dsim.dictionary.TurtleCommand;

/**
 * Created by Dag on 11.11.2017.
 */
public class ObserverBase extends Agent {


    protected TurtleBase createTurtle(TurtleCommand command) {
        TurtleBase turtle = World.turtles().createTurtle();
        command.accept((Turtle) turtle);
        return turtle;

    }

    public void resetTicks() {
        World.resetTicks();
    }

    public void tick() {
        World.tick();
    }
}
