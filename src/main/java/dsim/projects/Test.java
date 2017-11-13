package dsim.projects;

import dsim.commands.ObserverProcedure;
import dsim.dictionary.Common;
import dsim.dictionary.Observer;
import dsim.project.Project;

/**
 * Created by Dag on 13.11.2017.
 */
public class Test extends Project {
    @Override
    protected ObserverProcedure go() {
        return new ObserverProcedure("Kjør", true) {
            @Override
            public void runObserver(Observer o, Common c) {
                System.out.println("GO");
                c.ask(c.turtles(), t -> {
                    t.right(c.random(360));
                    t.forward(1);
                });
                o.tick();
            }
        };
    }

    @Override
    protected ObserverProcedure setup() {
        return new ObserverProcedure("Init") {
            @Override
            public void runObserver(Observer o, Common c) {
                o.createTurtles(100, t -> {
                    t.xcor(c.randomPxcor());
                });
                System.out.println("SETUP");
                o.resetTicks();
            }
        };
    }
}
