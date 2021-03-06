package com.group0536.puzzlemazing.views.games.crazymatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.games.crazymatch.CrazyMatchActionCreator;
import com.group0536.puzzlemazing.models.crazymatch.Animal;
import com.group0536.puzzlemazing.models.crazymatch.Board;
import com.group0536.puzzlemazing.stores.games.crazymatch.CrazyMatchStoreChangeEvent;
import com.group0536.puzzlemazing.stores.games.crazymatch.CrazyMatchStore;
import com.group0536.puzzlemazing.views.games.GameActivity;
import com.group0536.puzzlemazing.views.games.GameFinishedActivity;
import com.group0536.puzzlemazing.views.games.busyworker.BusyWorkerView;
import com.squareup.otto.Subscribe;

/**
 * This is an activity for the crazy match game
 */
public class CrazyMatchActivity extends GameActivity {
    private CrazyMatchStore store;
    private CrazyMatchActionCreator actionCreator;
    // The id of the ball drawing
    private int ballDrawingInt;

    // Components
    private ImageButton[][] btnBalls;
    private TextView txtScore;
    private int contentViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentLevel = getIntent().getIntExtra("level", 1);
        contentViewId = store.getContentView(currentLevel);
        setContentView(contentViewId);
        String layoutId = "layoutCrazyMatch" + currentLevel;
        int res = getResources()
                .getIdentifier(layoutId, "id", getPackageName());
        findViewById(res).post(new Runnable() {
            @Override
            public void run() {
                playIntro(R.raw.challenge3);
            }
        });
        bindViews();
    }

    /**
     * Initialize the elements on this view
     */
    private void bindViews() {
        initializeBalls();
        initializeScoreText();
    }

    /**
     * Initialize the text view displaying the score
     */
    private void initializeScoreText() {
        txtScore = findViewById(R.id.txtScore);
        String scoreText = getString(R.string.display_score, 0);
        txtScore.setText(scoreText);
    }

    /**
     * Initialize balls
     */
    private void initializeBalls() {
        int numRow = store.getNumberOfRows();
        int numCol = store.getNumberOfColumns();
        ballDrawingInt = R.drawable.match_ball;
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
     * @param i       row i position of the game_button
     * @param j       column j position of the game_button
     * @param btnBall ball game_button
     */
    private void setBallOnClickListener(int i, int j, ImageButton btnBall) {
        final int row = i;
        final int col = j;
        btnBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (store.canFlip(row, col)) {
                    actionCreator.flip(row, col);
                    actionCreator.updateScore(store.getUser().getToken(),
                            store.getUser().getLevel(), store.getScore());
                }

            }
        });
    }

    @Subscribe
    public void onCrazyMatchStoreChange(CrazyMatchStoreChangeEvent e) {
        updateUI();
        checkGameOver();
    }

    /**
     * set the game to be over if conditions satisfied
     */
    private void checkGameOver() {
        if (store.isGameOver()) {
            Intent intent = new Intent(CrazyMatchActivity.this, GameFinishedActivity.class);
            intent.putExtra("score", store.getScore());
            intent.putExtra("challenge", 3);
            startActivity(intent);
        }
    }

    /**
     * Update UI of this activity according to CrazyMatchStore
     */
    private void updateUI() {
        updateBoard();
        updateScore();
    }

    /**
     * Update the current game's score according to the store
     */
    private void updateScore() {
        int score = store.getScore();
        final String scoreText = getString(R.string.display_score, score);
        CrazyMatchActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtScore.setText(scoreText);
            }
        });
    }

    /**
     * Update the crazy match board according to the store
     */
    private void updateBoard() {
        Board board = store.getBoard();
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
                    CrazyMatchActivity.this.runOnUiThread(new Runnable() {
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
        super.initFluxComponents();
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
