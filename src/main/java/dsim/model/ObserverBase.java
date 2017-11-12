package dsim.model;

import dsim.dictionary.Turtle;
import dsim.dictionary.TurtleCommand;

/**
 * Created by Dag on 11.11.2017.
 */
public class ObserverBase implements Agent {


    protected static TurtleBase createTurtle(TurtleCommand command) {
        TurtleBase turtle = World.turtles().createTurtle();
        command.run((Turtle) turtle);
        World.patches().placeTurtle(turtle);
        return turtle;

    }
}
