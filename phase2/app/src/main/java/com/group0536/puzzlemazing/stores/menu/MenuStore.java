package com.group0536.puzzlemazing.stores.menu;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.actions.menu.MenuActions;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;


public class MenuStore extends Store implements MenuActions {
    private static MenuStore instance;
    private User user;

    protected MenuStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static MenuStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new MenuStore(dispatcher);
        }
        return instance;
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new MenuStoreChangeEvent();
    }

    @Override
    public void onAction(Action action) {

    }

    /**
     * Get the player of this game
     * @return the player of this game
     */
    public User getUser() {
        return user;
    }
}
