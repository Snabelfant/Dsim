package dsim.view;

import dsim.model.agent.Patch;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dag on 04.10.2017.
 */
class PatchPanel2  {
    private int physX;
    private final int physY;
    private int size;
    private Patch patch;

    PatchPanel2(Patch patch, int physX, int physY, int size) {
        this.patch = patch;
        this.physX = physX;
        this.physY = physY;
        this.size = size;
    }

    void paint(Graphics g) {
//        System.out.println("P" + physX + "/" + physY + "/" + patch.getDisplayColor());

        g.setColor(patch.getDisplayColor());
        g.fillRect(physX, physY, size, size);
    }
}
