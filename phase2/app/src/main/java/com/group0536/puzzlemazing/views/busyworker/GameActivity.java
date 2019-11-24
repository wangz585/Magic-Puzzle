package com.group0536.puzzlemazing.views.busyworker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group0536.puzzlemazing.actions.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.squareup.otto.Subscribe;

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
    public void x(BusyWorkerChangeEvent e) {
        updateUI();
    }

    private void updateUI() {
        checkGameFinished();
    }

    private void checkGameFinished(){
        if (store.checkWin()){
            Intent intent = new Intent(GameActivity.this, GameFinishedActivity.class);
            startActivity(intent);
        }
    }
}
