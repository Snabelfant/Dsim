package dsim.commands;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dsim.model.World;
import dsim.model.agent.Turtle;

/**
 * Created by Dag on 05.10.2017.
 */
public abstract class ObserverProcedure extends Procedure {

    public ObserverProcedure(boolean isForever) {
        super(isForever);
    }

    public abstract void run();
}
