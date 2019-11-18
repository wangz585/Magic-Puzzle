package com.group0536.puzzlemazing.services;

import com.group0536.puzzlemazing.utils.HttpUtil;

import org.json.JSONObject;

import okhttp3.Callback;

/**
 * This class sends API requests to our game server.
 *
 * @author Jimmy Lan
 */
public class ServerApi {
    private static ServerApi serverApi;
    private static final String ROOT_DOMAIN = "https://apis.puzzlemazing.online";

    public static ServerApi getServerApi() {
        if (serverApi == null) {
            serverApi = new ServerApi();
        }
        return serverApi;
    }

    private ServerApi() {
    }

    public String getDomain(String uri) {
        return ROOT_DOMAIN + uri;
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
        String url = getDomain("/application/check-update");
        HttpUtil.get(url, done);
    }

    public void performTokenValidation(String token, Callback done) {
        String url = getDomain("/sign-in-token");
        HttpUtil.post(url, new JSONObject(), token, done);  // post request with empty body
    }
}
