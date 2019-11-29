package com.group0536.puzzlemazing.stores.menu;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.actions.menu.MenuPageActions;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;

public class MenuPageStore extends Store implements MenuPageActions {
    private static MenuPageStore instance;
    private User player;

    protected MenuPageStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static MenuPageStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new MenuPageStore(dispatcher);
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

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }
}
