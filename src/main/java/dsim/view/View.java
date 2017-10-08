package dsim.view;

import dsim.model.World;
import dsim.model.agent.Patches;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dag on 03.10.2017.
 */
public class View extends JFrame {

    private static View view;
    private final int patchSize;
    private final int rows;
    private int cols;
    private JPanel panel;
    private PatchPanel[][] patchPanels;
    private Painter painter;

    public View(int patchSize, int rows, int cols) throws HeadlessException {
        this.patchSize = patchSize;
        this.rows = rows;
        this.cols = cols;
        painter = new Painter();
        init();
    }

    public static void setColor(int row, int col, Color color) {
        view.patchPanels[row][col].setBackground(color);
    }

    public static void create(int patchSize, int rows, int cols) {
        view = new View(patchSize, rows, cols);
        java.awt.EventQueue.invokeLater(() -> view.setVisible(true));
    }

    private void init() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dsim");
        int width = patchSize * cols;
        int height = patchSize * rows;
        setSize(width, height);
        setLocationRelativeTo(null);
         panel = new JPanel(new GridLayout(rows, cols));
        add(panel);

        patchPanels = new PatchPanel[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                PatchPanel patchPanel = new PatchPanel( World.getPatchAt(row,col));
                patchPanels[row][col] = patchPanel;
                panel.add(patchPanel);
            }
        }
    }

    public static void repaintView() {
        System.out.println("refresh " + System.currentTimeMillis());
        SwingUtilities.invokeLater(() -> view.panel.repaint());
    }

    public static void startRepainter(int refreshFrequency) {
        view.painter.start(refreshFrequency);
    }
    private class Painter {
        private  final Runnable repainter = () -> View.repaintView();
        private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        public void start(int refreshFrequency) {
            executorService.shutdownNow();
            executorService.scheduleAtFixedRate(painter.repainter, 0, 1000 / refreshFrequency, TimeUnit.MILLISECONDS);
        }
    }

}
