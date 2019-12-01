package com.group0536.puzzlemazing.stores.busyworker;

import android.util.SparseArray;

import com.group0536.puzzlemazing.models.busyworker.RawMaps;

import java.util.HashMap;

/**
 * This is a class responsible for the initialization process of busy worker game
 */
class BusyWorkerInitController {

    private SparseArray<HashMap<Object, String[]>> initializationData = new SparseArray<>();

    BusyWorkerInitController() {
        HashMap<Object, String[]> contentMapLevelOne = new HashMap<>();
        contentMapLevelOne.put("ContentView", RawMaps.LEVEL_1);
        initializationData.put(1,contentMapLevelOne);
        HashMap<Object, String[]> contentMapLevelTwo = new HashMap<>();
        contentMapLevelTwo.put("ContentView", RawMaps.LEVEL_2);
        initializationData.put(2,contentMapLevelTwo);
    }

    HashMap<Object, String[]> getLevelData(int level) {
        return initializationData.get(level);
    }


}
