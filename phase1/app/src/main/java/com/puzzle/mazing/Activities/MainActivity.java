package com.puzzle.mazing.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.puzzle.mazing.R;
import com.puzzle.mazing.Network.Http;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView httpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpTextView = findViewById(R.id.text_view_result);

        JSONObject obj = new JSONObject();
        try {
            obj.put("data", "testing-data-here");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Http.post("https://apis.puzzlemazing.online/test/post", obj, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        httpTextView.setText(res);
                    }
                });
            }
        });

    }
}
