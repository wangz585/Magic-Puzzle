package com.puzzle.mazing.Network;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Http {
  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  private static OkHttpClient client = new OkHttpClient();

  /**
   * Perform GET request
   * @param url destination URL
   * @param done Callback Object used after request completes
   */
  public static void get(String url, Callback done) {
    Request request = buildGetRequest(url);

    client.newCall(request).enqueue(done);
  }


  /**
   * Perform GET request synchronously
   * @param url destination URL
   * @return Response object
   * @throws IOException when request cannot deliver
   */
  public static Response syncGet(String url) throws IOException {
    Request request = buildGetRequest(url);

    return client.newCall(request).execute();
  }


  private static Request buildGetRequest(String url) {
    return new Request.Builder().url(url).build();
  }

  /**
   * Perform POST request
   * @param url destination URL
   * @param body body of request
   * @param done Callback Object used after request completes
   */
  public static void post(String url, JSONObject body, Callback done) {
    String bodyString = body.toString();
    Request request = buildPostRequest(url, bodyString);

    client.newCall(request).enqueue(done);
  }


  /**
   * Perform POST request with an authentication token
   * @param url destination URL
   * @param body body of request
   * @param token authentication token to access protected resource
   * @param done Callback Object used after request completes
   */
  public static void post(String url, JSONObject body, String token, Callback done) {
    String bodyString = body.toString();
    Request request = buildPostRequest(url, bodyString, token);

    client.newCall(request).enqueue(done);
  }


  /**
   * Perform POST request synchronously
   * @param url destination URL
   * @param body body of request
   * @return Response object
   * @throws IOException when request fail to deliver
   */
  public static Response syncPost(String url, JSONObject body) throws IOException {
    String bodyString = body.toString();
    Request request = buildPostRequest(url, bodyString);

    return client.newCall(request).execute();
  }


  /**
   * Perform POST request synchronously
   * @param url destination URL
   * @param body body of request
   * @param token authentication token to access protected resource
   * @return Response object
   * @throws IOException when request fail to deliver
   */
  public static Response syncPost(String url, JSONObject body, String token) throws IOException {
      String bodyString = body.toString();
      Request request = buildPostRequest(url, bodyString, token);

      return client.newCall(request).execute();
  }


  private static Request buildPostRequest(String url, String bodyString) {
    RequestBody requestBody = RequestBody.create(JSON, bodyString);
    return new Request.Builder()
            .url(url)
            .post(requestBody)
            .build();
  }


  private static Request buildPostRequest(String url, String bodyString, String token) {
      RequestBody requestBody = RequestBody.create(JSON, bodyString);
      return new Request.Builder()
              .url(url)
              .post(requestBody)
              .addHeader("authentication", token)
              .build();
  }
}
