package com.puzzle.mazing.DataAccess;

import static com.example.flyingbirds.BusyWorkerGameView.CELL_NUM_PER_LINE;

/**

 * Created items in the bitmap of the game.

 */

public class GameState {

    private int workerRow;

    private int workerCol;

    private int boxRow;

    private int boxCol;

    private int flagRow;

    private int flagCol;


    public int getWorkerRow() {
        return workerRow;
    }

    public int getWorkerCol() {
        return workerCol;
    }

    public int getBoxRow() {
        return boxRow;
    }

    public int getBoxCol() {
        return boxCol;
    }

    public void setBoxCol(int boxColumn) {
        boxCol = boxColumn;
    }

    public void setBoxRow(int boxRow) {
        this.boxRow = boxRow;
    }

    public void setManRow(int manRow) {
        workerRow = manRow;
    }

    public void setManColumn(int manColumn) {
        workerCol = manColumn;
    }


    private StringBuffer[] mLabelInCells;

    //initialize the GameState for a specific level.

    public GameState(String[] initialState){

        mLabelInCells = new StringBuffer[initialState.length];
        for (int i = 0; i < initialState.length; i++)
            mLabelInCells[i] = new StringBuffer(initialState[i]);
        get_initial_position_worker();

        //there is only one box at the starting point.
        get_initial_position_box();
        get_initial_position_flag();
    }


    public boolean isGameOver(){
        return boxRow == flagRow && boxCol == flagCol;
    }

    public boolean loseGame() { return (boxRow == CELL_NUM_PER_LINE - 2 && boxCol == CELL_NUM_PER_LINE - 2)
    || (boxRow == 1 && boxCol == 1) ||(boxRow == CELL_NUM_PER_LINE - 2 && boxCol == 1) ||
            (boxRow == 1 && boxCol == CELL_NUM_PER_LINE - 2);
    }


    private void get_initial_position_flag() {
        for (int r = 0; r < CELL_NUM_PER_LINE; r++)
            for (int c = 0; c < CELL_NUM_PER_LINE; c++){
                if (mLabelInCells[r].charAt(c) == 'F')  {
                    flagRow = r;
                    flagCol = c;
                    return;
                }
            }
    }



    //get the cells of the current GameState
    public StringBuffer[] getLabelInCells() {
        return mLabelInCells;
    }

    //initialize the worker's position according to the game
    public void get_initial_position_worker() {
        for (int a = 0; a < CELL_NUM_PER_LINE; a++)
            for (int b = 0; b < CELL_NUM_PER_LINE; b++){
                if (mLabelInCells[a].charAt(b) == 'M')  {
                    workerRow = a;
                    workerCol = b;
                    return;
                }
            }
    }



    //initialize the starting position of the box
    public void get_initial_position_box(){
        for (int r = 0; r< CELL_NUM_PER_LINE; r++)
            for (int c = 0; c < CELL_NUM_PER_LINE; c++){
                if (mLabelInCells[r].charAt(c) == 'B'){
                    boxRow = r;
                    boxCol = c;
                    return;
                }
            }
    }

    //The way to handle when the box is on the left side of the man.
    public void handleLeft() {
        if (isBoxLeftToMan()){
            if (boxCol > 0  && mLabelInCells[boxRow].charAt(boxCol - 1) != 'W'){
                mLabelInCells[boxRow].setCharAt(boxCol, ' ');
                mLabelInCells[boxRow].setCharAt(boxCol - 1, 'B');
                boxCol--;
                mLabelInCells[workerRow].setCharAt(workerCol, ' ');
                mLabelInCells[workerRow].setCharAt(workerCol - 1, 'M');
                workerCol--;
            }
        }else if (workerCol > 0 && mLabelInCells[workerRow].charAt(workerCol - 1) != 'W') {
            mLabelInCells[workerRow].setCharAt(workerCol, ' ');
            mLabelInCells[workerRow].setCharAt(workerCol - 1, 'M');
            workerCol--;
        }
    }



    // To identify whether the box is on the left of the man.
    public boolean isBoxLeftToMan() {
        return boxRow == workerRow && boxCol == workerCol - 1;
    }


    //The way to handle when the box is above the man.
    public void handleAbove() {
        if (isBoxAboveMan()){
            if (boxRow > 0 && mLabelInCells[boxRow - 1].charAt(boxCol) != 'W'){
                //The box has not reach the edge of the map
                //move the box, and modified the changing index in the matrix representing the map
                mLabelInCells[boxRow].setCharAt(boxCol, ' ');
                mLabelInCells[boxRow - 1].setCharAt(boxCol, 'B');
                boxRow--;

                //Record the movement of the worker, and modified his position
                mLabelInCells[workerRow].setCharAt(workerCol, ' ');
                mLabelInCells[workerRow - 1].setCharAt(workerCol, 'M');
                workerRow--;
            }
        } else if (workerRow > 0 && mLabelInCells[workerRow - 1].charAt(workerCol) != 'W') {
            mLabelInCells[workerRow].setCharAt(workerCol, ' ');
            mLabelInCells[workerRow - 1].setCharAt(workerCol, 'M');
            workerRow--;
        }
    }


    // To identify whether the box is above the man.
    public boolean isBoxAboveMan() {
        return boxCol == workerCol && boxRow == workerRow - 1;
    }


    //The way to handle when the box is below the man.
    public void handleDown() {
        if (isBoxBlowMan()) {
            if (boxRow + 1 < CELL_NUM_PER_LINE && mLabelInCells[boxRow + 1].charAt(boxCol) != 'W') {
                mLabelInCells[boxRow].setCharAt(boxCol, ' ');
                mLabelInCells[boxRow + 1].setCharAt(boxCol, 'B');
                boxRow++;

                mLabelInCells[workerRow].setCharAt(workerCol, ' ');
                mLabelInCells[workerRow + 1].setCharAt(workerCol, 'M');
                workerRow++;
            }
        } else if (workerRow + 1 < CELL_NUM_PER_LINE && mLabelInCells[workerRow + 1].charAt(workerCol) != 'W') {
            mLabelInCells[workerRow].setCharAt(workerCol, ' ');
            mLabelInCells[workerRow + 1].setCharAt(workerCol, 'M');
            workerRow++;
        }
    }

    // To identify whether the box is below the man.
    public boolean isBoxBlowMan() {
        return boxCol == workerCol && boxRow == workerRow + 1;
    }

    //The way to handle when the box is on the right of the man.
    public void handleRight() {
        if (isBoxRightToMan()) {
            if (boxCol + 1 < CELL_NUM_PER_LINE  && mLabelInCells[boxRow].charAt(boxCol + 1) != 'W') {
                mLabelInCells[boxRow].setCharAt(boxCol, ' ');
                mLabelInCells[boxRow].setCharAt(boxCol + 1, 'B');
                boxCol++;
                mLabelInCells[workerRow].setCharAt(workerCol, ' ');
                mLabelInCells[workerRow].setCharAt(workerCol + 1, 'M');
                workerCol++;
            }
        } else if (workerCol + 1 < CELL_NUM_PER_LINE && mLabelInCells[workerRow].charAt(workerCol + 1) != 'W') {
            mLabelInCells[workerRow].setCharAt(workerCol, ' ');
            mLabelInCells[workerRow].setCharAt(workerCol + 1, 'M');
            workerCol++;
        }
    }


    // To identify whether the box is on the right of the man.
    public boolean isBoxRightToMan() {
        return boxRow == workerRow && boxCol == workerCol + 1;
    }
}


