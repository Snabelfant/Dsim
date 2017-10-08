package dsim.commands;

import dsim.model.World;
import dsim.model.agent.Turtle;

/**
 * Created by Dag on 30.09.2017.
 */
public abstract class Procedure {
    private boolean isForever;

    Procedure(boolean isForever) {
        this.isForever = isForever;
    }

    abstract void run();

    public void execute() {
        System.out.println("Kjører...");
        run();
    }

    public boolean isForever() {
        return isForever;
    }
}
