package com.group0536.puzzlemazing.views.welcome;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.utils.ActivityUtil;

/**
 * This activity displays Puzzle Mazing logo to player as they
 * enter the game.
 *
 * @author Jimmy
 * @version 1.0
 * @since 1.0
 */
public class WelcomeActivity extends AppCompatActivity {

    // Components
    VideoView vvBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_welcome);

        bindViews();
    }

    private void bindViews() {
        vvBackground = findViewById(R.id.vvBackground);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpWelcomeVideo();
        vvBackground.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseWelcomeVideo();
    }

    private void setUpWelcomeVideo() {
        String videoPath = "android.resource://" + getPackageName() + "/" +
                R.raw.welcome;
        Uri videoUri = Uri.parse(videoPath);
        vvBackground.setVideoURI(videoUri);
    }

    private void releaseWelcomeVideo() {
        vvBackground.stopPlayback();
    }
}
