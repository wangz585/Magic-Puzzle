package com.group0536.puzzlemazing.stores.menu;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.menu.MenuActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;

/**
 * This is a store object that which holds specific information relating
 * to the menu of of the game. The menu does not contain many information now, but information may
 * be added in the future.
 */
public class MenuStore extends Store implements MenuActions {
    private static MenuStore instance;

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

}
