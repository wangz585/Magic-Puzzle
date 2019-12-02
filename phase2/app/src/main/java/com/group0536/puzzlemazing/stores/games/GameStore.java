package com.group0536.puzzlemazing.stores.games;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;

public class GameStore extends Store {
    private User user;

    protected GameStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new GameStoreChangeEvent();
    }

    @Override
    public void onAction(Action action) {

    }
}
