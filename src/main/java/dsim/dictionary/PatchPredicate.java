package dsim.dictionary;

/**
 * Created by Dag on 12.11.2017.
 */
public interface PatchPredicate {
    PatchPredicate TRUE = (p, c) -> true;


    boolean evaluate(Patch p, Common c);
}
