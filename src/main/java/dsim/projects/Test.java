package dsim.projects;

import dsim.commands.ObserverProcedure;
import dsim.commands.Procedure;
import dsim.dictionary.Common;
import dsim.dictionary.Observer;
import dsim.project.Project;

import java.awt.*;

/**
 * Created by Dag on 13.11.2017.
 */
public class Test extends Project {
    @Override
    public void init() {
        Procedure go =
                new ObserverProcedure("Kjør", true) {
                    @Override
                    public void runObserver(Observer o, Common c) {
                        c.ask(c.turtles(), t -> {
                            t.right(c.random(10) - 5);
                            t.forward(1);
                            t.add("energy", -1);
                            if (t.pcolor() == Color.GREEN) {
                                t.pcolor(Color.BLACK);
                                t.add("energy", 10);
                            }

                            if (t.getInt("energy") > 10) {
                                t.color(Color.YELLOW);
                            } else {
                                t.color(Color.RED);
                            }
                        });

//                        c.ask(c.turtles(), t -> System.out.println(t));
                        o.tick();
                    }
                };

        Procedure setup =
                new ObserverProcedure("Init") {
                    @Override
                    public void runObserver(Observer o, Common c) {
                        o.createTurtles(100, t -> {
                            t.define("energy", 0);
                            t.setxy(c.randomPxcor(), c.randomPycor());
                            t.color(Color.RED);
                        });
                        c.ask(c.patches(), p -> p.pcolor(Color.GREEN));
                        o.resetTicks();
                    }
                };

        setSetup(setup);
        addProcedure(go);
    }
};
