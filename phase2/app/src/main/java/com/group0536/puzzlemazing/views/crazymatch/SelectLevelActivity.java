package com.group0536.puzzlemazing.views.crazymatch;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.crazymatch.CrazyMatchActionCreator;
import com.group0536.puzzlemazing.services.BackgroundMusicService;
import com.group0536.puzzlemazing.stores.crazymatch.CrazyMatchStore;
import com.group0536.puzzlemazing.views.FluxActivity;

public class SelectLevelActivity extends FluxActivity {

    private CrazyMatchActionCreator actionCreator;
    private CrazyMatchStore store;
    Button btnLevel1;
    Button btnLevel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy_match_select_level);

        final TextView text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMyServiceRunning(BackgroundMusicService.class)) {
                    text.setText("Stoped");
                    stopService(new Intent(SelectLevelActivity.this, BackgroundMusicService.class));
                } else {
                    text.setText("Started");
                    startService(new Intent(SelectLevelActivity.this, BackgroundMusicService.class));
                }
            }
        });


        btnLevel1 = findViewById(getResources()
                .getIdentifier("btn_level_1", "id", getPackageName()));
        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = 1;
                actionCreator.initializeBoard(level);
                Intent intent = new Intent(SelectLevelActivity.this, GameActivity.class);
                intent.putExtra("level", level);
                startActivity(intent);
            }
        });
        btnLevel2 = findViewById(getResources()
                .getIdentifier("btn_level_2", "id", getPackageName()));
        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int level = 2;
                actionCreator.initializeBoard(level);
                Intent intent = new Intent(SelectLevelActivity.this, GameActivity.class);
                intent.putExtra("level", level);
                startActivity(intent);
            }
        });
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

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void initFluxComponents() {
        store = CrazyMatchStore.getInstance(dispatcher);
        actionCreator = new CrazyMatchActionCreator(dispatcher);
    }
}
