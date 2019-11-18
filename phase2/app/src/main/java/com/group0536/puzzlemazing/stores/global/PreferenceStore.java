package com.group0536.puzzlemazing.stores.global;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.welcome.AppInitializeActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

public class PreferenceStore extends Store implements AppInitializeActions {
    private String savedToken;

    protected PreferenceStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new PreferenceStoreChangeEvent();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {

    }
}
