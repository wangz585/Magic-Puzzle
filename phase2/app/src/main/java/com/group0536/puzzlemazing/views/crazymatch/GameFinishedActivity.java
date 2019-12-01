package com.group0536.puzzlemazing.views.crazymatch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.actions.crazymatch.CrazyMatchActionCreator;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.stores.crazymatch.CrazyMatchStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.scoreboard.ScoreBoardActivity;
import com.squareup.otto.Subscribe;

public class GameFinishedActivity extends FluxActivity {

    private CrazyMatchStore store;
    private CrazyMatchActionCreator actionCreator;

    private Button btnMenu;
    private Button btnPlayAgain;
    private TextView txtscore;
    private TextView txthighscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_exit);
        btnMenu = findViewById(R.id.btnMenu);
        btnPlayAgain = findViewById(R.id.btnNextLevel);
        btnMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.group0536.puzzlemazing.views.crazymatch.GameFinishedActivity.this, ScoreBoardActivity.class);
                startActivity(intent);
            }
        });
        btnPlayAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.group0536.puzzlemazing.views.crazymatch.GameFinishedActivity.this, ScoreBoardActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initFluxComponents() {
        store = CrazyMatchStore.getInstance(dispatcher);
        actionCreator = new CrazyMatchActionCreator(dispatcher);
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
