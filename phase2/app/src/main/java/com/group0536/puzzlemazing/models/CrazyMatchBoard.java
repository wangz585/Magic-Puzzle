package com.group0536.puzzlemazing.models;

import java.util.List;

public class CrazyMatchBoard {
    private List<List<Animal>> animals;

    /**
     *
     * @param animals a map stores all animals in the map
     */
    public CrazyMatchBoard(List<List<Animal>> animals){
        animals = animals;
    }

    /**
     *
     * @param row
     * @param col
     * @return the animal at (row, col) in the map
     */
    public Animal getAnimal(int row, int col){
        return animals.get(row).get(col);
    }

    /**
     *
     * @param animal
     * Add an new animal into the map at location (row, col)
     */
    public void insertAnimal(Animal animal){
        int[] position = animal.getPosition();
        int row = position[0];
        int col = position[1];

        animals.get(row).set(col, animal);
    }

    /**
     *
     * @param animal An animal which is matched and should disappear from the screen
     */
    public void crossOutAnimal(Animal animal){
        int [] position = animal.getPosition();
        int row = position[0];
        int col = position[1];

        animals.get(row).set(col, null);
    }
}
