package dsim.commands;

import dsim.dictionary.Turtle;
import dsim.model.World;

import java.util.List;

/**
 * Created by Dag on 05.10.2017.
 */
public abstract class TurtlesProcedure extends Procedure {

    public TurtlesProcedure(boolean isForever) {
        super(isForever);
    }

     void run() {
         ((List<Turtle>) World.getTurtles()).forEach(this::runTurtle);
     }

    public abstract void runTurtle(Turtle turtle);
}
