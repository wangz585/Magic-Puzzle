package com.group0536.puzzlemazing.stores.games.busyworker;

import android.graphics.Point;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.games.busyworker.BusyWorkerActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.busyworker.Map;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.group0536.puzzlemazing.stores.games.GameStore;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a busy worker store responsible for handling the logics
 */
public class BusyWorkerStore extends GameStore implements BusyWorkerActions {

    private Map map;
    private static BusyWorkerStore instance;
    private int score;
    private Point currentWorkerPosition;
    private Point currentBoxPosition;
    private int difficultyLevel;
    private BusyWorkerInitController initController = new BusyWorkerInitController();
    private boolean isGameOver;

    private BusyWorkerStore(Dispatcher dispatcher) {
        super(dispatcher);
        user = GameStore.getInstance(dispatcher).getUser();
    }


    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new BusyWorkerChangeEvent();
    }

    @Subscribe
    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case INIT:
                break;
            case INITIALIZE_GAME:
                setDifficulty(action);
                setChallenge((int) action.getPayloadEntry("challenge"));
                initGame();
                break;
            case MOVE:
                move(action);
                updateScore();
                reactIfGameEnd();
                break;
        }
        postChange();
    }

    private void initGame() {
        isGameOver = false;
        initMap();
        initCurrentPosition();
        initScore();
    }

    private void setDifficulty(Action action) {
        difficultyLevel = (int) action.getPayloadEntry(KEY_DIFFICULTY);
    }

    private void initScore() {
        score = 100;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Update the score of the player
     * The number of maximum steps you can take is 100, if you take more than
     * 100 steps to finish the game, you will get zero point.
     */
    private void updateScore() {
        if (score > 0) {
            score--;
        }
    }

    /**
     * Initialize the game map of the given level
     */
    private void initMap() {
        HashMap<Object, String[]> levelData = initController.getLevelData(difficultyLevel);
        String[] rawMap = levelData.get("ContentView");
        this.map = new Map();
        initLabels(rawMap);
        initCircumference(rawMap);
    }

    /**
     * Initialize all the game elements in the map
     *
     * @param rawMap the string list representing
     *               <p>
     *               the design of the game map
     */
    private void initLabels(String[] rawMap) {
        ArrayList<Point> wallPositions = new ArrayList<>();
        ArrayList<Point> deadPositions = new ArrayList<>();
        for (int y = 0; y < rawMap.length; y++)
            for (int x = 0; x < rawMap[y].length(); x++) {
                initializeLabel(rawMap, wallPositions, deadPositions, y, x);
            }
        map.setDeadPositions(deadPositions);
        map.setWallPositions(wallPositions);
    }

    private void initializeLabel(String[] rawMap, ArrayList<Point> wallPositions,
                                 ArrayList<Point> deadPositions, int y, int x) {
        switch (rawMap[y].charAt(x)) {
            case 'W':
                Point wallPosition = new Point(x, y);
                wallPositions.add(wallPosition);
                break;
            case 'B':
                Point boxPosition = new Point(x, y);
                map.setInitialBoxPosition(boxPosition);
                break;
            case 'F':
                Point flagPosition = new Point(x, y);
                map.setFlagPosition(flagPosition);
                break;
            case 'M':
                Point workerPosition = new Point(x, y);
                map.setInitialWorkerPosition(workerPosition);
                break;
            case ' ':
                Point spacePosition = new Point(x, y);
                if (checkDeadPosition(rawMap, spacePosition)) {
                    deadPositions.add(spacePosition);
                }
        }
    }

    /**
     * Check whether a position is a dead position in the game map
     *
     * @param rawMap        the string list representing the design of the game map
     * @param spacePosition the position of a single point in the game map
     * @return whether it is a dead position
     */
    private boolean checkDeadPosition(String[] rawMap, Point spacePosition) {
        return (checkAboveLeft(rawMap, spacePosition) || checkBelowLeft(rawMap, spacePosition) ||
                checkAboveRight(rawMap, spacePosition) || checkBelowRight(rawMap, spacePosition));
    }

    /**
     * Check whether a point has walls close to it both on its right and below
     *
     * @param rawMap        the string list representing the design of the game map
     * @param spacePosition the point's position in game map
     * @return whether the point has walls close to it both on its right and below
     */
    private boolean checkBelowRight(String[] rawMap, Point spacePosition) {
        return rawMap[spacePosition.y].charAt(spacePosition.x + 1) == 'W' &&
                rawMap[spacePosition.y + 1].charAt(spacePosition.x) == 'W';
    }

    /**
     * Check whether a point has walls close to it both on its right and above
     *
     * @param rawMap        the string list representing the design of the game map
     * @param spacePosition the point's position in game map
     * @return whether the point has walls close to it both on its right and above
     */
    private boolean checkAboveRight(String[] rawMap, Point spacePosition) {
        return rawMap[spacePosition.y].charAt(spacePosition.x + 1) == 'W' &&
                rawMap[spacePosition.y - 1].charAt(spacePosition.x) == 'W';
    }

    /**
     * Check whether a point has walls close to it both on its left and below
     *
     * @param rawMap        the string list representing the design of the game map
     * @param spacePosition the point's position in game map
     * @return whether the point has walls close to it both on its left and below
     */
    private boolean checkBelowLeft(String[] rawMap, Point spacePosition) {
        return rawMap[spacePosition.y].charAt(spacePosition.x - 1) == 'W' &&
                rawMap[spacePosition.y + 1].charAt(spacePosition.x) == 'W';
    }

    /**
     * Check whether a position has walls close to it both on its left and above
     *
     * @param rawMap        the string list representing the design of the game map
     * @param spacePosition the point's position in game map
     * @return whether the point has walls close to it both on its left and above
     */
    private boolean checkAboveLeft(String[] rawMap, Point spacePosition) {
        return rawMap[spacePosition.y].charAt(spacePosition.x - 1) == 'W' &&
                rawMap[spacePosition.y - 1].charAt(spacePosition.x) == 'W';
    }

    /**
     * Set the current position of the worker and the box
     */
    private void initCurrentPosition() {
        currentWorkerPosition = new Point(map.getInitialWorkerPosition().x,
                map.getInitialWorkerPosition().y);
        currentBoxPosition = new Point(map.getInitialBoxPosition().x,
                map.getInitialBoxPosition().y);
    }

    /**
     * Set the current position of the worker and the box
     */
    private void initCircumference(String[] rawMap) {
        map.setWidth(rawMap[1].length());
        map.setHeight(rawMap.length);
    }

    /**
     * Set things to be done when game is finished
     */
    private void reactIfGameEnd() {
        if (isWon()) {
            user.setLevel(getChallenge() + 1);
        }
        isGameOver = isWon() || isLose();
    }

    /**
     * Check whether the player has win the game
     *
     * @return whether win the game or not
     */
    private boolean isWon() {
        return currentBoxPosition.equals(map.getFlagPosition());
    }

    /**
     * Check whether the player lose the game or get stuck in the dead situation
     *
     * @return whether lose the game or can not move
     */
    private boolean isLose() {
        for (Point deadPostion : map.getDeadPositions()) {
            if (deadPostion.equals(currentBoxPosition)){
                score = 0;
                return true;
            }
        }
        return false;
    }

    /**
     * Make the worker move and push the box at the same time(if possible)
     * when a user touch the screen
     *
     * @param action the action that triggers the move.
     */
    private void move(Action action) {
        Point touchPosition = (Point) action.getPayloadEntry(KEY_POSITION);
        String HorizontalDirection = checkHorizontalPosition(touchPosition);
        String VerticalDirection = checkVerticalPosition(touchPosition);
        switch (HorizontalDirection) {
            case "left":
                moveLeft();
                break;
            case "right":
                moveRight();
                break;
        }
        switch (VerticalDirection) {
            case "above":
                moveAbove();
                break;
            case "below":
                moveBelow();
                break;
        }
    }

    /**
     * Check how the worker move vertically
     *
     * @param touchPosition where the user touch the screen
     * @return a string indicate how the worker move vertically
     */
    private String checkVerticalPosition(Point touchPosition) {
        if (touchPosition.y < currentWorkerPosition.y) return "above";
        else if (touchPosition.y > currentWorkerPosition.y) return "below";
        else return "noVerticalMovement";
    }

    /**
     * Check how the worker move horizontally
     *
     * @param touchPosition where the user touch the screen
     * @return a string indicate how the worker move horizontally
     */
    private String checkHorizontalPosition(Point touchPosition) {
        if (touchPosition.x > currentWorkerPosition.x) return "right";
        else if (touchPosition.x < currentWorkerPosition.x) return "left";
        else return "noHorizontalMovement";
    }

    /**
     * Make the worker move one unit upwards
     * And if there is a box above him, the box is pushed one unit upwards
     */
    private void moveAbove() {
        if ((!WallAboveBox()) && BoxAboveWorker()) {
            currentWorkerPosition.y = currentWorkerPosition.y - 1;
            currentBoxPosition.y = currentBoxPosition.y - 1;
        } else if ((!WallAboveWorker()) && !(BoxAboveWorker()))
            currentWorkerPosition.y = currentWorkerPosition.y - 1;
    }

    /**
     * Make the worker move one unit downwards
     * And if there is a box below him, the box is pushed one unit downwards
     */
    private void moveBelow() {
        if ((!WallBelowBox()) && BoxBelowWorker()) {
            currentWorkerPosition.y = currentWorkerPosition.y + 1;
            currentBoxPosition.y = currentBoxPosition.y + 1;
        } else if ((!WallBelowWorker()) && !(BoxBelowWorker()))
            currentWorkerPosition.y = currentWorkerPosition.y + 1;
    }

    /**
     * Make the worker move one unit to the left
     * And if there is a box on his left, the box is pushed one unit to the left
     */
    private void moveLeft() {
        if ((!WallLeftToBox()) && BoxLeftToWorker()) {
            currentWorkerPosition.x = currentWorkerPosition.x - 1;
            currentBoxPosition.x = currentBoxPosition.x - 1;
        } else if ((!WallLeftToWorker()) && !(BoxLeftToWorker()))
            currentWorkerPosition.x = currentWorkerPosition.x - 1;
    }

    /**
     * Make the worker move one unit to the right
     * And if there is a box on his right, the box is pushed move one unit to the right
     */
    private void moveRight() {
        if ((!WallRightToBox()) && BoxRightToWorker()) {
            currentWorkerPosition.x = currentWorkerPosition.x + 1;
            currentBoxPosition.x = currentBoxPosition.x + 1;
        } else if ((!WallRightToWorker()) && !(BoxRightToWorker()))
            currentWorkerPosition.x = currentWorkerPosition.x + 1;
    }


    /**
     * Check whether there is a wall just above the worker
     *
     * @return whether there is a wall just above the worker
     */
    private boolean WallAboveWorker() {
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentWorkerPosition.y - 1 &&
                    wallPosition.x == currentWorkerPosition.x) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether there is a wall just below the worker
     *
     * @return whether there is a wall just below the worker
     */
    private boolean WallBelowWorker() {
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentWorkerPosition.y + 1 &&
                    wallPosition.x == currentWorkerPosition.x) {
                return true;
            }
        }
        return false;
    }


    /**
     * Check whether there is a wall just on the left side of the worker
     *
     * @return whether there is a wall just on the left side of the worker
     */
    private boolean WallLeftToWorker() {
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.x == currentWorkerPosition.x - 1 &&
                    wallPosition.y == currentWorkerPosition.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether there is a wall just on the right side of the worker
     *
     * @return whether there is a wall just on the right side of the worker
     */
    private boolean WallRightToWorker() {
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.x == currentWorkerPosition.x + 1 &&
                    wallPosition.y == currentWorkerPosition.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether there is a wall just above the box
     *
     * @return whether there is a wall just above the box
     */
    private boolean WallAboveBox() {
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentBoxPosition.y - 1 &&
                    wallPosition.x == currentBoxPosition.x) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether there is a wall just below the box
     *
     * @return whether there is a wall just below the box
     */
    private boolean WallBelowBox() {
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentBoxPosition.y + 1 &&
                    wallPosition.x == currentBoxPosition.x) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether there is a wall just on the left side of the box
     *
     * @return whether there is a wall just on the left side of the box
     */
    private boolean WallLeftToBox() {
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentBoxPosition.y &&
                    wallPosition.x == currentBoxPosition.x - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether there is a wall just on the right side of the box
     *
     * @return whether there is a wall just on the right side of the box
     */
    private boolean WallRightToBox() {
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentBoxPosition.y &&
                    wallPosition.x == currentBoxPosition.x + 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the box is attached above the worker
     *
     * @return boolean whether the box is attached above the worker
     */
    private boolean BoxAboveWorker() {
        return currentBoxPosition.x == currentWorkerPosition.x &&
                currentBoxPosition.y == currentWorkerPosition.y - 1;
    }

    /**
     * Check whether the box is attached below the worker
     *
     * @return boolean whether the box is attached below the worker
     */
    private boolean BoxBelowWorker() {
        return currentBoxPosition.x == currentWorkerPosition.x &&
                currentBoxPosition.y == currentWorkerPosition.y + 1;
    }

    /**
     * Check whether the box is attached to the right side the worker
     *
     * @return boolean whether the box is attached to the right the worker
     */
    private boolean BoxRightToWorker() {
        return currentBoxPosition.x == currentWorkerPosition.x + 1 &&
                currentBoxPosition.y == currentWorkerPosition.y;
    }

    /**
     * Check whether the box is attached to the left side of the worker
     *
     * @return boolean whether the box is attached to the left the worker
     */
    private boolean BoxLeftToWorker() {
        return currentBoxPosition.x == currentWorkerPosition.x - 1 &&
                currentBoxPosition.y == currentWorkerPosition.y;
    }

    /**
     * Get an instance of this store
     *
     * @param dispatcher the dispatcher associated
     * @return an instance of this store
     */
    public static BusyWorkerStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new BusyWorkerStore(dispatcher);
        }
        return instance;
    }

    /**
     * Get the map in store
     *
     * @return map in the store
     */
    public Map getMap() {
        return map;
    }

    /**
     * Get the current worker_worker position in store
     *
     * @return current worker_worker position in the store
     */
    public Point getCurrentWorkerPosition() {
        return currentWorkerPosition;
    }

    /**
     * Get current position of the box in store
     *
     * @return current box_position in the store
     */
    public Point getCurrentBoxPosition() {
        return currentBoxPosition;
    }

    /**
     * Get the current store of game BusyWorker
     *
     * @return score of the game
     */
    public int getScore() {
        return score;
    }
}