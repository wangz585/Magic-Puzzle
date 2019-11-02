package com.puzzle.mazing.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.puzzle.mazing.Models.BusyWorkerGameLevels;
import com.puzzle.mazing.R;

public class BusyWorkerGameLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busyworker_activity_game_level);
        GridView gv_levels = findViewById(R.id.gv_levels);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.gv_levels_item_textview, BusyWorkerGameLevels.getLevelList());
        gv_levels.setAdapter(arrayAdapter);
        gv_levels.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(
                        BusyWorkerGameLevelActivity.this, BusyWorkerGameActivity.class);
                intent.putExtra(BusyWorkerGameActivity.KEY_SELECTED_LEVEL, i + 1);
                startActivity(intent);
            }

        });
    }
}
