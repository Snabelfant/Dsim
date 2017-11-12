package dsim.view;

import dsim.model.PatchBase;

import java.awt.*;

/**
 * Created by Dag on 04.10.2017.
 */
class PatchPanel {
    private final int physY;
    private int physX;
    private int size;
    private PatchBase patch;

    PatchPanel(PatchBase patch, int physX, int physY, int size) {
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
