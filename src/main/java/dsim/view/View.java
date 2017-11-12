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
public class View extends JFrame {

    private static View view;
    private final int patchSize;
    private final int rows;
    private int cols;
    private JPanel panel;
    private PatchPanel[][] patchPanels;
    private Painter painter;
    private boolean isReady;

    public View(int patchSize, int cols, int rows) throws HeadlessException {
        this.patchSize = patchSize;
        this.rows = rows;
        this.cols = cols;
        painter = new Painter();
        init();
    }

    public static void create(int patchSize, int cols, int rows) {
        view = new View(patchSize, cols, rows);
        EventQueue.invokeLater(() -> {
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

    public static void repaintView() {
        if (view.isReady) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("REP");
                    view.panel.repaint();
                }
            });
        }
    }

    public static void startRepainter(int refreshFrequency) {
        view.painter.start(refreshFrequency);
    }

    private void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dsim");
        int width = patchSize * cols;
        int height = patchSize * rows;
        setSize(width, height);
        setLocationRelativeTo(null);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        patchPanels[col][row].paint(g);
                    }
                }
            }
        };

        panel.setPreferredSize(new Dimension(cols * patchSize, rows * patchSize));
        add(panel);

        patchPanels = new PatchPanel[cols][rows];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                PatchPanel patchPanel = new PatchPanel(World.getPatchByPhysicalIndex(col, row), col * patchSize, row * patchSize, patchSize);
                patchPanels[col][row] = patchPanel;
            }
        }

        isReady = false;
    }

    private class Painter {
        private final Runnable repainter = () -> View.repaintView();
        private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        public void start(int refreshFrequency) {
//            executorService.shutdownNow();
            executorService.scheduleAtFixedRate(painter.repainter, 0, 1000 / refreshFrequency, TimeUnit.MILLISECONDS);
        }
    }

}
