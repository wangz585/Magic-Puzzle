package com.group0536.puzzlemazing.models.crazymatch;

import androidx.annotation.Nullable;

/**
 * An animal in the map
 */
public class Animal {
    private boolean flipped;
    private int animalSide;
    private int row;
    private int col;

    /**
     * @param animalSide an integer representing the animal type
     * @param row        an integer representing the row of this animal in the map
     * @param col        an integer representing the col of this animal in the map
     */
    public Animal(int animalSide, int row, int col) {
        flipped = false;
        this.animalSide = animalSide;
        this.row = row;
        this.col = col;
    }

    /**
     * Flip an animal
     */
    public void flipOver() {
        flipped = !flipped;
    }

    /**
     * @return flipped == true --> the map should show the animal side
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * @return [row, col] for the position of this animal in the map
     */
    public int[] getPosition() {
        return new int[]{row, col};
    }

    /**
     * @return animalSide
     */
    public int getAnimalSide() {
        return animalSide;
    }

    /**
     * @param obj the object being compared to
     * @return true iff two animals are the same animal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Animal) {
            Animal another_animal = (Animal) obj;
            return (another_animal.getAnimalSide() == getAnimalSide());
        } else {
            return false;
        }
    }
}
