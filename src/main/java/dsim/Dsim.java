package dsim;

import dsim.commands.ObserverProcedure;
import dsim.commands.TurtlesProcedure;
import dsim.dictionary.Common;
import dsim.dictionary.Observer;
import dsim.dictionary.Turtle;
import dsim.model.World;
import dsim.taskrunner.TaskRunner;
import dsim.util.Util;
import dsim.view.View;

import java.awt.*;

/**
 * Created by Dag on 30.09.2017.
 */
public class Dsim {

    public static void main(String... params) throws InterruptedException {
        int rows = 161;
        int cols = 157;
        World.create(cols, rows, true, true);
        View.create(6, cols, rows);

        System.out.println("Starter");

        TaskRunner taskRunner = new TaskRunner(3);

        taskRunner.submit(new ObserverProcedure() {
            @Override
            public void run() {
                Observer.createTurtles(10, t -> t.xcor(Common.random(cols / 2)));
            }

            @Override
            public String getId() {
                return "O1";
            }
        });

        ObserverProcedure observerProcedure1 = new ObserverProcedure(true) {
            private int sleep = 10;

            @Override
            public void run() {
                int row = Util.nextRandomInt(0, rows);
                int col = Util.nextRandomInt(0, cols);
//            Thread.sleep(4000);
                World.setPatchColor(col, row, Color.YELLOW);
                World.tick();
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                }
                row = Util.nextRandomInt(0, rows);
                col = Util.nextRandomInt(0, cols);
                World.setPatchColor(col, row, Color.GREEN);
            }

            @Override
            public String getId() {
                return "O";
            }
        };
//        taskRunner.submit(observerProcedure1);
        ObserverProcedure observerProcedure2 = new ObserverProcedure(true) {
            private int sleep = 100;

            @Override
            public void run() {
                int row = Util.nextRandomInt(0, rows);
                int col = Util.nextRandomInt(0, cols);
                if (World.getPatchColor(col, row).equals(Color.BLACK) || World.getPatchColor(col, row).equals(Color.GRAY)) {
                    World.setPatchColor(col, row, Color.MAGENTA);
                }

                row = Util.nextRandomInt(0, rows);
                col = Util.nextRandomInt(0, cols);
                if (World.getPatchColor(col, row).equals(Color.BLACK) || World.getPatchColor(col, row).equals(Color.GRAY)) {
                    World.setPatchColor(col, row, Color.CYAN);
                }
            }

            @Override
            public String getId() {
                return "O";
            }
        };
//        taskRunner.submit(observerProcedure2);

        TurtlesProcedure turtlesProcedure = new TurtlesProcedure(true) {
            double turn = 0.001;

            @Override
            public String getId() {
                return "T";
            }

            @Override
            public void runTurtle(Turtle turtle) {
//                turtle.jump(1);
                turtle.forward(1.4);

                if (turtle.pcolor().equals(Color.MAGENTA)) {
                    turtle.stamp(Color.YELLOW);
                } else {
                    turtle.stamp(Color.GRAY);
                }
                turtle.right(turn);
                turn += 0.01;

                if (turn > 10) {
                    turn = 0.001;
                }
//                System.out.println(turtle);
//                World.tick();
            }
        };

        taskRunner.submit(turtlesProcedure);


        View.startRepainter(30);
    }
}
