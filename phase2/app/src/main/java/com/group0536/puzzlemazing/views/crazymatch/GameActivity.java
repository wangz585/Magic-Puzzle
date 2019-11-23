package com.group0536.puzzlemazing.views.crazymatch;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.ContentView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.crazymatch.CrazyMatchActionCreator;
import com.group0536.puzzlemazing.models.Animal;
import com.group0536.puzzlemazing.models.CrazyMatchBoard;
import com.group0536.puzzlemazing.stores.crazymatch.CrazyMatchChangeEvent;
import com.group0536.puzzlemazing.stores.crazymatch.CrazyMatchStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.squareup.otto.Subscribe;

public class GameActivity extends FluxActivity {
    private CrazyMatchStore store;
    private CrazyMatchActionCreator actionCreator;
    private int ballDrawingInt;
    private int level;

    // Components
    private ImageButton[][] btnBalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        level = getIntent().getIntExtra("level", 0);
            setContentView(store.getContentView(1));
        bindViews();
    }

    /**
     * Initialize the elements on this view
     */
    private void bindViews() {
        initializeBalls();
    }

    /**
     * Initialize balls
     */
    private void initializeBalls() {
        int numRow = store.getNumberOfRows();
        int numCol = store.getNumberOfColumns();
        ballDrawingInt = R.drawable.crazy_match_ball;
        btnBalls = new ImageButton[numRow][numCol];
        for (int i = 0; i < btnBalls.length; i++) {
            for (int j = 0; j < btnBalls[0].length; j++) {
                final String btnId = "btn_ball_" + i + j;
                int res = getResources()
                        .getIdentifier(btnId, "id", getPackageName());
                ImageButton btnBall = findViewById(res);
                btnBalls[i][j] = btnBall;
                setBallOnClickListener(i, j, btnBall);
            }
        }
    }

    /**
     * Set On Click Listener of btnBall at row i and column j
     *
     * @param i       row i position of the button
     * @param j       column j position of the button
     * @param btnBall ball button
     */
    private void setBallOnClickListener(int i, int j, ImageButton btnBall) {
        final int row = i;
        final int col = j;
        btnBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (store.canFlip(row, col)) {
                    actionCreator.flip(row, col);
                }
            }
        });
    }

    @Subscribe
    public void update(CrazyMatchChangeEvent e) {
        updateUI();
    }

    /**
     * Update UI of this activity according to CrazyMatchStore
     */
    private void updateUI() {
        updateBoard();
    }


    private void updateBoard() {
        CrazyMatchBoard board = store.getBoard();
        for (int i = 0; i < board.getNumberOfRows(); i++) {
            for (int j = 0; j < board.getNumberOfColumns(); j++) {
                final ImageButton btn = btnBalls[i][j];
                Animal animal = board.getAnimal(i, j);
                if (animal == null) {
                    btn.setVisibility(View.INVISIBLE);
                } else if (animal.isFlipped()) {
                    int animalSide = board.getAnimal(i, j).getAnimalSide();
                    btn.setImageResource(animalSide);
                } else {
                    GameActivity.this.runOnUiThread(new Runnable() {
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
