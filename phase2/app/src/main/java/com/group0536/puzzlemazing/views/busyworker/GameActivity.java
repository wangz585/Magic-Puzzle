package com.group0536.puzzlemazing.views.busyworker;

import android.content.Intent;
import android.os.Bundle;

import com.group0536.puzzlemazing.actions.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.GameFinishedActivity;
import com.squareup.otto.Subscribe;

/**
 * This is an activity for the busy worker game
 */
public class GameActivity extends FluxActivity {

    private BusyWorkerStore store;
    private BusyWorkerActionCreator actionCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void initFluxComponents() {
        store = BusyWorkerStore.getInstance(dispatcher);
        actionCreator = new BusyWorkerActionCreator(dispatcher);
    }

    @Subscribe
    public void update(BusyWorkerChangeEvent e) {
        updateUI();
    }

    private void updateUI() {
        checkGameFinished();
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

    /**
     * Check if the game is finished
     */
    private void checkGameFinished(){
        if (store.checkWin() || store.checkLose()){
            Intent intent = new Intent(GameActivity.this, GameFinishedActivity.class);
            intent.putExtra("score", store.getScore());
            intent.putExtra("challenge", 1);
            startActivity(intent);
        }
    }
}
