package com.group0536.puzzlemazing.views.games.wordguessing;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.games.wordguessing.WordGuessingActionCreator;
import com.group0536.puzzlemazing.stores.games.wordguessing.WordGuessingChangeEvent;
import com.group0536.puzzlemazing.stores.games.wordguessing.WordGuessingGameStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.games.GameActivity;
import com.group0536.puzzlemazing.views.games.GameFinishedActivity;
import com.squareup.otto.Subscribe;

import java.util.List;

public class WordGuessingActivity extends GameActivity {
    private WordGuessingGameStore store;
    private WordGuessingActionCreator actionCreator;

    // Components
    private ImageButton btnNext;
    private EditText txtPuzzle;
    private TextView txtHint;
    private TextView countDownTimeLeft;
    private int currentLevel;

    public WordGuessingActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_guessing);
        this.currentLevel = getIntent().getIntExtra("level", 1);
        bindViews();
        findViewById(R.id.layoutWordGuessing).post(new Runnable() {
            @Override
            public void run() {
                playIntro(R.raw.challenge1);
            }
        });
    }

    /**
     * Initialize the elements on this view
     */
    private void bindViews() {
        initializeNextButton();
        initializeAnswerText();
        initializeHintText();
        initializeTimeLeft();
    }

    private void initializeNextButton() {
        btnNext = findViewById(R.id.btn_submit);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (store.isGameStarted()) {
                    String userAnswer = txtPuzzle.getText().toString();
                    actionCreator.submitAnswer(userAnswer);
                    actionCreator.updateScore(currentLevel, store.getScore(), store.getPlayer().getToken());
                }
                else {
                    actionCreator.startGame();
                    countDownTimer.start();
                    txtPuzzle.setFilters(new InputFilter[] { new InputFilter.LengthFilter(store.getPuzzleLength())});
                }
            }
        });

    }
    private CountDownTimer countDownTimer = new CountDownTimer(120000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int timeLeft = (int) (millisUntilFinished / 1000);
            String value = String.valueOf(timeLeft);
            countDownTimeLeft.setText(String.format("Remaining Time: %ss", value));
            if (timeLeft == 0) {
                Intent intent = new Intent(WordGuessingActivity.this, GameFinishedActivity.class);
                intent.putExtra("score", store.getScore());
                intent.putExtra("challenge", 2);
                startActivity(intent);
            }
        }

        @Override
        public void onFinish() {
            actionCreator.timeOver();
            btnNext.setEnabled(false);
        }
    };

    private void initializeTimeLeft() {
        countDownTimeLeft = findViewById(R.id.txt_time);
    }

    private void initializeAnswerText() {
        txtPuzzle = findViewById(R.id.txt_word);
    }

    private void initializeHintText() {
        txtHint = findViewById(R.id.txt_hint);
    }


    @Override
    protected void initFluxComponents() {
        super.initFluxComponents();
        store = WordGuessingGameStore.getInstance(dispatcher);
        actionCreator = new WordGuessingActionCreator(dispatcher);
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

    @Subscribe
    public void update(WordGuessingChangeEvent e) {
        updateUI();
        if (store.isGameOver()) {
            System.out.println("Finished the game");
            Intent intent = new Intent(WordGuessingActivity.this, GameFinishedActivity.class);
            intent.putExtra("score", store.getScore());
            intent.putExtra("challenge", 2);
            startActivity(intent);
        }
    }

    private void updateUI() {
        updatePuzzle();
    }

    /**
     * Update the word for guessing
     */
    private void updatePuzzle() {
        StringBuilder myPuzzle = new StringBuilder();
        List<Character> puzzle = store.getPuzzle();
        for (Character c : puzzle) {
            myPuzzle.append(c);
        }
        txtPuzzle.setText("");
        txtPuzzle.setHint(myPuzzle);
        txtHint.setText(store.getHint());

    }
}
