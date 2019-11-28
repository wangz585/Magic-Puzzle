package com.group0536.puzzlemazing.stores;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;

public abstract class GameStore extends Store {

    private User player;
    private int score;

    protected GameStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return null;
    }

    @Override
    public void onAction(Action action) {

    }

    private void initScore(){

    }

    private void initPlayer(User player){
        this.player = player;
    }
}
