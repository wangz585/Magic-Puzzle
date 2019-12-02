package com.group0536.puzzlemazing.views.games.busyworker;

import android.content.Intent;
import android.os.Bundle;

import com.group0536.puzzlemazing.actions.games.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.stores.games.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.games.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.games.GameFinishedActivity;
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
    public void onBusyWorkerStoreChange(BusyWorkerChangeEvent e) {
        if (store.checkWin() || store.checkLose()) {
            showGameResult();
        }
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
     * Show an activity informing the user about the game result.
     */
    private void showGameResult(){
        Intent intent = new Intent(GameActivity.this, GameFinishedActivity.class);
        // TODO Remove the following two lines
        intent.putExtra("score", store.getScore());
        intent.putExtra("challenge", 1);
        startActivity(intent);
    }
}
