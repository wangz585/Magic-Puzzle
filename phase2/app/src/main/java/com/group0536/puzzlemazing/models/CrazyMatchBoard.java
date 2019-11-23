package com.group0536.puzzlemazing.models;

import java.util.List;

public class CrazyMatchBoard {
    private List<List<Animal>> animals;
    private int row;
    private int col;
    private int numAnimals;

    /**
     * @param animals a map stores all animals in the map
     */
    public CrazyMatchBoard(List<List<Animal>> animals) {
        this.animals = animals;
        row = animals.size();
        col = animals.get(0).size();
        numAnimals = row * col;
    }

    public int getNumRow() {
        return row;
    }

    public int getNumColumn() {
        return col;
    }

    /**
     * @param row the row of the board
     * @param col the column of the board
     * @return the animal at (row, col) in the map
     */
    public Animal getAnimal(int row, int col) {
        return animals.get(row).get(col);
    }

    /**
     * @param animal Add an new animal into the map at location (row, col)
     */
    public void insertAnimal(Animal animal) {
        int[] position = animal.getPosition();
        int row = position[0];
        int col = position[1];

        animals.get(row).set(col, animal);
    }

    /**
     * @param animal An animal which is matched and should disappear from the screen
     */
    public void crossOutAnimal(Animal animal) {
        int[] position = animal.getPosition();
        int row = position[0];
        int col = position[1];
        animals.get(row).set(col, null);
        numAnimals --;

    }

    public int getNumAnimal() {
        return numAnimals;
    }
}
