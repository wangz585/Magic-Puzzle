package com.group0536.puzzlemazing.stores.busyworker;
import android.graphics.Point;
import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.busyworker.BusyWorkerActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.BusyWorkerMap;
import com.group0536.puzzlemazing.models.BusyWorkerRawMaps;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class BusyWorkerStore extends Store implements BusyWorkerActions {

    private BusyWorkerMap map;
    private static BusyWorkerStore instance;
    private int score;
    private Point currentWorkerPosition;
    private Point currentBoxPosition;
    private Timer timer;
    private int timeUsed;
    private BusyWorkerInitController initController = new BusyWorkerInitController();

    protected BusyWorkerStore(Dispatcher dispatcher) {
        super(dispatcher);
    }


    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new BusyWorkerChangeEvent();
    }

    @Subscribe
    @Override
    public void onAction(Action action) {
        switch(action.getType()){
            case MOVE:
                Point touchPosition = (Point)action.getPayloadEntry("position");
                move(touchPosition);
                updateScore();
                postChange();
                break;
            case INIT_MAP:
                int level = (int)action.getPayloadEntry("level");
                initMap(level);
                initCurrentPosition();
                initTimer();
                initScore();
                postChange();
                break;
        }
    }

    private void initScore() {
        score = 100;
    }


    private void updateScore() {
        score = score - 1;

    }

    private void initTimer(){
        timer = new Timer();
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                timeUsed = timeUsed + 1;
                postChange();
            }
        };
        timer.scheduleAtFixedRate(t,1000,1000);
    }

    private void initMap(int level) {
        HashMap<Object, String[]> levelData = initController.getLevelData(level);
        String[] rawMap = (String[])levelData.get("ContentView");
        this.map = new BusyWorkerMap();
        initLabels(rawMap);
        initCircumference(rawMap);
    }

    private void initLabels(String[] rawMap){
        ArrayList<Point> wallPositions = new ArrayList<>();
        ArrayList<Point> deadPositions = new ArrayList<>();
        for (int y = 0; y < rawMap.length; y++)
            for (int x = 0; x < rawMap[y].length(); x++){
                switch(rawMap[y].charAt(x)){
                    case 'W':
                        Point wallPosition = new Point(x,y);
                        wallPositions.add(wallPosition);
                        break;
                    case 'B':
                        Point boxPosition = new Point(x,y);
                        map.setInitialBoxPosition(boxPosition);
                        break;
                    case 'F':
                        Point flagPosition = new Point(x,y);
                        map.setFlagPosition(flagPosition);
                        break;
                    case 'M':
                        Point workerPosition = new Point(x,y);
                        map.setInitialWorkerPosition(workerPosition);
                        break;
                    case ' ':
                        Point spacePosition = new Point(x,y);
                        if (checkDeadPosition(rawMap, spacePosition)){
                            deadPositions.add(spacePosition);
                        }
                }
            }
        map.setDeadPositions(deadPositions);
        map.setWallPositions(wallPositions);
    }

    private boolean checkDeadPosition(String[] rawMap, Point spacePosition){
        return (checkAboveLeft(rawMap,spacePosition) || checkBelowLeft(rawMap,spacePosition) ||
                checkAboveRight(rawMap,spacePosition) || checkBelowRight(rawMap,spacePosition));
    }

    private boolean checkBelowRight(String[] rawMap, Point spacePosition) {
        return rawMap[spacePosition.y].charAt(spacePosition.x+1) == 'W' &&
                rawMap[spacePosition.y+1].charAt(spacePosition.x) == 'W';
    }

    private boolean checkAboveRight(String[] rawMap, Point spacePosition) {
        return rawMap[spacePosition.y].charAt(spacePosition.x+1) == 'W' &&
                rawMap[spacePosition.y-1].charAt(spacePosition.x) == 'W';
    }

    private boolean checkBelowLeft(String[] rawMap, Point spacePosition) {
        return rawMap[spacePosition.y].charAt(spacePosition.x-1) == 'W' &&
                rawMap[spacePosition.y+1].charAt(spacePosition.x) == 'W';
    }

    private boolean checkAboveLeft(String[] rawMap, Point spacePosition) {
        return rawMap[spacePosition.y].charAt(spacePosition.x-1) == 'W' &&
                rawMap[spacePosition.y-1].charAt(spacePosition.x) == 'W';
    }

    private void initCurrentPosition(){
        currentWorkerPosition = new Point(map.getInitialWorkerPosition().x,map.getInitialWorkerPosition().y);
        currentBoxPosition = new Point(map.getInitialBoxPosition().x,map.getInitialBoxPosition().y);
    }

    private void initCircumference(String[] rawMap){
        map.setWidth(rawMap[1].length());
        map.setHeight(rawMap.length);
    }

    public boolean checkWin() {
        return currentBoxPosition.equals(map.getFlagPosition());
    }

    public boolean checkLose() {
        for (Point deadPostion : map.getDeadPositions()){
            if (deadPostion.equals(currentBoxPosition)) return true;
        }
        return false;
    }

    private void move(Point touchPosition) {
        String HorizontalDirection = checkHorizontalPosition(touchPosition);
        String VerticalDirection = checkVerticalPosition(touchPosition);
        switch (HorizontalDirection) {
            case "left": moveLeft();
            break;
            case "right": moveRight();
            break;
        }
        switch (VerticalDirection) {
            case "above": moveAbove();
            break;
            case "below": moveBelow();
            break;
        }
    }

    private String checkVerticalPosition(Point touchPosition) {
        if (touchPosition.y < currentWorkerPosition.y) return "above";
        else if (touchPosition.y > currentWorkerPosition.y) return "below";
        else return "noVerticalMovement";
    }

    private String checkHorizontalPosition(Point touchPosition) {
        if (touchPosition.x > currentWorkerPosition.x) return "right";
        else if (touchPosition.x < currentWorkerPosition.x) return "left";
        else return "noHorizontalMovement";
    }

    private void moveAbove() {
        if ((!WallAboveBox()) && BoxAboveWorker()) {
            currentWorkerPosition.y = currentWorkerPosition.y - 1;
            currentBoxPosition.y = currentBoxPosition.y - 1;
        }
        else if ((!WallAboveWorker()) && !(BoxAboveWorker())) currentWorkerPosition.y = currentWorkerPosition.y - 1;
    }

    private void moveBelow() {
        if ((!WallBelowBox()) && BoxBelowWorker()) {
            currentWorkerPosition.y = currentWorkerPosition.y + 1;
            currentBoxPosition.y = currentBoxPosition.y + 1;
        }
        else if ((!WallBelowWorker()) && !(BoxBelowWorker())) currentWorkerPosition.y = currentWorkerPosition.y + 1;
    }

    private void moveLeft() {
        if ((!WallLeftToBox()) && BoxLeftToWorker()) {
            currentWorkerPosition.x = currentWorkerPosition.x - 1;
            currentBoxPosition.x = currentBoxPosition.x - 1;
        }
        else if ((!WallLeftToWorker()) && !(BoxLeftToWorker())) currentWorkerPosition.x = currentWorkerPosition.x - 1;
    }

    private void moveRight() {
        if ((!WallRightToBox()) && BoxRightToWorker()) {
            currentWorkerPosition.x = currentWorkerPosition.x + 1;
            currentBoxPosition.x = currentBoxPosition.x + 1;
        }
        else if ((!WallRightToWorker()) && !(BoxRightToWorker())) currentWorkerPosition.x = currentWorkerPosition.x + 1;
    }


    private boolean WallAboveWorker(){
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentWorkerPosition.y - 1 && wallPosition.x == currentWorkerPosition.x){
                return true;
            }
        }
        return false;
    }

    private boolean WallLeftToWorker(){
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.x == currentWorkerPosition.x - 1 && wallPosition.y == currentWorkerPosition.y){
                return true;
            }
        }
        return false;
    }

    private boolean WallRightToWorker(){
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.x == currentWorkerPosition.x + 1 && wallPosition.y == currentWorkerPosition.y){
                return true;
            }
        }
        return false;
    }

    private boolean WallAboveBox(){
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentBoxPosition.y - 1 && wallPosition.x == currentBoxPosition.x){
                return true;
            }
        }
        return false;
    }

    private boolean WallBelowBox(){
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentBoxPosition.y + 1 && wallPosition.x == currentBoxPosition.x){
                return true;
            }
        }
        return false;
    }

    private boolean WallLeftToBox(){
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentBoxPosition.y && wallPosition.x == currentBoxPosition.x - 1){
                return true;
            }
        }
        return false;
    }

    private boolean WallRightToBox(){
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentBoxPosition.y && wallPosition.x == currentBoxPosition.x + 1){
                return true;
            }
        }
        return false;
    }

    private boolean WallBelowWorker(){
        for (Point wallPosition : map.getWallPositions()) {
            if (wallPosition.y == currentWorkerPosition.y + 1 && wallPosition.x == currentWorkerPosition.x){
                return true;
            }
        }
        return false;
    }

    private boolean BoxAboveWorker() {
        return currentBoxPosition.x == currentWorkerPosition.x &&
                currentBoxPosition.y == currentWorkerPosition.y - 1;
    }

    private boolean BoxBelowWorker() {
        return currentBoxPosition.x == currentWorkerPosition.x &&
                currentBoxPosition.y == currentWorkerPosition.y + 1;
    }

    private boolean BoxRightToWorker() {
        return currentBoxPosition.x == currentWorkerPosition.x + 1 &&
                currentBoxPosition.y == currentWorkerPosition.y;
    }

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
    public BusyWorkerMap getMap() {
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

    public Point getCurrentBoxPosition() {
        return currentBoxPosition;
    }

    public int getScore() {
        return score;
    }

    public int getTimeUsed() {
        return timeUsed;
    }
}