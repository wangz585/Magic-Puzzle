package com.group0536.puzzlemazing.views.busyworker;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.actions.scoreboard.ScoreBoardActionCreator;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.crazymatch.SelectLevelActivity;
import com.group0536.puzzlemazing.views.scoreboard.ScoreBoardActivity;
import com.squareup.otto.Subscribe;

public class GameFinishedActivity extends FluxActivity {

    private BusyWorkerStore store;
    private BusyWorkerActionCreator actionCreator;

    private Button btnMenu;
    private Button btnPlayAgain;
    private TextView txtScore;
    private TextView txtTotalscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_exit);
        btnMenu = findViewById(R.id.btnMenu);
        btnPlayAgain = findViewById(R.id.btnNextLevel);
        btnMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(GameFinishedActivity.this, ScoreBoardActivity.class);
                startActivity(intent);
            }
        });
        btnPlayAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(GameFinishedActivity.this, SelectLevelActivity.class);
                startActivity(intent);
            }
        });
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
