package com.group0536.puzzlemazing.views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.views.animation.AnimationActivity;
import com.group0536.puzzlemazing.views.welcome.AppInitializeActivity;

public class GameFinishedActivity extends AppInitializeActivity {

    private Button btnMenu;
    private Button btnNext;
    private TextView txtScore;
    private TextView txtTimeUsed;
    private int challenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_exit);
        bindView();
    }

    private void bindView() {
        Intent mIntent = getIntent();
        int score = mIntent.getIntExtra("score", 0);
        challenge = mIntent.getIntExtra("challenge", 0);
        btnNext = findViewById(R.id.btnNextChallenge);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameFinishedActivity.this, AnimationActivity.class);
                intent.putExtra("challenge", challenge);
                startActivity(intent);
            }
        });
        btnMenu = findViewById(R.id.btnMenu);
        txtScore = findViewById(R.id.txtScore);
        txtTimeUsed = findViewById(R.id.txtTime);
        //txtScore.setText(score);
    }
}
