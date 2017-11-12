package dsim.model;

import dsim.dictionary.Patch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dag on 02.10.2017.
 */
public class Patches {
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private Patch[][] patches;
    private List<Patch> patchesAsList;
    private int minIndexX;
    private int minIndexY;
    private int maxIndexX;
    private int maxIndexY;
    private int rows;
    private int cols;
    private boolean wrapX;
    private boolean wrapY;
    public Patches(int cols, int rows, boolean wrapX, boolean wrapY) {
        this.rows = rows;
        this.cols = cols;
        this.wrapX = wrapX;
        this.wrapY = wrapY;
        patches = new Patch[cols][rows];
        minIndexX = -cols / 2;
        maxIndexX = cols / 2;
        minIndexY = -rows / 2;
        maxIndexY = rows / 2;

        System.out.println(minIndexX + " " + maxIndexX + " " + minIndexY + " " + maxIndexY);
        patchesAsList = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Patch patch = new Patch(col + minIndexX, maxIndexY - row);
                patches[col][row] = patch;
                patchesAsList.add(patch);
            }
        }

        minX = minIndexX - 0.5;
        maxX = maxIndexX + 0.5;
        minY = minIndexY - 0.5;
        maxY = maxIndexY + 0.5;
    }

    List<Patch> getPatchesAsList() {
        return patchesAsList;
    }

    protected double getMinX() {
        return minX;
    }

    protected double getMinY() {
        return minY;
    }

    protected double getMaxX() {
        return maxX;
    }

    protected double getMaxY() {
        return maxY;
    }

    protected int getMinIndexX() {
        return minIndexX;
    }

    protected int getMinIndexY() {
        return minIndexY;
    }

    protected int getMaxIndexX() {
        return maxIndexX;
    }

    protected int getMaxIndexY() {
        return maxIndexY;
    }

    private double xJump(double distance, double headingInRads) {
        return distance * Math.sin(headingInRads);
    }

    private double yJump(double distance, double headingInRads) {
        return distance * Math.cos(headingInRads);
    }

    private double xJumpWithTopology(double fromX, double distance, double headingInRads) {
        double toX = (xJump(distance, headingInRads) + fromX) % cols;

        if (toX < minX) {
            if (wrapX) {
                return maxX - (-toX + minX);
            } else {
                return minX;
            }
        } else {
            if (toX > maxX) {
                if (wrapX) {
                    return minX + (toX - maxX);
                } else {
                    return maxX;
                }
            } else {
                return toX;
            }
        }
    }

    private double yJumpWithTopology(double fromY, double distance, double headingInRads) {
        double toY = (yJump(distance, headingInRads) + fromY) % rows;

        if (toY < minY) {
            if (wrapY) {
                return maxY - (-toY + minY);
            } else {
                return minY;
            }
        } else {
            if (toY > maxY) {
                if (wrapY) {
                    return minY + (toY - maxY);
                } else {
                    return maxY;
                }
            } else {
                return toY;
            }
        }
    }

    Patch getPatchAt(Position position) {
        int patchX = (int) (Math.round(position.getX()) - minIndexX);
        int patchY = (int) (maxIndexY - Math.round(position.getY()));
        return patches[patchX][patchY];
    }

    Position jump(Position from, double distance, double headingInRads) {
        double toX = xJumpWithTopology(from.getX(), distance, headingInRads);
        double toY = yJumpWithTopology(from.getY(), distance, headingInRads);
        return new Position(toX, toY);
    }

    Color getDisplayColor(int physicalCol, int physicalRow) {
        return patches[physicalCol][physicalRow].getDisplayColor();
    }

    Patch getPatchByPhysicalIndex(int physicalCol, int physicalRow) {
        return patches[physicalCol][physicalRow];
    }

}
