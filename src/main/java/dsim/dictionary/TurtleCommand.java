package dsim.dictionary;

import java.util.function.Consumer;

/**
 * Created by Dag on 12.11.2017.
 */
public interface TurtleCommand extends Consumer<Turtle> {
    TurtleCommand NONE = t -> {
    };
}
