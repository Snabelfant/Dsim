package dsim.commands;

import dsim.model.World;
import dsim.model.agent.Turtle;

/**
 * Created by Dag on 05.10.2017.
 */
public abstract class TurtlesProcedure extends Procedure {

    public TurtlesProcedure(boolean isForever) {
        super(isForever);
    }

     void run() {
         World.getTurtles().forEach(this::runTurtle);
     }

    public abstract void runTurtle(Turtle turtle);
}
