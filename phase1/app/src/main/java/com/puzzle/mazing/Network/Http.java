package com.puzzle.mazing.Network;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Http {
  private static OkHttpClient client = new OkHttpClient();

  public static void get(String url, Callback done) {
    Request request = new Request.Builder().url(url).build();

    client.newCall(request).enqueue(done);
  }
}
