package com.group0536.puzzlemazing.views.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.actions.menu.MenuActionCreator;
import com.group0536.puzzlemazing.stores.menu.MenuStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.games.GameActivity;
import com.group0536.puzzlemazing.views.games.GameMapActivity;
import com.group0536.puzzlemazing.views.global.PreferenceActivity;
import com.group0536.puzzlemazing.views.global.ScoreBoardActivity;

import java.io.Serializable;

/**
 * This is a main menu activity
 */
public class MenuActivity extends FluxActivity {
    // Components
    private Button btnEnter;
    private Button btnSetting;
    private Button btnScoreBoard;
    private ActionCreator actionCreator;
    private MenuStore store;

    @Override
    protected void initFluxComponents() {
        store = MenuStore.getInstance(dispatcher);
        setContentView(R.layout.activity_menu_page);
        actionCreator = new MenuActionCreator(dispatcher);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pass the user into the store
        bindViews();
    }

    /**
     * Initialize all the components on the activity
     */
    private void bindViews() {
        initializeEnterButton();
        initializeSettingButton();
        initializeScoreboardButton();
    }

    /**
     * Initialize the scoreboard button
     */
    private void initializeScoreboardButton() {
        btnScoreBoard = findViewById(R.id.btnScoreboard);
        btnScoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ScoreBoardActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
    }

    /**
     * Initialize the settings button
     */
    private void initializeSettingButton() {
        btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PreferenceActivity.class);
                intent.putExtra("User", (Serializable) store.getUser());
                MenuActivity.this.startActivity(intent);
            }
        });
    }

    /**
     * Initialize the enter game button
     */
    private void initializeEnterButton() {
        btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If the user just registered, play the animation and go to the page to choose game

                // otherwise, go to the page to select game
                Intent intent = new Intent(MenuActivity.this, GameMapActivity.class);
                startActivity(intent);
            }
        });
    }


}
