package com.group0536.puzzlemazing.webapi;

import com.group0536.puzzlemazing.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;

/**
 * This class sends API requests to our game server.
 *
 * @author Jimmy Lan
 */
public class ServerApi {
    static final String ROOT_DOMAIN = "https://apis.puzzlemazing.online";

    ServerApi() {
    }

    String getURL(String uri) {
        return ROOT_DOMAIN + uri;
    }
}
