package com.group0536.puzzlemazing.views.busyworker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.FluxActivity;

public class SelectLevelActivity extends FluxActivity {

    private BusyWorkerActionCreator actionCreator;
    private BusyWorkerStore store;
    Button btnLevel1;
    Button btnLevel2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy_match_select_level);
        btnLevel1 = findViewById(getResources()
                .getIdentifier("btn_level_1", "id", getPackageName()));
        btnLevel2 = findViewById(getResources()
                .getIdentifier("btn_level_2", "id", getPackageName()));
        btnLevel1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                actionCreator.initMap(1);
                Intent intent = new Intent(SelectLevelActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        btnLevel2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                actionCreator.initMap(2);
                Intent intent = new Intent(SelectLevelActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initFluxComponents() {
        store = BusyWorkerStore.getInstance(dispatcher);
        actionCreator = new BusyWorkerActionCreator(dispatcher);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerStore(store);
    }

    @Override
    protected void onPause() {
        super.onPause();
        registerStore(store);
    }
}
