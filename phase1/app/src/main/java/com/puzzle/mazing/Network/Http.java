package com.puzzle.mazing.Network;

import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Http {
  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  private static OkHttpClient client = new OkHttpClient();

  /**
   * Perform GET request
   * @param url destination URL
   * @param done Callback Object used after request completes
   */
  public static void get(String url, Callback done) {
    Request request = new Request.Builder()
            .url(url)
            .build();

    client.newCall(request).enqueue(done);
  }

  /**
   * Perform POST request
   * @param url destination URL
   * @param body body of request
   * @param done Callback Object used after request completes
   */
  public static void post(String url, JSONObject body, Callback done) {
    String bodyString = body.toString();
    RequestBody requestBody = RequestBody.create(JSON, bodyString);

    Request request = new Request.Builder()
            .url(url)
            .post(requestBody)
            .build();

    client.newCall(request).enqueue(done);
  }
}
