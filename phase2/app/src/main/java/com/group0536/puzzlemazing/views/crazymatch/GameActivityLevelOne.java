package com.group0536.puzzlemazing.views.crazymatch;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.crazymatch.CrazyMatchActionCreator;
import com.group0536.puzzlemazing.stores.crazymatch.CrazyMatchStore;
import com.group0536.puzzlemazing.views.FluxActivity;

public class GameActivityLevelOne extends FluxActivity {
    private CrazyMatchStore store;
    private CrazyMatchActionCreator actionCreator;

    // Components
    private ImageButton[][] btnBalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy_match_level_one);
        bindViews();
    }

    private void bindViews() {
        btnBalls = new ImageButton[2][3];
        for (int i = 0; i < btnBalls.length; i++) {
            for (int j = 0; j < btnBalls[0].length; j++) {
                final String btnId = "btn_ball_" + i + j;
                int res = getResources()
                        .getIdentifier(btnId, "id", getPackageName());
                ImageButton btnBall = findViewById(res);
                btnBalls[i][j] = btnBall;

                final int row = i;
                final int col = j;
                btnBall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ballOnClick(row, col);
                    }
                });
            }
        }
    }

    public void ballOnClick(int row, int col) {
        //actionCreator.flip(row, col);
    }

    @Override
    protected void initFluxComponents() {
        store = CrazyMatchStore.getInstance(dispatcher);
        actionCreator = new CrazyMatchActionCreator(dispatcher);
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
