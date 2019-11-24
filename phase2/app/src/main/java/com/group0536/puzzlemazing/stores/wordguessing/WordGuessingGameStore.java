package com.group0536.puzzlemazing.stores.wordguessing;

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

import java.util.HashMap;
import java.util.List;

public class WordGuessingGameStore extends Store implements WordGuessingActions {
    private WordGuessingInitController initController = new WordGuessingInitController();
    private User player;
    private WordBank wordBank;
    private Word currentWord;
    private boolean gameOver;
    private int score;
    private ScoreCalculator scoreCalculator;
    private static com.group0536.puzzlemazing.stores.wordguessing.WordGuessingGameStore instance;

    protected WordGuessingGameStore(Dispatcher dispatcher) {
        super(dispatcher);
        // TODO: pass in a player
//        this.player = player;
        this.currentWord = null;
        this.gameOver = false;
        this.wordBank = null;
        this.score = 0;
//        this.scoreCalculator = null;
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

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
//            case TIME_OVER:
//                setGameOver();
            case START_GAME:
                Word newWord = getANewWord();
                setCurrentWord(newWord);
                postChange();
                break;
            case SUBMIT_ANSWER:
                List<Character> userInput = (List<Character>) action.getPayloadEntry("User Input");
                if(isCorrect(userInput)){
                    // if the answer is correct
                    // updateScore();
                    // if the game is finished
                    if(isGameOver()){
                        // time is out, the user cannot longer click the submit button

                    }
                    else{
                        // there is time left
                        // clear the user input
                        // give a new word

                    }
                }
                else{
                    // if the answer is not correct
                    // clear over the user input

                }
                postChange();
                break;
//            case INITIALIZE_WORDBANK:
//                initializeWordBank((int) action.getPayloadEntry("level"));
//                postChange();
//                break;
        }
    }

    /**
     * Initialize a word bank based on the game level
     * @param level
     */
    private void initializeWordBank(int level) {
        if (level == 1){

        }
        else{
            // if level is 2
        }
    }

    /**
     *  Check if the user guesses the word correctly
     * @param userInput user's input
     * @return true if the user guesses the word correctly
     */
    private boolean isCorrect(List<Character> userInput) {
        List<Character> missingChars = currentWord.getMissingChars();
        if(userInput.size() != missingChars.size()){
            return false;
        }
        for (int i = 0; i < userInput.size(); i++){
            if (!userInput.get(i).equals(missingChars.get(i))){
                return false;
            }
        }
        return true;
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
        Word newWord = wordBank.getARandomWord();
        return newWord;
    }

    public int getContentView(int level) {
        HashMap<Object, Object> levelData = initController.getLevelData(level);
        return (int) levelData.get("ContentView");
    }


    /**
     * Get the player's score in this game
     *
     * @return the player's score in this game
     */
    public int getScore() {
        return scoreCalculator.getTotalScore();
    }
}
