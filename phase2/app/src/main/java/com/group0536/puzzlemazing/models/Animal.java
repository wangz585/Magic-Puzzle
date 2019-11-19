package com.group0536.puzzlemazing.models;

/**
 * An animal in the map
 */
public class Animal {
    private boolean flipped;
    private int animalSide;
    private int row;
    private int col;

    /**
     *
     * @param animalSide an integer representing the animal type
     * @param row an integer representing the row of this animal in the map
     * @param col an integer representing the col of this animal in the map
     */
    public Animal(int animalSide, int row, int col){
        flipped = false;
        animalSide = animalSide;
        row = row;
        col = col;
    }

    /**
     * Flip an animal
     */
    public void flipOver(){
        flipped = !flipped;
    }

    /**
     *
     * @return
     * flipped == true --> the map should show the animal side
     */
    public boolean isFlipped(){
        return flipped;
    }

    /**
     *
     * @return [row, col] for the position of this animal in the map
     */
    public int[] getPosition(){
        int[] position = new int[] {row, col};
        return position;
    }
}
