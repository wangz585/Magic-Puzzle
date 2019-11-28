package com.group0536.puzzlemazing.stores.scoreboard;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.actions.scoreboard.ScoreBoardActions;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;

import java.util.List;

public class ScoreBoardStore extends Store implements ScoreBoardActions {
    private List<List> usersWithScores;
    private static ScoreBoardStore instance;

    private ScoreBoardStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static ScoreBoardStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new ScoreBoardStore(dispatcher);
        }
        return instance;
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return null;
    }

    @Override
    public void onAction(Action action) {
        // There is only one action that a user can make in the score board page
        String scoreType = (String) action.getPayloadEntry("score_type");
        // based on the score type, fetch the top 3 users from the database
        // add each user and the score into the usersWithScore
        postChange();

    }

    public List<List> getUsersWithScores(){
        return usersWithScores;
    }
}
