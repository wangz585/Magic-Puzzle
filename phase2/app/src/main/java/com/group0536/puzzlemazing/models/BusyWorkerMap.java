package com.group0536.puzzlemazing.models;

import android.graphics.Point;

import java.util.ArrayList;

public class BusyWorkerMap {

    private int width;
    private int height;
    private String[] Background;
    private Point initialWorkerPosition;
    private Point FlagPosition;
    private Point initialBoxPosition;
    private ArrayList<Point> DeadPositions;
    private ArrayList<Point> WallPositions;

    public BusyWorkerMap() {
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBackground(String[] background) {
        Background = background;
    }

    public void setInitialWorkerPosition(Point initialWorkerPosition) {
        this.initialWorkerPosition = initialWorkerPosition;
    }

    public void setFlagPosition(Point flagPosition) {
        FlagPosition = flagPosition;
    }

    public void setInitialBoxPosition(Point initialBoxPosition) {
        this.initialBoxPosition = initialBoxPosition;
    }

    public ArrayList<Point> getDeadPositions() {
        return DeadPositions;
    }

    public void setDeadPositions(ArrayList<Point> deadPositions) {
        DeadPositions = deadPositions;
    }

    public ArrayList<Point> getWallPositions() {
        return WallPositions;
    }

    public void setWallPositions(ArrayList<Point> wallPositions) {
        WallPositions = wallPositions;
    }
}
