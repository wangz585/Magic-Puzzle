package com.group0536.puzzlemazing.stores.busyworker;

import android.util.SparseArray;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.models.BusyWorkerRawMaps;

import java.util.HashMap;

public class BusyWorkerInitController {

    private SparseArray<HashMap<Object, String[]>> initializationData = new SparseArray<>();

    BusyWorkerInitController() {
        HashMap<Object, String[]> contentMapLevelOne = new HashMap<>();
        contentMapLevelOne.put("ContentView", BusyWorkerRawMaps.LEVEL_1);
        initializationData.put(1,contentMapLevelOne);
        HashMap<Object, String[]> contentMapLevelTwo = new HashMap<>();
        contentMapLevelTwo.put("ContentView", BusyWorkerRawMaps.LEVEL_2);
        initializationData.put(2,contentMapLevelTwo);
    }

    HashMap<Object, String[]> getLevelData(int level) {
        return initializationData.get(level);
    }


}
