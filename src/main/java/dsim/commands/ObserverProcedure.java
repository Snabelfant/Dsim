package dsim.commands;

import dsim.dictionary.Common;
import dsim.dictionary.Observer;
import dsim.model.World;

/**
 * Created by Dag on 05.10.2017.
 */
public abstract class ObserverProcedure extends Procedure {

    public ObserverProcedure(String id) {
        super(id, false);
    }

    public ObserverProcedure(String id, boolean isForever) {
        super(id, isForever);
    }

    @Override
    void run() {
        runObserver(World.getObserver(), World.getCommon());
    }

    public abstract void runObserver(Observer o, Common c);
}
