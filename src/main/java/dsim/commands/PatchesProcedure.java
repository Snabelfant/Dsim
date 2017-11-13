package dsim.commands;

import dsim.dictionary.Common;
import dsim.dictionary.Patch;
import dsim.model.World;

/**
 * Created by Dag on 05.10.2017.
 */
public abstract class PatchesProcedure extends Procedure {

    public PatchesProcedure(String id) {
        super(id, false);
    }

    public PatchesProcedure(String id, boolean isForever) {
        super(id, isForever);
    }

    @Override
    void run() {
        World.patches().asList().forEach(p -> runPatch(p, World.getCommon()));
    }

    public abstract void runPatch(Patch o, Common c);
}
