package com.group0536.puzzlemazing.views.games.wordguessing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.actions.games.wordguessing.WordGuessingActionCreator;
import com.group0536.puzzlemazing.stores.games.wordguessing.WordGuessingGameStore;
import com.group0536.puzzlemazing.views.FluxActivity;

public class WordGuessingSelectLevelActivity extends FluxActivity {

    private WordGuessingActionCreator actionCreator;
    private WordGuessingGameStore store;
    Button btnLevel1;
    Button btnLevel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy_match_select_level);
        btnLevel1 = findViewById(getResources()
                .getIdentifier("btn_level_1", "id", getPackageName()));
        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = 1;
                actionCreator.initializeWordBank(level, getApplicationContext());
                Intent intent = new Intent(WordGuessingSelectLevelActivity.this, WordGuessingActivity.class);
                intent.putExtra("level", level);
                startActivity(intent);
            }
        });
        btnLevel2 = findViewById(getResources()
                .getIdentifier("btn_level_2", "id", getPackageName()));
        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = 2;
                actionCreator.initializeWordBank(level, getApplicationContext());
                Intent intent = new Intent(WordGuessingSelectLevelActivity.this, WordGuessingActivity.class);
                intent.putExtra("level", level);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerStore(store);
    }

    @Override
    protected void onPause() {
        super.onPause();
        registerStore(store);
    }

    @Override
    protected void initFluxComponents() {
        store = WordGuessingGameStore.getInstance(dispatcher);
        actionCreator = new WordGuessingActionCreator(dispatcher);
    }
}