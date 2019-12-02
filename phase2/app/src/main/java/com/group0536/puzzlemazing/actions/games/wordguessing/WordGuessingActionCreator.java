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

    public void submitAnswer(String word){
        Action action = new Action.ActionBuilder(SUBMIT_ANSWER)
                .load("word", word)
                .build();
        dispatcher.dispatch(action);
    }

    public void startGame(){
        Action action = new Action.ActionBuilder(START_GAME).build();
        dispatcher.dispatch(action);
    }

    public void initializeWordBank(int level, Context context){
        Action action = new Action.ActionBuilder(INITIALIZE_WORDBANK)
                .load("level", level)
                .load("context", context)
                .build();
        dispatcher.dispatch(action);
    }

    public void timeOver() {
        Action action = new Action.ActionBuilder(TIME_OUT).build();
        dispatcher.dispatch(action);
    }

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
