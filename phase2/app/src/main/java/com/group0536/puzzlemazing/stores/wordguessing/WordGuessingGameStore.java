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
                        // the user should be able to go to somewhere else
                    }
                    else{
                        // there is time left
                        // clear the user input
                        // give a new word
                        currentWord.setGuessed(true);
                        currentWord = getANewWord();
                    }
                }
                else{
                    // if the answer is not correct
                    // clear over the user input
                }
                postChange();
                break;
            case INITIALIZE_WORDBANK:
                initializeWordBank((int) action.getPayloadEntry("level"),
                        (Context) action.getPayloadEntry("context"));
                postChange();
                break;
        }
    }

    /**
     * Initialize a word bank based on the game level
     * @param level
     * @param context
     */
    private void initializeWordBank(int level, Context context) {
            wordBank = new WordBank(getWordList(level, context));
        }

    private List<Word> getWordList(int level, Context context) {
        List<Word> wordsInBank = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    context.getAssets().open("wordGuessingLevel"+level+".txt")));
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] words = line.split("--");
                List<List<Character>> splicedWord = splitWord(words[0], level);
                Word newWord = new Word(words[0],
                        splicedWord.get(0),
                        splicedWord.get(1),
                        words[1]);
                wordsInBank.add(newWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsInBank;
    }

    /**
     * Split a word into two part: missing chars and shown chars to the user
      * @param word
     * @return a list of two list of characters
     */
    private List<List<Character>> splitWord(String word, int level) {
        List<List<Character>> splicedWord = new ArrayList<>();
        List<Character> shownChars = new ArrayList<>();
        List<Character> missingChars = new ArrayList<>();

        int wordLength;
        if(level == 1){
            wordLength = 6;
        }
        else{
            wordLength = 8;
        }

        for(int i = 0; i < wordLength; i++){
            if(i % 2 == 0){
                shownChars.add(word.charAt(i));
            }
            else{
                missingChars.add(word.charAt(i));
            }
        }
        splicedWord.add(shownChars);
        splicedWord.add(missingChars);
        return splicedWord;
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
