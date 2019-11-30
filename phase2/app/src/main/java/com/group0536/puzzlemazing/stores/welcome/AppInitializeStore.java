package com.group0536.puzzlemazing.stores.welcome;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.appinit.AppInitializeActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.AppInitProgress;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

public class AppInitializeStore extends Store implements AppInitializeActions {
    private static AppInitializeStore instance;
    private final String TAG = "AppInitializeStore";

    private AppInitProgress progress;

    // other information during app init process
    private String savedToken;

    public static AppInitializeStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new AppInitializeStore(dispatcher);
        }
        return instance;
    }

    // Getters
    public AppInitProgress getProgress() {
        return progress;
    }

    public String getSavedToken() {
        return savedToken;
    }

    private AppInitializeStore(Dispatcher dispatcher) {
        super(dispatcher);
        progress = new AppInitProgress();
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new AppInitializeStoreChangeEvent();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case START_INITIALIZATION:
                startInit();
                break;
            case RESTART_INITIALIZATION:
                finishInit();
                startInit();
                break;
            case CHECK_UPDATE:
                handleUpdateCheckResult(action);
                break;
            case LOAD_SAVED_TOKEN:
                loadSavedToken(action);
                break;
            case FINISH_INITIALIZATION:
                finishInit();
                break;
        }
        postChange();
    }

    private void startInit() {
        if (progress == null) {
            progress = new AppInitProgress();
        }
    }

    /**
     * Release resources when initialization has finished.
     */
    private void finishInit() {
        progress = null;
        savedToken = null;
    }

    private void handleUpdateCheckResult(Action action) {
        if (action.isError()) {
            progress.setErrorMessage(R.string.app_init_error_checking_update);
            return;
        }
        boolean requireUpdate = (boolean) action.getPayloadEntry(KEY_REQUIRE_UPDATE);
        if (requireUpdate) {
            progress.setErrorMessage(R.string.app_init_error_oudated);
            return;
        }
        progress.setUpdateCheckDone(true);
    }

    private void loadSavedToken(Action action) {
        savedToken = (String) action.getPayloadEntry(KEY_SAVED_TOKEN);
        progress.setLoadSavedTokenDone(true);
    }
}
