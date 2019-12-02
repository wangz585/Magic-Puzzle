package com.group0536.puzzlemazing.actions.games.crazymatch;

import android.media.MediaSync;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.webapi.ServerApi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CrazyMatchActionCreator extends ActionCreator implements CrazyMatchActions {
    private ServerApi serverApi;
    public CrazyMatchActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
        serverApi = ServerApi.getServerApi();
    }
    /**
     * A card at (row, col) is flipped
     * @param row row position of a card
     * @param col col position of a card
     */
    public void flip(int row, int col){
        Action action = new Action.ActionBuilder(FLIP)
                .load("row", row)
                .load("col", col)
                .build();
        dispatcher.dispatch(action);
    }

    /**
     * A match board is initialized based on the game level
     * @param level game level
     */
    public void initializeBoard(int level){
        Action action = new Action.ActionBuilder(INITIALIZE_BOARD)
                .load("level", level)
                .build();
        dispatcher.dispatch(action);
    }

    public void updateScore(String token, int level, int score){
        serverApi.performScoreUpdateCrazyMatch(token, level, score, new Callback() {
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
