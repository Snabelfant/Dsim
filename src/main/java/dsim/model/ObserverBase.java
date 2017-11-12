package dsim.model;

import dsim.dictionary.Turtle;
import dsim.dictionary.TurtleCommand;

/**
 * Created by Dag on 11.11.2017.
 */
public class ObserverBase implements Agent {


    protected TurtleBase createTurtle(TurtleCommand command) {
        TurtleBase turtle = World.turtles().createTurtle();
        command.run((Turtle) turtle);
        return turtle;

    }
}
