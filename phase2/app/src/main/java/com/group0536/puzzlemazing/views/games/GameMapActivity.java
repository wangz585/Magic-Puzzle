package com.group0536.puzzlemazing.views.games;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.games.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.stores.games.GameStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.games.busyworker.BusyWorkerActivity;
import com.group0536.puzzlemazing.views.games.busyworker.BusyWorkerSelectLevelActivity;
import com.group0536.puzzlemazing.views.games.crazymatch.CrazyMatchActivity;
import com.group0536.puzzlemazing.views.games.crazymatch.CrazyMatchSelectLevelActivity;
import com.group0536.puzzlemazing.views.games.wordguessing.WordGuessingActivity;
import com.group0536.puzzlemazing.views.games.wordguessing.WordGuessingSelectLevelActivity;

import java.util.ArrayList;
import java.util.List;

public class GameMapActivity extends FluxActivity {
    // Components
    Button btnEnter;
    TextView tvNextGame;

    GameStore store;

    List<Intent> gameActivities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_map);
        bindViews();
        addListeners();
        registerGames();
    }

    @Override
    protected void initFluxComponents() {
        store = GameStore.getInstance(dispatcher);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerStore(store);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterStore(store);
    }

    private void registerGames() {
        Intent busyWorkerIntent = new Intent(GameMapActivity.this,
                BusyWorkerSelectLevelActivity.class);
        Intent wordGuessingIntent = new Intent(GameMapActivity.this,
                WordGuessingSelectLevelActivity.class);
        Intent crazyMatchActivity = new Intent(GameMapActivity.this,
                CrazyMatchSelectLevelActivity.class);
        gameActivities.add(busyWorkerIntent);
        gameActivities.add(wordGuessingIntent);
        gameActivities.add(crazyMatchActivity);
    }

    private void addListeners() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = gameActivities.get(store.getUser().getLevel() - 1);
                startActivity(intent);
            }
        });
    }

    private void bindViews() {
        btnEnter = findViewById(R.id.btnEnter);
        tvNextGame = findViewById(R.id.tvNextGame);
    }
}
