package com.group0536.puzzlemazing.views.wordguessing;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.wordguessing.WordGuessingActionCreator;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.wordguessing.WordGuessingGameStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.squareup.otto.Subscribe;

public class GameFinishedActivity extends FluxActivity {

    private WordGuessingGameStore store;
    private WordGuessingActionCreator actionCreator;

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
        btnmenu = findViewById(getResources()
                .getIdentifier("btnMenu", "id", getPackageName()));//go to menu
        btnplayagain = findViewById(getResources()
                .getIdentifier("btnPlayAgain", "id", getPackageName()));
        txtscore.setText("Your current score is" + store.getScore());
        txthighscore.setText("Your current score is" + store.getScore());//get the highest score from server
        txttime.setText("Your current score is" + store.getScore()); //get the time spent from timer
        //get the highest score from the server
        setUpButton();
    }

    @Override
    protected void initFluxComponents() {

    }

    private void setUpButton() {
        btnplayagain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.group0536.puzzlemazing.views.wordguessing.GameFinishedActivity.this, SelectLevelActivity.class);
                startActivity(intent);
            }
        });
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
