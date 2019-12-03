package com.group0536.puzzlemazing.actions.games.crazymatch;

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
     * Initialize the game base on the level that the user selected and the tells the store
     * which challenge it is
     * @param level the game level the user selected
     * @param challenge the sequence of the crazy match game in all games
     *
     */
    public void initializeGame(int level, int challenge){
        Action action = new Action.ActionBuilder(INITIALIZE_GAME)
                .load("level", level)
                .load("challenge", challenge)
                .build();
        dispatcher.dispatch(action);
    }

    /**
     * Update the score to the server
     * @param token the token, which maps to a specific user
     * @param level the current level of the user
     * @param score the score of this game
     */
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
