package dsim.dictionary;

/**
 * Created by Dag on 12.11.2017.
 */
public interface PatchCommand {
    PatchCommand NONE = (p, c) -> {
    };

    void run(Patch p, Common c);
}
