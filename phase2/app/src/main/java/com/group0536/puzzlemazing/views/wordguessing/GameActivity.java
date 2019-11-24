package com.group0536.puzzlemazing.views.wordguessing;

import android.os.Bundle;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.views.FluxActivity;

public class GameActivity extends FluxActivity {

    public GameActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_guessing_level_one);
    }

    @Override
    protected void initFluxComponents() {

    }
}
