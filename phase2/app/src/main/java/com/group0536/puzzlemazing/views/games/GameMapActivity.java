package com.group0536.puzzlemazing.views.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.stores.games.GameStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.games.busyworker.BusyWorkerSelectLevelActivity;
import com.group0536.puzzlemazing.views.games.crazymatch.CrazyMatchSelectLevelActivity;
import com.group0536.puzzlemazing.views.games.wordguessing.WordGuessingSelectLevelActivity;
import com.group0536.puzzlemazing.views.menu.MenuActivity;

import java.util.ArrayList;
import java.util.List;

public class GameMapActivity extends FluxActivity {
    // Components
    Button btnEnter;
    TextView tvNextGame;
    GameStore store;
    boolean hasMoreChallenges;

    List<Intent> gameActivities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_map);
        registerGames();
        hasMoreChallenges = store.getUser().getLevel() <= gameActivities.size();
        bindViews();
        addListeners();
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
                if (hasMoreChallenges) {
                    Intent intent = gameActivities.get(store.getUser().getLevel() - 1);
                    intent.putExtra("challenge", store.getUser().getLevel());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(GameMapActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void bindViews() {
        btnEnter = findViewById(R.id.btnEnter);
        tvNextGame = findViewById(R.id.tvNextGame);
        if (hasMoreChallenges) {
            tvNextGame.setText(R.string.next_game_prmopt);
        } else {
            tvNextGame.setText(R.string.finish_all_challenges_prmopt);
        }
    }
}
