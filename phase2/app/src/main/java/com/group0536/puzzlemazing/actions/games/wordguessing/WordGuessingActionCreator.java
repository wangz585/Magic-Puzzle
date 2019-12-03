package com.group0536.puzzlemazing.actions.games.wordguessing;

import android.content.Context;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.webapi.ServerApi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WordGuessingActionCreator extends ActionCreator implements WordGuessingActions {
    private ServerApi serverApi;
    public WordGuessingActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
        serverApi = ServerApi.getServerApi();
    }

    /**
     * A player submit a word
     * @param word user's input
     */
    public void submitAnswer(String word){
        Action action = new Action.ActionBuilder(SUBMIT_ANSWER)
                .load("word", word)
                .build();
        dispatcher.dispatch(action);
    }

    /**
     * Start the game
     */
    public void startGame(){
        Action action = new Action.ActionBuilder(START_GAME).build();
        dispatcher.dispatch(action);
    }

    /**
     * Initialize the word bank based on game level
     * @param level chosen game level
     * @param context game context
     */
    public void initializeGame(int level, Context context, int challenge){
        Action action = new Action.ActionBuilder(INITIALIZE_GAME)
                .load("level", level)
                .load("context", context)
                .load("challenge", challenge)
                .build();
        dispatcher.dispatch(action);
    }

    /**
     * The timer runs out of the time
     */
    public void timeOver() {
        Action action = new Action.ActionBuilder(TIME_OUT).build();
        dispatcher.dispatch(action);
    }

    /**
     * Update score to the server
     * @param currentLevel the chosen game level
     * @param score the player's score
     * @param token the player's token
     */
    public void updateScore(int currentLevel, int score, String token) {
        serverApi.performScoreUpdateWordGuessing(token, currentLevel, score, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

}
