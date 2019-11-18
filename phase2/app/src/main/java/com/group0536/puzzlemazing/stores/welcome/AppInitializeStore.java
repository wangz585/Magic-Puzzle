package com.group0536.puzzlemazing.stores.welcome;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.welcome.AppInitializeActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

public class AppInitializeStore extends Store implements AppInitializeActions {
    private static AppInitializeStore instance;

    // Steps required to initialize application
    private boolean isUpdateCheckDone;
    private boolean isLoadSavedTokenDone;
    private boolean isLogInUserDone;

    // errorMessage associating with errors. It is empty if no error occurred.
    private String errorMessage;

    // other information during app init process
    private String savedToken;

    public static AppInitializeStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new AppInitializeStore(dispatcher);
        }
        return instance;
    }

    private AppInitializeStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new AppInitializeStoreChangeEvent();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case CHECK_UPDATE:
                handleUpdateCheckResult(action);
                postChange();
                break;
            case LOAD_SAVED_TOKEN:
                loadSavedToken(action);
                postChange();
                break;
        }
    }

    private void handleUpdateCheckResult(Action action) {
        if (action.isError()) {
            errorMessage = "Update check failed. Your device may be offline or" +
                    " our server may be out of service.";
            return;
        }
        boolean requireUpdate = (boolean) action.getPayloadEntry("requireUpdate");
        if (requireUpdate) {
            errorMessage = "This application is outdated. Please update to the newest" +
                    " version!";
            return;
        }
        isUpdateCheckDone = true;
    }

    private void loadSavedToken(Action action) {
        savedToken = (String) action.getPayloadEntry(KEY_SAVED_TOKEN);
    }
}
