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
    private TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_exit);
        bindView();
    }

    private void bindView() {
        btnNext = findViewById(R.id.btnNextChallenge);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameFinishedActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });
        btnMenu = findViewById(R.id.btnMenu);
        txtScore = findViewById(R.id.txtScore);
        txtTime = findViewById(R.id.txt_time);
    }
}
