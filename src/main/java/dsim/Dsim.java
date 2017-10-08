package dsim;

import dsim.commands.ObserverProcedure;
import dsim.model.World;
import dsim.taskrunner.TaskRunner;
import dsim.view.View;

import java.awt.*;
import java.util.Random;

/**
 * Created by Dag on 30.09.2017.
 */
public class Dsim {

    public static void main(String... params) throws InterruptedException {
        int rows = 13;
        int cols = 27;
        World.create(rows, cols, true, true);
        View.create(40, rows, cols);
        Random r = new Random();
        int row = r.nextInt(rows);
        int col = r.nextInt(cols);

        ObserverProcedure observerProcedure = new ObserverProcedure(true) {
            private int sleep = 1;
            @Override
            public void run() {
                int row = r.nextInt(rows);
                int col = r.nextInt(cols);
//            Thread.sleep(4000);
                World.setPatchColor(row, col, Color.YELLOW);
                World.tick();
                try {
                    Thread.sleep(sleep++);
                } catch (InterruptedException e) {
                }
                row = r.nextInt(rows);
                col = r.nextInt(cols);
                World.setPatchColor(row, col, Color.GREEN);
            }
        };
        TaskRunner taskRunner = new TaskRunner(1);
        taskRunner.submit(observerProcedure);
    }
}
