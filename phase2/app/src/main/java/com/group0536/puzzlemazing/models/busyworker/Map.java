package com.group0536.puzzlemazing.models.busyworker;

import android.graphics.Point;

import java.util.ArrayList;

public class Map {

    private int width;
    private int height;
    private String[] Background;
    private Point initialWorkerPosition;
    private Point FlagPosition;
    private Point initialBoxPosition;
    private ArrayList<Point> DeadPositions;
    private ArrayList<Point> WallPositions;

    public Map() {
    }

    /**
     * Get the background of this game
     *
     * @return background of this game
     */
    public String[] getBackground() {
        return Background;
    }

    /**
     * Get the initial position of the worker
     *
     * @return point of the initial position
     */
    public Point getInitialWorkerPosition() {
        return initialWorkerPosition;
    }

    /**
     * Get the flag position in the game
     *
     * @return point of the flag's location
     */
    public Point getFlagPosition() {
        return FlagPosition;
    }

    /**
     * Get the initial position of the box
     *
     * @return point of the initial box's location
     */
    public Point getInitialBoxPosition() {
        return initialBoxPosition;
    }

    /**
     * Get width of the game surface
     *
     * @return width of game surface
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get height of the game surface
     *
     * @return height of game surface
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the game surface's width
     *
     * @param width the width of the game surface
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Set the game surface's height
     *
     * @param height the height of the game surface
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Set the game surface's background view
     *
     * @param background the background of the game
     */
    public void setBackground(String[] background) {
        Background = background;
    }

    /**
     * Set the position of the flag
     *
     * @param flagPosition the position of the flag
     */
    public void setFlagPosition(Point flagPosition) {
        FlagPosition = flagPosition;
    }

    /**
     * Initialize the position of worker
     *
     * @param initialWorkerPosition the initial position of the worker
     */
    public void setInitialWorkerPosition(Point initialWorkerPosition) {
        this.initialWorkerPosition = initialWorkerPosition;
    }

    /**
     * Initialize the position of the box
     *
     * @param initialBoxPosition the initial position of the box
     */
    public void setInitialBoxPosition(Point initialBoxPosition) {
        this.initialBoxPosition = initialBoxPosition;
    }

    /**
     * Get positions where player will lose the game
     *
     * @return a list of dead positions of the game
     */
    public ArrayList<Point> getDeadPositions() {
        return DeadPositions;
    }

    /**
     * Set positions where player will lose the game
     *
     * @param deadPositions the list of dead positions of the box
     */
    public void setDeadPositions(ArrayList<Point> deadPositions) {
        DeadPositions = deadPositions;
    }

    /**
     * Get the all wall positions in the game
     *
     * @return a list of points of the wall's location
     */
    public ArrayList<Point> getWallPositions() {
        return WallPositions;
    }

    /**
     * Set positions of the walls
     *
     * @param wallPositions a list of wall positions
     */
    public void setWallPositions(ArrayList<Point> wallPositions) {
        WallPositions = wallPositions;
    }
}
