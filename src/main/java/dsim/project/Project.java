package dsim.project;

import dsim.commands.ObserverProcedure;
import dsim.commands.Procedure;
import dsim.model.World;
import dsim.taskrunner.TaskRunner;
import dsim.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dag on 13.11.2017.
 */
public abstract class Project {
    private int rows = 151;
    private int cols = 151;
    private TaskRunner taskRunner;
    private List<Procedure> procedures;

    public Project() {
        World.create(cols, rows, true, true);
        View.create(6, cols, rows);
        taskRunner = new TaskRunner(10);
        procedures = new ArrayList<>();
    }

    public void run() {
        taskRunner.submit(setup());
        taskRunner.submit(go());

        for (Procedure procedure : procedures) {
            taskRunner.submit(procedure);
        }
    }

    protected abstract ObserverProcedure go();

    protected abstract ObserverProcedure setup();

    protected void addProcedure(Procedure procedure) {
        procedures.add(procedure);
    }

}
