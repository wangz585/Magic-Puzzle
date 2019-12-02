package com.group0536.puzzlemazing.views.games;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.views.menu.MenuActivity;
import com.group0536.puzzlemazing.views.appinit.AppInitializeActivity;

/**
 * An activity after a game has finished
 *
 * @author Hanzi Jiang
 * @author Jimmy Lan
 */
public class GameFinishedActivity extends AppInitializeActivity {
    // Components
    private Button btnMenu;
    private TextView txtScore;

    // Data
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_exit);
        loadData();
        bindViews();
        addListeners();
    }

    private void loadData() {
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
    }

    /**
     * Initialize all the components on this activity
     */
    private void bindViews() {
        txtScore = findViewById(R.id.txtScore);
        txtScore.setText(getString(R.string.score, score));
        btnMenu = findViewById(R.id.btnMenu);
    }

    private void addListeners() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameFinishedActivity.this, GameMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
