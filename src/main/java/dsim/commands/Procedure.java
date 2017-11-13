package dsim.commands;

/**
 * Created by Dag on 30.09.2017.
 */
public abstract class Procedure {
    private String id;
    private boolean isForever;

    Procedure(String id, boolean isForever) {
        this.id = id;
        this.isForever = isForever;
    }

    abstract void run();

    public String getId() {
        return id;
    }

    public void execute() {
        run();
    }

    public boolean isForever() {
        return isForever;
    }
}
