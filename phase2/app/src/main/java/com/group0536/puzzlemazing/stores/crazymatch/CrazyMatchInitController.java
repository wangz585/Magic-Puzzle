package com.group0536.puzzlemazing.stores.crazymatch;

import android.util.SparseArray;

import com.group0536.puzzlemazing.R;

import java.util.HashMap;

class CrazyMatchInitController {
    private SparseArray<HashMap<Object, Object>> initializationData = new SparseArray<>();

    CrazyMatchInitController() {
        HashMap<Object, Object> contentMapLevelOne = new HashMap<>();
        contentMapLevelOne.put("ContentView", R.layout.activity_crazy_match_level_one);
        initializationData.put(1,contentMapLevelOne);
        HashMap<Object, Object> contentMapLevelTwo = new HashMap<>();
        contentMapLevelTwo.put("ContentView", R.layout.activity_crazy_match_level_two);
        initializationData.put(2,contentMapLevelTwo);
    }

    HashMap<Object, Object> getLevelData(int level) {
        return initializationData.get(level);
    }


}

