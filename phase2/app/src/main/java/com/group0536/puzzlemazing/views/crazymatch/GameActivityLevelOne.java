package com.group0536.puzzlemazing.views.crazymatch;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.crazymatch.CrazyMatchActionCreator;
import com.group0536.puzzlemazing.models.Animal;
import com.group0536.puzzlemazing.models.CrazyMatchBoard;
import com.group0536.puzzlemazing.stores.crazymatch.CrazyMatchChangeEvent;
import com.group0536.puzzlemazing.stores.crazymatch.CrazyMatchStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.squareup.otto.Subscribe;

public class GameActivityLevelOne extends FluxActivity {
    private CrazyMatchStore store;
    private CrazyMatchActionCreator actionCreator;
    private int ballDrawingInt;

    // Components
    private ImageButton[][] btnBalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy_match_level_one);
        ballDrawingInt = R.drawable.crazy_match_yellow_ball;
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
        if (store.canFlip(row, col)) {
            actionCreator.flip(row, col);
        }
    }

    @Subscribe
    public void update(CrazyMatchChangeEvent e) {
        updateUI();
    }

    /**
     * Update UI of this activity according to TicTacToeStore
     */
    private void updateUI() {
        updateBoard();
    }

    private void updateBoard() {
        CrazyMatchBoard board = store.getBoard();
            for (int i = 0; i < board.getNumRow(); i++) {
                for (int j = 0; j < board.getNumColumn(); j++) {
                    final ImageButton btn = btnBalls[i][j];
                    Animal animal = board.getAnimal(i, j);
                    if (animal == null) {
                        btn.setVisibility(View.INVISIBLE);
                        //System.out.println("");
                    } else if (animal.isFlipped()) {
                        int animalSide = board.getAnimal(i, j).getAnimalSide();
                        btn.setImageResource(animalSide);
                    } else {
                        GameActivityLevelOne.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btn.setImageResource(ballDrawingInt);
                            }
                        });
                    }

                }
        }
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
