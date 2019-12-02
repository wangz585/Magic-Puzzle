package com.group0536.puzzlemazing.actions.global;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.webapi.ServerApi;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ScoreBoardActionCreator extends ActionCreator implements ScoreBoardActions {
    private ServerApi serverApi;

    public ScoreBoardActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
        serverApi = ServerApi.getServerApi();
    }

    public void chooseScoreType(String scoreType){
        JSONObject top3PlayersWithScores = serverApi.getTop3PlayersWithScores(scoreType, new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
        Action action = new Action.ActionBuilder(SELECT_SCORE_TYPE)
                .load("score_type", scoreType)
                .load("top3data", top3PlayersWithScores)
                .build();
        dispatcher.dispatch(action);
    }
}
