package com.puzzle.mazing.Models;

import com.puzzle.mazing.Models.Animal;
import com.puzzle.mazing.R;

public class MatchBoard {

    private Animal[] animals;


    public MatchBoard(){
        setZoo();

    }

    private void setZoo(){

        Animal chicken1 = new Animal(R.drawable.chicken,0, "chicken");
        Animal chicken2 = new Animal(R.drawable.chicken, 6,"chicken");
        Animal cow1 = new Animal(R.drawable.cow,1,"cow");
        Animal cow2 = new Animal(R.drawable.cow,7,"cow");
        Animal fox1 = new Animal(R.drawable.fox,2,"fox");
        Animal fox2 = new Animal(R.drawable.fox,8,"fox");
        Animal snail1 = new Animal(R.drawable.snail,3,"snail");
        Animal snail2 = new Animal(R.drawable.snail, 9,"snail");
        Animal owl1 = new Animal(R.drawable.owl, 4,"owl");
        Animal owl2 = new Animal(R.drawable.owl,10,"owl");
        Animal rein1 = new Animal(R.drawable.chicken, 5, "reindeer");
        Animal rein2 = new Animal(R.drawable.reindeer, 11, "reindeer");
        animals = new Animal[]{chicken1, cow1, fox1,snail1,owl1,rein1,chicken2,cow2,fox2,snail2,owl2,rein2};

    }

    /*
    * Set the Animals in the forest
     */
    public void setAnimals(Animal[] animals) {
        this.animals = animals;
    }

    public Animal[] getAnimals() {
        return animals;
    }
}
