package com.group0536.puzzlemazing.stores.wordguessing;

import android.content.Context;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.wordguessing.WordGuessingActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.models.wordguessing.ScoreCalculator;
import com.group0536.puzzlemazing.models.wordguessing.Word;
import com.group0536.puzzlemazing.models.wordguessing.WordBank;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WordGuessingGameStore extends Store implements WordGuessingActions {
    private User player;
    private WordBank wordBank;
    private Word currentWord;
    private boolean gameOver;
    private int score;
    private boolean gameStart;
    private ScoreCalculator scoreCalculator;
    private static com.group0536.puzzlemazing.stores.wordguessing.WordGuessingGameStore instance;

    protected WordGuessingGameStore(Dispatcher dispatcher) {
        super(dispatcher);
        // TODO: pass in a player
//        this.player = player;
    }

    /**
     * Get an instance of this store
     *
     * @param dispatcher the dispatcher associated
     * @return an instance of this store
     */
    public static com.group0536.puzzlemazing.stores.wordguessing.WordGuessingGameStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new com.group0536.puzzlemazing.stores.wordguessing.WordGuessingGameStore(dispatcher);
        }
        return instance;
    }

    public boolean isGameStarted() {
        return gameStart;
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new WordGuessingChangeEvent();
    }

    public void setGameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public int getPuzzleLength() {
        return getPuzzle().size();
    }


    public String getHint() {
        return currentWord.getHint();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
          case TIME_OUT:
              setGameOver();
              postChange();
              break;
            case START_GAME:
                Word newWord = getANewWord();
                setCurrentWord(newWord);
                gameStart = true;
                postChange();
                break;
            case SUBMIT_ANSWER:
                String wordGuessed = (String) action.getPayloadEntry("word");
                if(isCorrect(wordGuessed)) {
                    // if the answer is correct
                    updateScore();
                    currentWord.setGuessed(true);
                    if(wordBank.noMoreWord()){
                        setGameOver();
                    }
                    else{
                        currentWord = getANewWord();
                    }
                } else{
                    currentWord.setCurrentState(currentWord.getInitialState());
                }
                /*// if the game is finished
                if(isGameOver()){
                    // time is out, the user cannot longer click the submit game_button
                    // the user should be able to go to somewhere else
                }
                else{
                    // there is time left
                    // clear the user input
                    // give a new word
                    currentWord = getANewWord();
                }*/
                postChange();
                break;
            case INITIALIZE_WORDBANK:
                initializeWordBank((int) action.getPayloadEntry("level"),
                        (Context) action.getPayloadEntry("context"));
                break;
        }
    }

    private void updateScore() {
        score += 10;
        // TODO: update the score to the server for this level of game
        // one game has two levels, two different scores

    }

    /**
     * Initialize a word bank based on the game level
     * @param level
     * @param context
     */
    private void initializeWordBank(int level, Context context) {
            currentWord = null;
            score = 0;
            gameStart = false;
            wordBank = new WordBank(getWordList(level, context));
        }

    private List<Word> getWordList(int level, Context context) {
        List<Word> wordsInBank = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    context.getAssets().open("wordGuessingLevel"+level+".txt")));
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] entry = line.split("--");
                String text = entry[0];
                String hint = entry[1];
                List<Character> initialAppearance = getInitialAppearance((int) Math.ceil(text.length()/2), text);
                Word newWord = new Word(text, hint, initialAppearance);
                wordsInBank.add(newWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsInBank;
    }


    private List<Character> getInitialAppearance(int numMissing, String word) {
        int wordLength = word.length();
        List<Character> initialAppearance = new ArrayList<>();
        List<Integer> missingIndex = getMissingIndex(numMissing, wordLength );
        for (int i = 0; i < wordLength; i++) {
            if (missingIndex.contains(i)) {
                initialAppearance.add(word.charAt(i));
            } else {
                initialAppearance.add('_');
            }

        }
        return initialAppearance;
    }

    private List<Integer> getMissingIndex(int numMissing, int wordLength) {
        List<Integer> missingIndex = new ArrayList<>();
        while (missingIndex.size() < numMissing) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, wordLength);
            if (!missingIndex.contains(randomIndex)) {
                missingIndex.add(randomIndex);
            }
        }
        return missingIndex;
    }


    /**
     *  Check if the user guesses the word correctly
     * @param wordGuessed the word the user guessed
     * @return true if the user guesses the word correctly
     */
    private boolean isCorrect(String wordGuessed) {
        int compare = currentWord.getSpelling().compareToIgnoreCase(wordGuessed);
        return compare == 0;
    }

    /**
     * Set the current word for guessing so that the store knows which word the player is guessing
     * @param newWord
     */
    private void setCurrentWord(Word newWord) {
        this.currentWord = newWord;
    }

    /**
     * Get a new word that is never been guessed from the word bank for the player to guess
     * @return a new word
     */
    private Word getANewWord() {
        return wordBank.getARandomWord();
    }

    /**
     * Get the player's score in this game
     *
     * @return the player's score in this game
     */
    public int getScore() {
        return scoreCalculator.getTotalScore();
    }

    public List<Character> getPuzzle() {
        return currentWord.getCurrentState();
    }
}
