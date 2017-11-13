package dsim.projects;

import dsim.commands.ObserverProcedure;
import dsim.commands.PatchesProcedure;
import dsim.dictionary.Common;
import dsim.dictionary.Observer;
import dsim.dictionary.Patch;
import dsim.model.Colors;
import dsim.project.Project;

import java.awt.*;

/**
 * Created by Dag on 13.11.2017.
 */
public class Utv extends Project {

    public Utv() {
        addProcedure(
                new ObserverProcedure("Tell sorte", true) {
                    @Override
                    public void runObserver(Observer o, Common c) {
                        System.out.println("Sort=" + c.count(c.patches(), p -> p.pcolor() == Color.BLACK));
                        c.sleep(3000);
                    }
                }
        );

        addProcedure(
                new PatchesProcedure("Tegn ramme", true) {
                    @Override
                    public void runPatch(Patch p, Common c) {
                        Color color = Colors.randomColorNotBlack();
                        if (p.pycor() == c.maxPycor() || p.pycor() == c.minPycor() || p.pxcor() == c.maxPxcor() || p.pxcor() == c.minPxcor()) {
                            p.pcolor(color);
                            c.sleep(5);
                        }
                    }
                }
        );

        addProcedure(
                new ObserverProcedure("Farg", true) {
                    @Override
                    public void runObserver(Observer o, Common c) {
                        int x = c.randomPxcor();
                        int y = c.randomPycor();
                        Patch patch = c.patch(x, y);

                        if (patch.pcolor().equals(Color.BLACK) || patch.pcolor().equals(Color.GRAY)) {
                            patch.pcolor(Color.CYAN);
                        }

                        x = c.randomPxcor();
                        y = c.randomPycor();
                        patch = c.patch(x, y);

                        if (patch.pcolor().equals(Color.BLACK) || patch.pcolor().equals(Color.GRAY)) {
                            patch.pcolor(Color.MAGENTA);
                        }
                    }

                }
        );
    }

    @Override
    protected ObserverProcedure go() {
        return new ObserverProcedure("Kjør", true) {
            public double turn = 0;

            @Override
            public void runObserver(Observer o, Common c) {
                c.ask(c.turtles(),
                        t -> {
                            t.forward(0.4);
                            t.right(turn);
                            turn += 0.01;

                            if (turn > 10) {
                                turn = 0.001;
                            }
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
                o.createTurtles(3, t -> t.xcor(c.randomPxcor()));
                System.out.println("SETUP");
                o.resetTicks();
            }
        };
    }
}
