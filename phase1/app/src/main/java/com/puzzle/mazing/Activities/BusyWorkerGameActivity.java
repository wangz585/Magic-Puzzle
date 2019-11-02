package com.puzzle.mazing.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
;import com.puzzle.mazing.DataAccess.GameState;
import com.puzzle.mazing.Models.BusyWorkerGameLevels;


public class BusyWorkerGameActivity extends AppCompatActivity {
    public static final String KEY_SELECTED_LEVEL = "Selected_Level";
    private GameState mCurrentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int selected_level = getIntent().getIntExtra(KEY_SELECTED_LEVEL, 1);
        mCurrentState = new GameState(BusyWorkerGameLevels.getLevel(selected_level));
        BusyWorkerGameView gameView = new BusyWorkerGameView(this);
        setContentView(gameView);
    }


    public GameState getCurrentState(){
        return mCurrentState;
    }
}
