package dsim.dictionary;

import java.util.function.Predicate;

/**
 * Created by Dag on 12.11.2017.
 */
public interface PatchPredicate extends Predicate<Patch> {
    PatchPredicate TRUE = p -> true;
}
