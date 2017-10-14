package dsim.view;

import dsim.model.World;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dag on 03.10.2017.
 */
public class ViewX extends JFrame {

    private static ViewX view;
    private final int patchSize;
    private final int rows;
    private int cols;
    private JPanel panel;
    private PatchPanel[][] patchPanels;
    private Painter painter;
    private boolean isReady;

    public ViewX(int patchSize, int rows, int cols) throws HeadlessException {
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
        view = new ViewX(patchSize, rows, cols);
        java.awt.EventQueue.invokeLater(() -> {
            view.setVisible(true);
            view.isReady = true;
        });

        while (!view.isReady) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    public void waitUntilReady() {
        while (!view.isReady) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
    public static void repaintView() {
        if (view.isReady) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    view.panel.repaint();
                }
            });
        }
    }

    public static void startRepainter(int refreshFrequency) {
        view.painter.start(refreshFrequency);
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
                PatchPanel patchPanel = new PatchPanel(World.getPatchByPhysicalIndex(row, col));
                patchPanels[row][col] = patchPanel;
                panel.add(patchPanel);
            }
        }

        isReady = false;
    }

    private class Painter {
        private final Runnable repainter = () -> ViewX.repaintView();
        private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        public void start(int refreshFrequency) {
            executorService.shutdownNow();
            executorService.scheduleAtFixedRate(painter.repainter, 0, 1000 / refreshFrequency, TimeUnit.MILLISECONDS);
        }
    }

}
