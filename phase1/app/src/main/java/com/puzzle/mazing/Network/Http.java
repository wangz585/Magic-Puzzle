package com.puzzle.mazing.Network;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.RequestBody;

public class Http {
    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Perform GET request
     * @param url destination URL
     * @param done Callback object when request completes
     */
    public static void get(String url, Callback done) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(done);
    }


    /**
     * Perform POST request with body
     * @param url destination URL
     * @param body body of POST request
     * @param done Callback object when request completes
     */
    public static void post(String url, JSONObject body, Callback done) {
        String jsonString = body.toString();
        RequestBody requestBody = RequestBody.create(JSON, jsonString);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(done);
    }
}
