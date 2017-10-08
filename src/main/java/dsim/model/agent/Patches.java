package dsim.model.agent;

import dsim.model.Position;

import java.awt.*;

/**
 * Created by Dag on 02.10.2017.
 */
public class Patches {
    double minX;
    double minY;
    double maxX;
    double maxY;
    private Patch[][] patches;
    private int minIndexX;
    private int minIndexY;
    private int maxIndexX;
    private int maxIndexY;
    private int rows;
    private int cols;
    private boolean wrapX;
    private boolean wrapY;

    public Patches(int rows, int cols, boolean wrapX, boolean wrapY) {
        this.rows = rows;
        this.cols = cols;
        this.wrapX = wrapX;
        this.wrapY = wrapY;
        patches = new Patch[rows][cols];
        minIndexX = -cols / 2;
        maxIndexX = cols / 2;
        minIndexY = -rows / 2;
        maxIndexY = rows / 2;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                patches[row][col] = new Patch(col + minIndexX, maxIndexY - row);
            }
        }

        minX = minIndexX - 0.5;
        maxX = maxIndexX + 0.5;
        minY = minIndexY - 0.5;
        maxY = maxIndexY + 0.5;
    }

    private double xJump(double distance, double headingInRads) {
        return distance * Math.sin(headingInRads);
    }

    private double yJump(double distance, double headingInRads) {
        return distance * Math.cos(headingInRads);
    }

    private double xJumpWithTopology(double fromX, double distance, double headingInRads) {
        double toX = xJump(distance, headingInRads) + fromX;

        if (toX < minX) {
            if (wrapX) {
                return maxX + (toX  - minX);
            } else {
                return minX;
            }
        } else {
            if (toX >= maxX) {
                if (wrapX) {
                    return minX - (toX - maxX);
                } else {
                    return maxX;
                }
            } else {
                return toX;
            }
        }
    }

    private double yJumpWithTopology(double fromY, double distance, double headingInRads) {
        double toY = yJump(distance, headingInRads) + fromY;

        if (toY < minY) {
            if (wrapY) {
                return maxY - (toY + minY);
            } else {
                return minY;
            }
        } else {
            if (toY >= maxY) {
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

    public Position jump(Position from, double distance, double headingInRads) {
        double toX = xJumpWithTopology(from.getX(), distance, headingInRads);
        double toY = yJumpWithTopology(from.getY(), distance, headingInRads);
        return new Position(toX, toY);
    }

    public Color getDisplayColor(int physicalRow, int physicalCol) {
        return patches[physicalRow][physicalCol].getDisplayColor();
    }


    public Patch getPatchByPhysicalIndex(int physicalRow, int physicalCol) {
        return patches[physicalRow][physicalCol];
    }
}
