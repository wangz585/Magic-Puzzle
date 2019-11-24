package com.group0536.puzzlemazing.stores.busyworker;

import android.content.res.Resources;
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

public class BusyWorkerStore extends Store implements BusyWorkerActions {

    private BusyWorkerMap map;
    private static BusyWorkerStore instance;
    private int score;
    private Point currentWorkerPosition;
    private Point currentBoxPosition;

    protected BusyWorkerStore(Dispatcher dispatcher) {
        super(dispatcher);
        score = 0;
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
                postChange();
                break;
            case INIT_MAP:
                int level = (int)action.getPayloadEntry("level");
                initMap(level);
                initCurrentPosition();
                postChange();
                break;
        }
    }

    private void initMap(int level) {
        this.map = new BusyWorkerMap();
        String[] rawMap;
        switch (level) {
            case 1: rawMap = BusyWorkerRawMaps.LEVEL_1;
                break;
            case 2: rawMap = BusyWorkerRawMaps.LEVEL_2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + level);
        }
        initLabels(rawMap);
        initCircumference(rawMap);
    }

    private void initLabels(String[] rawMap){
        ArrayList<Point> wallPositions = new ArrayList<>();
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
                }
            }
        map.setWallPositions(wallPositions);
    }

    private void initCurrentPosition(){
        currentWorkerPosition = new Point(map.getInitialWorkerPosition().x,map.getInitialWorkerPosition().y);
        currentBoxPosition = new Point(map.getInitialBoxPosition().x,map.getInitialBoxPosition().y);
    }

    private void initCircumference(String[] rawMap){
        map.setWidth(rawMap[1].length());
        map.setHeight(rawMap.length);
    }

    private boolean checkWin() {
        return currentBoxPosition.equals(map.getFlagPosition());
    }

    private boolean checkLose() {
        for (Point deadPosition : map.getDeadPositions()) {
            if (deadPosition.equals(currentBoxPosition)) return true;
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
        else if ((!WallAboveWorker())) currentWorkerPosition.y = currentWorkerPosition.y - 1;
    }

    private void moveBelow() {
        if ((!WallBelowBox()) && BoxBelowWorker()) {
            currentWorkerPosition.y = currentWorkerPosition.y + 1;
            currentBoxPosition.y = currentBoxPosition.y + 1;
        }
        else if ((!WallBelowWorker())) currentWorkerPosition.y = currentWorkerPosition.y + 1;
    }

    private void moveLeft() {
        if ((!WallLeftToBox()) && BoxLeftToWorker()) {
            currentWorkerPosition.x = currentWorkerPosition.x - 1;
            currentBoxPosition.x = currentBoxPosition.x - 1;
        }
        else if ((!WallLeftToWorker())) currentWorkerPosition.x = currentWorkerPosition.x - 1;
    }

    private void moveRight() {
        if ((!WallRightToBox()) && BoxRightToWorker()) {
            currentWorkerPosition.x = currentWorkerPosition.x + 1;
            currentBoxPosition.x = currentBoxPosition.x + 1;
        }
        else if ((!WallRightToWorker())) currentWorkerPosition.x = currentWorkerPosition.x + 1;
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
            if (wallPosition.y == currentBoxPosition.y + 1 && wallPosition.x == currentBoxPosition.x){
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

    public static BusyWorkerStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new BusyWorkerStore(dispatcher);
        }
        return instance;
    }

    public BusyWorkerMap getMap() {
        return map;
    }

    public Point getCurrentWorkerPosition() {
        return currentWorkerPosition;
    }

    public Point getCurrentBoxPosition() {
        return currentBoxPosition;
    }
}