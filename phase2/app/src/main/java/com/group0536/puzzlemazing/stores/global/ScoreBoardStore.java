package com.group0536.puzzlemazing.stores.global;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.actions.global.ScoreBoardActions;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a store object that holds information relating to the scoreboard. It gets JSON objects
 * from the server and parse them.
 */
public class ScoreBoardStore extends Store implements ScoreBoardActions {
    private List<List> usersWithScores;
    private static ScoreBoardStore instance;
    private String scoreType;

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
        return new ScoreBoardStoreChangeEvent();
    }

    @Override
    public void onAction(Action action) {
        // There is only one action that a user can make in the score board page
        scoreType = (String) action.getPayloadEntry("score_type");
        // based on the score type, fetch the top 3 users from the database
        // add each user and the score into the usersWithScore
        JSONArray top3PlayersData = (JSONArray) action.getPayloadEntry("top3data");
        List<List> list = new ArrayList<>();

        for(int i = 0; i < top3PlayersData.length(); i++){
            JSONObject object = null;
            try {
                object = top3PlayersData.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String userName = null;
            try {
                userName = object.get("userName").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String userScore = null;
            try {
                userScore = object.get("score").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            List temp = new ArrayList();
            temp.add(userName);
            temp.add(Integer.parseInt(userScore));
            list.add(temp);
        }
        setUsersWithScores(list);
        postChange();

    }

    public List<List> getUsersWithScores(){
        return usersWithScores;
    }

    public String getScoreType() {
        return scoreType;
    }

    private void setUsersWithScores(List<List> usersWithScores) {
        this.usersWithScores = usersWithScores;
    }
}
