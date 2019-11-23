package com.group0536.puzzlemazing.models;

import android.graphics.Point;

public class BusyWorkerMap {

    private int width;
    private int height;
    private String[] Background;
    private Point initialWorkerPosition;
    private Point FlagPosition;
    private Point initialBoxPosition;
    private Point[] DeadPositions;
    private Point[] WallPositions;

    public BusyWorkerMap(String[] background, Point initialWorkerPosition, Point flagPosition, Point initialBoxPosition, Point[] deadPositions, Point[] wallPositions) {
        Background = background;
        this.initialWorkerPosition = initialWorkerPosition;
        FlagPosition = flagPosition;
        this.initialBoxPosition = initialBoxPosition;
        DeadPositions = deadPositions;
        WallPositions = wallPositions;
    }

    public String[] getBackground() {
        return Background;
    }

    public Point getInitialWorkerPosition() {
        return initialWorkerPosition;
    }

    public Point getFlagPosition() {
        return FlagPosition;
    }

    public Point getInitialBoxPosition() {
        return initialBoxPosition;
    }

    public Point[] getDeadPositions() {
        return DeadPositions;
    }

    public Point[] getWallPositions() {
        return WallPositions;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
