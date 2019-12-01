package com.group0536.puzzlemazing.stores.crazymatch;

import android.util.SparseArray;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.crazymatch.CrazyMatchActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.models.crazymatch.Animal;
import com.group0536.puzzlemazing.models.crazymatch.Board;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CrazyMatchStore extends Store implements CrazyMatchActions {
    private Board board;
    private int score;
    private Animal firstFlip;
    private Animal secondFlip;
    private User player;
    private int stepsTaken;
    private static List<Integer> allAnimals;
    private SparseArray<List<Integer>> levelToDimension;
    private static CrazyMatchStore instance;
    private boolean isWaiting;
    private CrazyMatchInitController initController = new CrazyMatchInitController();

    protected CrazyMatchStore(Dispatcher dispatcher) {
        super(dispatcher);
        populateAnimalDrawables();
        // TODO: pass in a player
//        this.player = player;
        setLevelToDimension();
    }

    /**
     * Get an instance of this store
     *
     * @param dispatcher the dispatcher associated
     * @return an instance of this store
     */
    public static CrazyMatchStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new CrazyMatchStore(dispatcher);
        }
        return instance;
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new CrazyMatchChangeEvent();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case FLIP:
                flipButtons(action);
                break;
            case INITIALIZE_BOARD:
                initializeBoard((int) action.getPayloadEntry("level"));
                postChange();
                break;
        }
    }

    /**
     * Initialize the HashMap that maps level to the dimension of the game board
     */
    private void setLevelToDimension() {
        List<Integer> dim1 = new ArrayList<>();
        dim1.add(2);
        dim1.add(3);
        levelToDimension = new SparseArray<>();
        levelToDimension.put(1, dim1);

        List<Integer> dim2 = new ArrayList<>();
        dim2.add(2);
        dim2.add(4);
        levelToDimension.put(2, dim2);
    }

    /**
     * Populate the animal drawables. This is all animal drawables that could be used.
     */
    private static void populateAnimalDrawables() {
        allAnimals = new ArrayList<>(Arrays.asList(R.drawable.match_butterfly,
                R.drawable.match_chicken,
                R.drawable.match_cow,
                R.drawable.match_dog,
                R.drawable.match_lizard,
                R.drawable.match_fish,
                R.drawable.match_fox,
                R.drawable.match_frog,
                R.drawable.match_ladybug,
                R.drawable.match_lion,
                R.drawable.match_octopus,
                R.drawable.match_owl,
                R.drawable.match_reindeer,
                R.drawable.match_robin,
                R.drawable.match_snail,
                R.drawable.match_turkey));
    }

    /**
     * Get the board of this store
     *
     * @return board of this store
     */
    public Board getBoard() {
        return board;
    }

    public int getContentView(int level) {
        HashMap<Object, Object> levelData = initController.getLevelData(level);
        return (int) levelData.get("ContentView");
    }

    /**
     * Flip the game_button according to the pay load entry of action
     *
     * @param action action Object of the action being processed
     */
    private void flipButtons(Action action) {
        stepsTaken++;
        int row = (int) action.getPayloadEntry("row");
        int col = (int) action.getPayloadEntry("col");
        flipAnimal(row, col);
        postChange();
        // if two animals are flipped
        if ((firstFlip != null) && (secondFlip != null)) {
            flipBackAnimals();
        }
        // do nothing since the user just flip one animal in the board
    }

    /**
     * Flip back the animals
     */
    private void flipBackAnimals() {
        Timer timerCheckPairs = new Timer();
        isWaiting = true;
        timerCheckPairs.schedule(new TimerTask() {
            @Override
            public void run() {
                resetAnimals();
                postChange();
                isWaiting = false;
            }
        }, 1000);
    }

    /**
     * Reset the animals and the statistics associated
     */
    private void resetAnimals() {
        firstFlip.flipOver();
        secondFlip.flipOver();
        if (isTheSame()) {
            updateScore();
            stepsTaken = 0;
            removeAnimal(firstFlip);
            removeAnimal(secondFlip);
        }
        clearFlipPair();
    }

    /**
     * Check if the Animal object at (row, col) can be flipped
     *
     * @param row The row of the Animal object being checked
     * @param col The column of the Animal object being checked
     * @return if the Animal object can be flipped
     */
    public boolean canFlip(int row, int col) {
        Animal animal = board.getAnimal(row, col);
        return (!isWaiting && animal != null && !animal.isFlipped());
    }


    /**
     * Check if the game is over
     *
     * @return if the game is over
     */
    public boolean isGameOver() {
        return board.getNumberOfAnimalsLeft() == 0;
    }

    /**
     * Initialize the board of this store
     *
     * @param level the level of the game
     */
    private void initializeBoard(int level) {
        List<Integer> dimension = levelToDimension.get(level);
        int row = dimension.get(0);
        int col = dimension.get(1);
        List<List<Animal>> animals = GenerateAnimalsList(row, col);
        board = new Board(animals);
    }

    /**
     * Get a random list of Animal objects of that contains numRows sublists, each sublist
     * contains numColumns Animals object
     *
     * @param numRows    number of sublists that the returned list contains
     * @param numColumns number of Animal objects that each sublist contains
     * @return list of Animal objects
     */
    private List<List<Animal>> GenerateAnimalsList(int numRows, int numColumns) {
        int numPair = numRows * numColumns / 2;
        List<Integer> randomAnimals = generateRandomAnimalDrawable(numPair);
        Collections.shuffle(randomAnimals);
        List<List<Animal>> animals = new ArrayList<>();
        int i = 0;
        for (int j = 0; j < numRows; j++) {
            List<Animal> animalsRow = new ArrayList<>();
            // Generate a random row
            for (int k = 0; k < numColumns; k++) {
                animalsRow.add(new Animal(randomAnimals.get(i), j, k));
                i++;
            }
            animals.add(animalsRow);
        }

        return animals;
    }

    /**
     * Generate a random list that contains numAnimal integers, each integer corresponds to
     * an animal drawable
     *
     * @param numAnimal the number of animals
     * @return list of randomly generated integers
     */
    private List<Integer> generateRandomAnimalDrawable(int numAnimal) {
        List<Integer> animalsClone = new ArrayList<>(allAnimals);

        Collections.shuffle(animalsClone);
        List<Integer> randomAnimals = new ArrayList<>();
        for (int i = 0; i < numAnimal; i++) {
            randomAnimals.add(animalsClone.get(i));
            randomAnimals.add(animalsClone.get(i));
        }
        return randomAnimals;
    }

    private void clearFlipPair() {
        firstFlip = null;
        secondFlip = null;
    }

    /**
     * Remove animal from board
     *
     * @param animal an animal object to be removed from board
     */
    private void removeAnimal(Animal animal) {
        board.RemoveAnimal(animal);
    }

    /**
     * Update the score
     */
    private void updateScore() {
        score += Math.round(35 * board.getNumberOfAnimalsLeft() / stepsTaken);
    }

    /**
     * Get the player's score in this game
     *
     * @return the player's score in this game
     */
    public int getScore() {
        return score;
    }

    /**
     * Check if the two animals being flipped are the same
     *
     * @return if the two animals being flipped are the same
     */
    private boolean isTheSame() {
        return firstFlip.equals(secondFlip);
    }


    /**
     * Flip the animal at (row, col)
     *
     * @param row row of the board of this store
     * @param col column of the board of this store
     */
    private void flipAnimal(int row, int col) {
        Animal animal = board.getAnimal(row, col);
        animal.flipOver();
        stepsTaken += 1;
        if (firstFlip == null) {
            firstFlip = animal;
        } else if (secondFlip == null) {
            secondFlip = animal;
        }
    }

    /**
     * Get the number of rows of the board of this store
     *
     * @return number of rows
     */
    public int getNumberOfRows() {
        return board.getNumberOfRows();
    }

    /**
     * Get the number of columns of the board of this store
     *
     * @return number of columns
     */
    public int getNumberOfColumns() {
        return board.getNumberOfColumns();
    }
}
