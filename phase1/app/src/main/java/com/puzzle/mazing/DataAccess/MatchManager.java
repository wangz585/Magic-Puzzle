package com.puzzle.mazing.DataAccess;

import com.puzzle.mazing.Models.MatchBoard;

public class MatchManager {

    //    private GameState state;
//    User user;
    public MatchManager(){


    }
    private MatchBoard board;



    public void Restart(){
        board = new MatchBoard();
    }
    public void onLoad(){

/*        HashMap data = state.getdata();
        for (Animal animal: board.getAnimals()){
            animal.setvisibility(data.get("visibility"));
            animal.setAppearance(data.get("appearance"));
        }*/

    }

    public MatchBoard getBoard() {
        return board;
    }
}
