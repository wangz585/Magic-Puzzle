package com.group0536.puzzlemazing.views.wordguessing;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.wordguessing.WordGuessingActionCreator;
import com.group0536.puzzlemazing.stores.wordguessing.WordGuessingChangeEvent;
import com.group0536.puzzlemazing.stores.wordguessing.WordGuessingGameStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.squareup.otto.Subscribe;

import java.util.List;

public class GameActivity extends FluxActivity {
    private WordGuessingGameStore store;
    private WordGuessingActionCreator actionCreator;

    // Components
    private ImageButton btnNext;
    private TextView txtPuzzle;

    public GameActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_guessing_level_one);
        bindViews();
    }


    /**
     * Initialize the elements on this view
     */
    private void bindViews() {
        initializeNextButton();
        initializeAnswerText();
    }

    private void initializeNextButton() {
        btnNext = findViewById(R.id.btn_submit);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (store.isGameStarted()) {
                    String userAnswer = txtPuzzle.getText().toString();
                    actionCreator.submitAnswer(userAnswer);
                } else if (store.isGameOver()) {
                    // TODO
                } else {
                    actionCreator.startGame();
                    txtPuzzle.setFilters(new InputFilter[] { new InputFilter.LengthFilter(store.getPuzzle().size())});
                }
            }
        });

    }

    private void initializeAnswerText() {
        txtPuzzle = findViewById(R.id.txt_word);
    }


    @Override
    protected void initFluxComponents() {
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
    }

    private void updateUI() {
        updatePuzzle();
        updateScore();
    }

    private void updatePuzzle() {
        StringBuilder myPuzzle = new StringBuilder();
        List<Character> puzzle = store.getPuzzle();
        for (Character c : puzzle) {
            myPuzzle.append(c);
        }
        txtPuzzle.setText("");
        txtPuzzle.setHint(myPuzzle);
    }

    private void updateScore() {
        //TODO
    }
}
