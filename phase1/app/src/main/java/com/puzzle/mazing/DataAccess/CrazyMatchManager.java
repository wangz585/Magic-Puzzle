package com.puzzle.mazing.DataAccess;

import com.puzzle.mazing.Models.Animal;
import com.puzzle.mazing.Models.CrazyMatchBoard;

public class CrazyMatchManager {

    //    private GameState state;
//    User user;
    private CrazyMatchBoard board;
    public CrazyMatchManager(){
    restart();
    }


    public void restart(){
        board = new CrazyMatchBoard();
    }

    public CrazyMatchBoard getBoard() {
        return board;
    }
/*    public void onLoad(){

        HashMap data = state.getdata();
        for (Animal animal: board.getAnimals()){
            animal.setVisibility(data.get("visibility"));
            animal.setAppearance(data.get("appearance"));
        }

    }*/


}
