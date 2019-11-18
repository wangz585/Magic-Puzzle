package com.group0536.puzzlemazing.views.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.utils.ActivityUtil;
import com.group0536.puzzlemazing.views.FluxActivity;

/**
 * This activity displays when the application is initializing.
 * Initialization includes loading user preferences and identifying whether
 * the user has logged in before.
 *
 * @author Jimmy
 */
public class AppInitializeActivity extends FluxActivity {

    // Components
    ImageView imgLoading;
    AnimationDrawable animLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_initialize);

        bindViews();
    }

    private void bindViews() {
        imgLoading = findViewById(R.id.imgLoading);
        animLoading = ActivityUtil.setLoadingImage(imgLoading);
    }

    @Override
    protected void initFluxComponents() {

    }
}
