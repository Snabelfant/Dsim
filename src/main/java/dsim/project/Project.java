package dsim.project;

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
    private int rows = 51;
    private int cols = 51;
    private TaskRunner taskRunner;
    private Procedure setup;
    private List<Procedure> procedures;

    public Project() {
        World.create(cols, rows, true, true);
        View.create(8, cols, rows);
        taskRunner = new TaskRunner(10);
        procedures = new ArrayList<>();
    }

    public void run() {
        init();
        taskRunner.submit(setup);

        for (Procedure procedure : procedures) {
            taskRunner.submit(procedure);
        }
    }

    protected void addProcedure(Procedure procedure) {
        procedures.add(procedure);
    }

    public void setSetup(Procedure setup) {
        this.setup = setup;
    }

    public abstract void init();
}
