package com.group0536.puzzlemazing.models;

public class BusyWorkerMap {

    private String[] Background;
    private int[] WorkerPosition;
    private int[] FlagPosition;
    private int[] BoxPosition;

    public BusyWorkerMap(String[] background, int[] workerLocation, int[] flagLocation, int[] Boxlocation){

        Background = background;
        WorkerPosition = workerLocation;
        FlagPosition = flagLocation;
        BoxPosition = Boxlocation;
    }

    public void setWorkerPosition(int[] workerPosition) {
        WorkerPosition = workerPosition;
    }

    public void setBoxPosition(int[] boxPosition) {
        BoxPosition = boxPosition;
    }

    public String[] getBackground() {
        return Background;
    }

    public int[] getWorkerPosition() {
        return WorkerPosition;
    }

    public int[] getFlagPosition() {
        return FlagPosition;
    }

    public int[] getBoxPosition() {
        return BoxPosition;
    }
}
