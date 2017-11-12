package dsim;

import dsim.commands.ObserverProcedure;
import dsim.commands.TurtlesProcedure;
import dsim.dictionary.Common;
import dsim.dictionary.Observer;
import dsim.dictionary.Turtle;
import dsim.model.Colors;
import dsim.model.World;
import dsim.taskrunner.TaskRunner;
import dsim.view.View;

import java.awt.*;

/**
 * Created by Dag on 30.09.2017.
 */
public class Dsim {

    public static void main(String... params) throws InterruptedException {
        int rows = 151;
        int cols = 151;
        World.create(cols, rows, true, true);
        View.create(6, cols, rows);

        TaskRunner taskRunner = new TaskRunner(3);

        taskRunner.submit(new ObserverProcedure() {
            @Override
            public void runObserver(Observer o, Common c) {
                o.createTurtles(3, t -> t.xcor(c.random(cols / 2)));
            }

            @Override
            public String getId() {
                return "O1";
            }
        });

        taskRunner.submit(new ObserverProcedure(true) {
            @Override
            public void runObserver(Observer o, Common c) {
                System.out.println("Sort=" + c.countPatches((p, c1) -> p.pcolor() == Color.BLACK));
                c.sleep(3000);
            }

            @Override
            public String getId() {
                return "O88";
            }
        });

        taskRunner.submit(new ObserverProcedure(true) {
            @Override
            public void runObserver(Observer o, Common c) {
                Color color = Colors.randomColorNotBlack();
                c.askPatches((p, c1) -> p.pcolor(color));
                c.sleep(5000);
            }

            @Override
            public String getId() {
                return "O88";
            }
        });

        ObserverProcedure observerProcedure2 = new ObserverProcedure(true) {
            private int sleep = 100;

            @Override
            public void runObserver(Observer o, Common c) {
                int row = c.random(rows);
                int col = c.random(cols);
                    World.setPatchColor(col, row, Color.MAGENTA);

                row = c.random(rows);
                col = c.random(cols);
                if (World.getPatchColor(col, row).equals(Color.BLACK) || World.getPatchColor(col, row).equals(Color.GRAY)) {
                    World.setPatchColor(col, row, Color.CYAN);
                }
            }

            @Override
            public String getId() {
                return "O";
            }
        };
        taskRunner.submit(observerProcedure2);

        TurtlesProcedure turtlesProcedure = new TurtlesProcedure(true) {
            double turn = 0.001;

            @Override
            public String getId() {
                return "T";
            }

            @Override
            public void runTurtle(Turtle t, Common c) {
//                turtle.jump(1);
                t.forward(0.4);

//                if (turtle.pcolor().equals(Color.MAGENTA)) {
//                    turtle.stamp(Color.YELLOW);
//                } else {
//                    turtle.stamp(Color.GRAY);
//                }
                t.right(turn);
                turn += 0.01;

                if (turn > 10) {
                    turn = 0.001;
                }
//                World.tick();
            }
        };

        taskRunner.submit(turtlesProcedure);


        View.startRepainter(30);
    }
}
