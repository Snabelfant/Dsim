package dsim;

import dsim.project.Project;
import dsim.projects.Test;
import dsim.projects.Utv;

/**
 * Created by Dag on 30.09.2017.
 */
public class Dsim {
    public static void main(String... params) throws InterruptedException {
        Project test = new Test();
        Project utv = new Utv();

//        test.run();
        utv.run();
    }

}
