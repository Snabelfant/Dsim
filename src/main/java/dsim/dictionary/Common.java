package dsim.dictionary;

import dsim.model.CommonBase;

/**
 * Created by Dag on 12.11.2017.
 */
public class Common extends CommonBase {
    public Common(int seed) {
        super(seed);
    }

    public int random(int maxExclusive) {
        return nextRandomInt(0, maxExclusive);
    }

    public int countPatches(PatchPredicate predicate) {
        return super.countPatches(predicate);
    }

    public void askPatches(PatchCommand patchCommand) {
        super.askPatches(patchCommand);
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }
}
