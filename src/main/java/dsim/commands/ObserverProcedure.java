package dsim.commands;

/**
 * Created by Dag on 05.10.2017.
 */
public abstract class ObserverProcedure extends Procedure {

    public ObserverProcedure() {
        super(false);
    }
    public ObserverProcedure(boolean isForever) {
        super(isForever);
    }

    public abstract void run();
}
