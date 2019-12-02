package com.group0536.puzzlemazing.views.games.busyworker;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.PopupWindow;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.games.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.stores.games.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.games.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.games.GameActivity;
import com.group0536.puzzlemazing.views.games.GameFinishedActivity;
import com.squareup.otto.Subscribe;

/**
 * This is an activity for the busy worker game
 */
public class BusyWorkerActivity extends GameActivity {

    private BusyWorkerStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusyWorkerView busyWorkerView = new BusyWorkerView(this);
        setContentView(busyWorkerView);
        busyWorkerView.post(new Runnable() {
            @Override
            public void run() {
                playIntro(R.raw.challenge1);
            }
        });
    }

    @Override
    protected void initFluxComponents() {
        super.initFluxComponents();
        store = BusyWorkerStore.getInstance(dispatcher);
    }

    @Subscribe
    public void onBusyWorkerStoreChange(BusyWorkerChangeEvent e) {
        if (store.isGameOver()) {
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
        Intent intent = new Intent(BusyWorkerActivity.this, GameFinishedActivity.class);
        // TODO Remove the following two lines
        intent.putExtra("score", store.getScore());
        intent.putExtra("challenge", 1);
        startActivity(intent);
    }
}
