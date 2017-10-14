package dsim;

import dsim.commands.ObserverProcedure;
import dsim.commands.TurtlesProcedure;
import dsim.model.World;
import dsim.model.agent.Turtle;
import dsim.taskrunner.TaskRunner;
import dsim.view.ViewX;
import dsim.view.View2;

import java.awt.*;
import java.util.Random;

/**
 * Created by Dag on 30.09.2017.
 */
public class Dsim {

    public static void main(String... params) throws InterruptedException {
        int rows = 181;
        int cols = 171;
        World.create(cols,rows, true, true);
        View2.create(5, cols,rows);

        System.out.println("Starter");
        Random r = new Random();

        TaskRunner taskRunner = new TaskRunner(3);

        ObserverProcedure observerProcedure1 = new ObserverProcedure(true) {
            private int sleep = 10;
            @Override
            public void run() {
                int row = r.nextInt(rows);
                int col = r.nextInt(cols);
//            Thread.sleep(4000);
                World.setPatchColor(col, row, Color.YELLOW);
                World.tick();
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                }
                row = r.nextInt(rows);
                col = r.nextInt(cols);
                World.setPatchColor(col,row,Color.GREEN);
            }

            @Override
            public String getId() {
                return "O";
            }
        };
        taskRunner.submit(observerProcedure1);
       ObserverProcedure observerProcedure2 = new ObserverProcedure(true) {
            private int sleep = 100;
            @Override
            public void run() {
                int row = r.nextInt(rows);
                int col = r.nextInt(cols);
//            Thread.sleep(4000);
                World.setPatchColor(col,row, Color.MAGENTA);
                World.tick();
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                }
                row = r.nextInt(rows);
                col = r.nextInt(cols);
                World.setPatchColor(col,row, Color.CYAN);
            }

            @Override
            public String getId() {
                return "O";
            }
        };
        taskRunner.submit(observerProcedure2);

        World.createTurtle();
        TurtlesProcedure turtlesProcedure = new TurtlesProcedure(true) {
            double turn=0.001;
            @Override
            public String getId() {
                return "T";
            }

            @Override
            public void runTurtle(Turtle turtle) {
                turtle.jump(1);
                turtle.turnRight(turn);
                turn += 0.0001;
            }
        };

        taskRunner.submit(turtlesProcedure);
    }
}
