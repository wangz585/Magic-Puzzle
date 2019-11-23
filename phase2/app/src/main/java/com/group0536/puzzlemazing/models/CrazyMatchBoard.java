package com.group0536.puzzlemazing.models;

import java.util.List;

public class CrazyMatchBoard {
    private List<List<Animal>> animals;
    private int row;
    private int col;
    private int numAnimals;

    /**
     * A CrazyMatchBoard object
     *
     * @param animals the animals on this board
     */
    public CrazyMatchBoard(List<List<Animal>> animals) {
        this.animals = animals;
        row = animals.size();
        col = animals.get(0).size();
        numAnimals = row * col;
    }

    /**
     * Get the number of rows of this board
     *
     * @return number of rows of this board
     */
    public int getNumberOfRows() {
        return row;
    }

    /**
     * Get the number of columns of this board
     *
     * @return number of columns of this board
     */
    public int getNumberOfColumns() {
        return col;
    }

    /**
     * Get the animal at (row, col)
     *
     * @param row the row of this board
     * @param col the column of this board
     * @return the animal at (row, col)
     */
    public Animal getAnimal(int row, int col) {
        return animals.get(row).get(col);
    }

    /**
     * Add animal onto this board
     *
     * @param animal animal to be added
     */
    public void insertAnimal(Animal animal) {
        int[] position = animal.getPosition();
        int row = position[0];
        int col = position[1];

        animals.get(row).set(col, animal);
    }

    /**
     * Remove animal from the board
     *
     * @param animal an animal which is matched and should disappear from the screen
     */
    public void RemoveAnimal(Animal animal) {
        int[] position = animal.getPosition();
        int row = position[0];
        int col = position[1];
        animals.get(row).set(col, null);
        numAnimals--;

    }

    /**
     * Get the number of animals on this board
     *
     * @return number of animals on this board
     */
    public int getNumberOfAnimals() {
        return numAnimals;
    }
}
