package com.group0536.puzzlemazing.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group0536.puzzlemazing.R;

/**
 * This activity displays welcome message to player as they
 * enter the game.
 *
 * @author Jimmy
 * @version 1.0
 * @since 1.0
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
}
