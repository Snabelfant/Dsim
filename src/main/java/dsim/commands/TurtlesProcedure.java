package dsim.commands;

import dsim.dictionary.Common;
import dsim.dictionary.Turtle;
import dsim.model.World;

/**
 * Created by Dag on 05.10.2017.
 */
public abstract class TurtlesProcedure extends Procedure {

    public TurtlesProcedure(boolean isForever) {
        super(isForever);
    }

    @Override
     void run() {
//         System.out.println(World.getTurtles());
        World.getTurtles().forEach(t -> runTurtle((Turtle) t, World.getCommon()));
     }

    public abstract void runTurtle(Turtle turtle, Common common);
}
