package com.group0536.puzzlemazing.stores.wordguessing;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.wordguessing.WordGuessingActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.models.wordguessing.ScoreCalculator;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.HashMap;

public class WordGuessingGameStore extends Store implements WordGuessingActions {
    private WordGuessingInitController initController = new WordGuessingInitController();
    private User player;
    private ScoreCalculator scoreCalculator;
    private static com.group0536.puzzlemazing.stores.wordguessing.WordGuessingGameStore instance;

    protected WordGuessingGameStore(Dispatcher dispatcher) {
        super(dispatcher);
        // TODO: pass in a player
//        this.player = player;
    }

    /**
     * Get an instance of this store
     *
     * @param dispatcher the dispatcher associated
     * @return an instance of this store
     */
    public static com.group0536.puzzlemazing.stores.wordguessing.WordGuessingGameStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new com.group0536.puzzlemazing.stores.wordguessing.WordGuessingGameStore(dispatcher);
        }
        return instance;
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new WordGuessingChangeEvent();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case SHOW_HINT:
                break;
            case CHECK:
                break;
            case SKIP:
                break;

        }
    }

    public int getContentView(int level) {
        HashMap<Object, Object> levelData = initController.getLevelData(level);
        return (int) levelData.get("ContentView");
    }


    /**
     * Get the player's score in this game
     *
     * @return the player's score in this game
     */
    public int getScore() {
        return scoreCalculator.getTotalScore();
    }
}
