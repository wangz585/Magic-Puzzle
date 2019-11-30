package com.group0536.puzzlemazing.views.busyworker;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.squareup.otto.Subscribe;

public class GameFinishedActivity extends FluxActivity {

    private BusyWorkerStore store;
    private BusyWorkerActionCreator actionCreator;

    private Button btnmenu;
    private Button btnplayagain;
    private TextView txtscore;
    private TextView txthighscore;
    private TextView txttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_exit);

    }

    @Override
    protected void initFluxComponents() {
        store = BusyWorkerStore.getInstance(dispatcher);
        actionCreator = new BusyWorkerActionCreator(dispatcher);
    }

    @Subscribe
    public void x(BusyWorkerChangeEvent e) {
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
