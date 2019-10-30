package com.puzzle.mazing.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.puzzle.mazing.R;
import com.puzzle.mazing.Network.Http;

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
        Http.get("https://apis.puzzlemazing.online/test/get", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final int myResponseCode = response.code();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String display = "Status Code is:" + myResponseCode;
                        httpTextView.setText(display);
                    }
                });
            }
        });

    }
}
