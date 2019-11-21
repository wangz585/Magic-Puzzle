package com.group0536.puzzlemazing.actions.welcome;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.services.ServerApi;
import com.group0536.puzzlemazing.utils.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AppInitializeActionCreator extends ActionCreator implements AppInitializeActions {
    private ServerApi serverApi;

    public AppInitializeActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
        serverApi = ServerApi.getServerApi();
    }

    /**
     * Construct and dispatch an action that is resulted from failure of
     * a network request.
     *
     * @param type action type
     */
    private void dispatchNetworkErrorAction(String type) {
        Action action = new Action.ActionBuilder(type)
                .error(true)
                .build();
        dispatcher.dispatch(action);
    }

    /**
     * Construct and dispatch an action that is resulted from an error.
     * The action will contain a payload entry with key KEY_ERROR_MESSAGE
     * accessible from {@link AppInitializeActions}.
     *
     * @param type action type
     * @param message error message
     */
    private void dispatchErrorAction(String type, String message) {
        Action action = new Action.ActionBuilder(type)
                .error(true)
                .load(KEY_ERROR_MESSAGE, message)
                .build();
        dispatcher.dispatch(action);
    }

    public void startInitialization() {
        Action action = new Action.ActionBuilder(START_INITIALIZATION)
                .build();
        dispatcher.dispatch(action);
    }

    public void finishInitialization() {
        Action action = new Action.ActionBuilder(FINISH_INITIALIZATION)
                .build();
        dispatcher.dispatch(action);
    }

    public void restartInitialization() {
        Action action = new Action.ActionBuilder(RESTART_INITIALIZATION)
                .build();
        dispatcher.dispatch(action);
    }

    public void checkUpdate() {
        serverApi.performUpdateCheck(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dispatchNetworkErrorAction(CHECK_UPDATE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                boolean requireUpdate;
                try {
                    JSONObject body = Parser.parseResponseBody(response);
                    requireUpdate = body.getBoolean("result");
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
                Action action = new Action.ActionBuilder(CHECK_UPDATE)
                        .load(KEY_REQUIRE_UPDATE, requireUpdate)
                        .build();
                dispatcher.dispatch(action);
            }
        });
    }

    public void loadSavedToken(Context appContext) {
        SharedPreferences preferences = appContext.getSharedPreferences(PREFIX, 0);
        String savedToken = preferences.getString(KEY_SAVED_TOKEN, "");

        Action action = new Action.ActionBuilder(LOAD_SAVED_TOKEN)
                .load(KEY_SAVED_TOKEN, savedToken)
                .build();
        dispatcher.dispatch(action);
    }

    public void verifyToken(String token) {
        serverApi.performTokenValidation(token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dispatchNetworkErrorAction(VERIFY_TOKEN);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                User user = new User();
                boolean requestSucceed = response.isSuccessful();
                if (requestSucceed) {
                    user = Parser.parseResponseToUser(response);
                }

                Action action = new Action.ActionBuilder(VERIFY_TOKEN)
                        .load(KEY_TOKEN_VALIDITY, requestSucceed)
                        .load(KEY_CURRENT_USER, user)
                        .build();
                dispatcher.dispatch(action);
            }
        });
    }
}
