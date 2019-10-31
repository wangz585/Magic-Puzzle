package com.puzzle.mazing.Models;

import java.util.HashMap;

public class GameState {
  private HashMap states;
  //    private Game currentGame;
  //    private User currentUser;

  //    public GameState(Game currentGame, User currentUser) {
  //        this.states = new HashMap<String, Integer> ();
  //        this.currentGame = currentGame;
  //        this.currentUser = currentUser;
  //    }

  //    public void uploadState(){
  //        // this should upload the state to the server
  //        JSONObject state_to_json = new JSONObject();
  //        try {
  //            state_to_json.put("GameStateForUser" + currentUser.getUserID() +
  // currentGame.getID(), states);
  //        } catch (JSONException e) {
  //            e.printStackTrace();
  //        }

  //        Http.post("https://apis.puzzlemazing.online/test/post", state_to_json, new Callback() {
  //            @Override
  //            public void onFailure(Call call, IOException e) {
  //                e.printStackTrace();
  //            }
  //
  //            @Override
  //            public void onResponse(Call call, Response response) throws IOException {
  //                final String res = response.body().string();
  //            }
  //        });

  //    }
  //
  //    public void setStates(String key, int value){
  //        states.put(key, value);
  //    }
  //    public HashMap getStates(){
  //        return states;
  //    }
  //    public boolean checkCurrentState(){
  //    }
}
