package com.puzzle.mazing.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.puzzle.mazing.R;


public class BusyWorkerMainActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnExitGame = findViewById(R.id.btn_exit);
        btnExitGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button btnStartGame = findViewById(R.id.btn_start_game);
        btnStartGame.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View view) {
                Intent intent = new Intent(BusyWorkerMainActivity.this, BusyWorkerGameLevelActivity.class);
                startActivity(intent);
            }
        });
    }
}
