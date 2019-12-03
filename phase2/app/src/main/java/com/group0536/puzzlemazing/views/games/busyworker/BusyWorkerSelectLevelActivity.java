package com.group0536.puzzlemazing.views.games.busyworker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.games.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.stores.games.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.FluxActivity;

/**
 * This is an activity that prompts the user to select a game level
 */
public class BusyWorkerSelectLevelActivity extends FluxActivity {

    private BusyWorkerActionCreator actionCreator;
    private BusyWorkerStore store;

    //Components
    Button btnLevel1;
    Button btnLevel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy_match_select_level);
        initializeButtonLevel1();
        initializeButtonLevel2();
    }

    /**
     * Initialize the button level 2
     */
    private void initializeButtonLevel2() {
        btnLevel2 = findViewById(getResources()
                .getIdentifier("btn_level_2", "id", getPackageName()));
        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionCreator.initializeGame(2, getChallenge());
                Intent intent = new Intent(BusyWorkerSelectLevelActivity.this,
                        BusyWorkerActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Initialize the button level 1
     */
    private void initializeButtonLevel1() {
        btnLevel1 = findViewById(getResources()
                .getIdentifier("btn_level_1", "id", getPackageName()));
        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionCreator.initializeGame(1, getChallenge());
                Intent intent = new Intent(BusyWorkerSelectLevelActivity.this,
                        BusyWorkerActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Get the order of the busy worker  game
     * @return the challenge number, which is the order of the busy worker game relative to all
     * games
     */
    private int getChallenge() {
        Intent mIntent = getIntent();
        return mIntent.getIntExtra("challenge", 1);
    }

    @Override
    protected void initFluxComponents() {
        store = BusyWorkerStore.getInstance(dispatcher);
        actionCreator = new BusyWorkerActionCreator(dispatcher);
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
}
