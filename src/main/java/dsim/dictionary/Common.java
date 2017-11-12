package dsim.dictionary;

import dsim.util.Util;

/**
 * Created by Dag on 12.11.2017.
 */
public class Common {

    public static int random(int maxExclusive) {
        return Util.nextRandomInt(0, maxExclusive);
    }
}
