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
import com.group0536.puzzlemazing.views.games.busyworker.BusyWorkerActivity;
import com.group0536.puzzlemazing.views.games.crazymatch.CrazyMatchActivity;
import com.group0536.puzzlemazing.views.games.wordguessing.WordGuessingActivity;

import java.util.ArrayList;
import java.util.List;

public class GameMapActivity extends AppCompatActivity {
    // Components
    Button btnEnter;
    TextView tvNextGame;

    List<Intent> gameActivities = new ArrayList<Intent>() {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_map);
        bindViews();
        addListeners();
        registerGames();
    }

    private void registerGames() {
        Intent busyWorkerIntent = new Intent(GameMapActivity.this,
                BusyWorkerActivity.class);
        Intent wordGuessingIntent = new Intent(GameMapActivity.this,
                WordGuessingActivity.class);
        Intent crazyMatchActivity = new Intent(GameMapActivity.this,
                CrazyMatchActivity.class);
        gameActivities.add(busyWorkerIntent);
        gameActivities.add(wordGuessingIntent);
        gameActivities.add(crazyMatchActivity);
    }

    private void addListeners() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void bindViews() {
        btnEnter = findViewById(R.id.btnEnter);
        tvNextGame = findViewById(R.id.tvNextGame);
    }
}
