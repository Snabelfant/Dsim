package dsim.view;

import dsim.model.agent.Patch;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dag on 04.10.2017.
 */
class PatchPanel extends JPanel {
    private static  int x = 0;
    private Patch patch;

    PatchPanel(Patch patch) {
        this.patch = patch;
    }

     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
         setBackground(patch.getDisplayColor());
    }


}
