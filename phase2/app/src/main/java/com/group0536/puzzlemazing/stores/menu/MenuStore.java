package com.group0536.puzzlemazing.stores.menu;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.actions.menu.MenuPageActions;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;

/**
 * This is a menu store. It handles all the logic
 */
public class MenuStore extends Store implements MenuPageActions {
    private static MenuStore instance;
    private User player;

    protected MenuStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    /**
     * Get the instance of this store
     * @param dispatcher the dispatcher
     * @return the instance of this store
     */
    public static MenuStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new MenuStore(dispatcher);
        }
        return instance;
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return null;
    }

    @Override
    public void onAction(Action action) {

    }

    /**
     * Get the player of this game
     * @return the player of this game
     */
    public User getPlayer() {
        return player;
    }
}
