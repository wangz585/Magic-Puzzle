package com.group0536.puzzlemazing.stores.appinit;

import android.content.Context;
import android.util.Log;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.appinit.AppInitializeActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.AppInitProgress;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

public class AppInitializeStore extends Store implements AppInitializeActions {
    private static AppInitializeStore instance;
    private static final String TAG = "AppInitializeStore";

    private AppInitProgress progress;

    // other information during app init process
    private User currentUser;

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

    private AppInitializeStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new AppInitializeStoreChangeEvent();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case START_INITIALIZATION:
                startInit(action);
                break;
            case RESTART_INITIALIZATION:
                finishInit();
                startInit(action);
                break;
            case CHECK_UPDATE:
                handleUpdateCheckResult(action);
                break;
            case LOAD_SAVED_TOKEN:
                loadSavedToken(action);
                break;
            case LOG_IN:
            case REGISTER:
                extractUser(action);
                break;
            case SKIP_LOG_IN:
                skipLogIn();
                break;
            case CLEAR_CURRENT_USER:
                clearUser();
                break;
            case FINISH_INITIALIZATION:
                finishInit();
                break;
            default:
                return;
        }
        postChange();
    }

    private void startInit(Action action) {
        if (progress == null) {
            Context context = (Context) action.getPayloadEntry(KEY_CONTEXT);
            progress = new AppInitProgress(context);
        }
    }

    /**
     * Release resources when initialization has finished.
     */
    private void finishInit() {
        Log.d(TAG, "finishInit: is called!!!!!!");
        progress = null;
        currentUser = null;
    }

    private void clearUser() {
        currentUser = null;
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
        if (!action.isError()) {
            currentUser = (User) action.getPayloadEntry(KEY_CURRENT_USER);
        }
        progress.setLoadSavedTokenDone(true);
    }

    private void extractUser(Action action) {
        if (action.isError()) {
            Object errorMessage = action.getPayloadEntry(KEY_ERROR_MESSAGE);
            if (errorMessage == null) {
                progress.setErrorMessage(R.string.app_init_error_logging_in);
            } else {
                progress.setErrorMessage((String) errorMessage);
            }
            return;
        }
        currentUser = (User) action.getPayloadEntry(KEY_CURRENT_USER);
        progress.setLogInUserDone(true);
    }

    private void skipLogIn() {
        if (currentUser != null) {
            progress.setLogInUserDone(true);
        }
    }
}
