package dsim.dictionary;

/**
 * Created by Dag on 12.11.2017.
 */
public interface TurtleCommand {
    TurtleCommand NONE = turtle -> {
        System.out.println("NONE");
    };

    void run(Turtle turtle);
}
