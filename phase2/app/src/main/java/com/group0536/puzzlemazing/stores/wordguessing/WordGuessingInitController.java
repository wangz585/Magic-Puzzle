package com.group0536.puzzlemazing.stores.wordguessing;

import android.util.SparseArray;

import com.group0536.puzzlemazing.R;

import java.util.HashMap;

class WordGuessingInitController {
    private SparseArray<HashMap<Object, Object>> initializationData = new SparseArray<>();

    WordGuessingInitController() {
        HashMap<Object, Object> contentMapLevelOne = new HashMap<>();
        contentMapLevelOne.put("ContentView", R.layout.activity_word_guessing_level_one);
        initializationData.put(1,contentMapLevelOne);
    }

    HashMap<Object, Object> getLevelData(int level) {
        return initializationData.get(level);
    }


}