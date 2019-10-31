package com.puzzle.mazing.Games;

import java.util.HashMap;

public class GameRecord {
  private HashMap historicMaxPoints;
  private HashMap allGameStates;
  private int point;
  private int level;
  //    private User currentUser;

  //    public GameRecord(String gameID1, String gameID2, String gameID3, User currentUser) {
  //        this.historicMaxPoints = new HashMap<String, Integer>();
  //        this.allGameStates = new HashMap<String, GameState>();
  //        this.point = 0;
  //        this.level = 1;
  //
  //        //initialize the game states
  //        this.allGameStates.put(gameID1, new GameState(currentUser));
  //        this.allGameStates.put(gameID2, new GameState(currentUser));
  //        this.allGameStates.put(gameID3, new GameState(currentUser));
  //
  //        //initialize the historic game max points
  //        this.historicMaxPoints.put(gameID1, 0);
  //        this.historicMaxPoints.put(gameID2, 0);
  //        this.historicMaxPoints.put(gameID3, 0);
  //    }

  public int getLevel() {
    return level;
  }

  public int getPoint() {
    return point;
  }
}
