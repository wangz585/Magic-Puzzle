package com.group0536.puzzlemazing.webapi;

import com.group0536.puzzlemazing.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;

public class AuthApi extends ServerApi {
    private static AuthApi instance;

    public static AuthApi getAuthApi() {
        if (instance == null) {
            instance = new AuthApi();
        }
        return instance;
    }

    public void performTokenValidation(String token, Callback done) {
        String url = getURL("/sign-in-token");
        HttpUtil.post(url, new JSONObject(), token, done);  // post request with empty body
    }

    public void performLogIn(String username, String password, Callback done) {
        String url = getURL("/sign-in");
        JSONObject body = getJSONWithCredential(username, password);
        HttpUtil.post(url, body, done);
    }

    public void performRegister(String username, String password, Callback done) {
        String url = getURL("/sign-up");
        JSONObject body = getJSONWithCredential(username, password);
        HttpUtil.post(url, body, done);
    }

    private JSONObject getJSONWithCredential(String username, String password) {
        JSONObject body = new JSONObject();
        try {
            body.put("username", username);
            body.put("password", password);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * Performs an update check to see if newer version of the application
     * is available.
     * TODO There are better ways to achieve update checks (i.e. using google play).
     *  However, this is out of the scope of CSC207 and we will do it in the future
     *  if possible. Here we simulate this process.
     * @param done Callback object used after the request has completed
     */
    public void performUpdateCheck(Callback done) {
        String url = getURL("/application/check-update");
        HttpUtil.get(url, done);
    }
}
