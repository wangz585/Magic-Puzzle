package com.group0536.puzzlemazing.views.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.actions.menu.MenuPageActionCreator;
import com.group0536.puzzlemazing.stores.menu.MenuStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.games.AnimationActivity;
import com.group0536.puzzlemazing.views.preference.PreferenceActivity;
import com.group0536.puzzlemazing.views.scoreboard.ScoreBoardActivity;

import java.io.Serializable;

/**
 * This is a main menu activity
 */
public class MenuPageActivity extends FluxActivity {
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
        actionCreator = new MenuPageActionCreator(dispatcher);
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
                Intent intent = new Intent(MenuPageActivity.this, ScoreBoardActivity.class);
                MenuPageActivity.this.startActivity(intent);
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
                Intent intent = new Intent(MenuPageActivity.this, PreferenceActivity.class);
                intent.putExtra("User", (Serializable) store.getPlayer());
                MenuPageActivity.this.startActivity(intent);

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
                Intent intent = new Intent(MenuPageActivity.this, AnimationActivity.class);
                intent.putExtra("challenge", 0);
                startActivity(intent);
            }
        });
    }


}
