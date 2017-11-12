package dsim.commands;

/**
 * Created by Dag on 30.09.2017.
 */
public abstract class Procedure {
    private boolean isForever;

    Procedure(boolean isForever) {
        this.isForever = isForever;
    }

    abstract void run();

    public abstract String getId();

    public void execute() {
        run();
    }

    public boolean isForever() {
        return isForever;
    }
}
