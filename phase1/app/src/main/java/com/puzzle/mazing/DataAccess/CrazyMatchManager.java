package com.puzzle.mazing.DataAccess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.puzzle.mazing.Activities.CrazyMatchActivity;
import com.puzzle.mazing.Models.Animal;
import com.puzzle.mazing.Models.CrazyMatchBoard;
import com.puzzle.mazing.R;

import androidx.appcompat.app.AlertDialog;

public class CrazyMatchManager {

    //    private GameState state;
//    User user;
    private CrazyMatchBoard board;
    public CrazyMatchManager(){


    }


    public void restart(ImageView[] buttonPos){
        board = new CrazyMatchBoard(buttonPos);
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

    /**
     * Check whether the game end or not
     */
    public boolean End(){
        ImageView[] buttonPos = board.getButtonPos();
        return buttonPos[0].getVisibility() == View.INVISIBLE &&
                buttonPos[1].getVisibility() == View.INVISIBLE &&
                buttonPos[2].getVisibility() == View.INVISIBLE &&
                buttonPos[3].getVisibility() == View.INVISIBLE &&
                buttonPos[4].getVisibility() == View.INVISIBLE &&
                buttonPos[5].getVisibility() == View.INVISIBLE &&
                buttonPos[6].getVisibility() == View.INVISIBLE &&
                buttonPos[7].getVisibility() == View.INVISIBLE &&
                buttonPos[8].getVisibility() == View.INVISIBLE &&
                buttonPos[9].getVisibility() == View.INVISIBLE &&
                buttonPos[10].getVisibility() == View.INVISIBLE &&
                buttonPos[11].getVisibility() == View.INVISIBLE ;
    }

}
