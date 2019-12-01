package com.group0536.puzzlemazing.views.appinit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.VideoView;

import com.group0536.puzzlemazing.R;

public class MenuActivity extends AppCompatActivity {

    // Components
    VideoView vvBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_menu);

        bindViews();
    }

    private void bindViews() {
        vvBackground = findViewById(R.id.vvBackground);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpTransitionVideo();
        vvBackground.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseTransitionVideo();
    }

    private void setUpTransitionVideo() {
        String videoPath = "android.resource://" + getPackageName() + "/" +
                R.raw.intro;
        Uri videoUri = Uri.parse(videoPath);
        vvBackground.setVideoURI(videoUri);
        vvBackground.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent intent = new Intent(MenuActivity.this, AppInitializeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void releaseTransitionVideo() {
        vvBackground.stopPlayback();
    }
}
