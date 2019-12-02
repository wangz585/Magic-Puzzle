package com.group0536.puzzlemazing.views.games;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0536.puzzlemazing.R;

import java.util.Arrays;
import java.util.List;

public class AnimationActivity extends AppCompatActivity {

    private List<Integer> videos;
    private List<Class> classes;
    // The index of which video to be played and which activity to be jumped to after the video
    private int currentVideoIndex;

    // Components
    private VideoView vvBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_animation);
        bindViews();
    }

    /**
     * Populate the list containing the videos
     */
    private void initializeVideos() {
        videos = Arrays.asList(R.raw.challenge1, R.raw.challenge2, R.raw.challenge3,
                R.raw.winning);
    }

    /**
     * Populate the list containing the classes. When a video is over, the class at index
     * currentVideoIndex is jumped to
     */
    private void initializeActivities() {
        classes = Arrays.asList((Class)
                com.group0536.puzzlemazing.views.games.busyworker.SelectLevelActivity.class,
                com.group0536.puzzlemazing.views.games.wordguessing.SelectLevelActivity.class,
                com.group0536.puzzlemazing.views.games.crazymatch.SelectLevelActivity.class,
                com.group0536.puzzlemazing.views.scoreboard.ScoreBoardActivity.class);
    }

    /**
     * Initialize all the components on this activity
     */
    private void bindViews() {
        vvBackground = findViewById(R.id.vvBackground);
        Intent mIntent = getIntent();
        currentVideoIndex = mIntent.getIntExtra("challenge", 0);
        initializeVideos();
        initializeActivities();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpVideo();
        vvBackground.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseVideo();
    }

    /**
     * Set up the video to be played
     */
    private void setUpVideo() {
        String videoPath = "android.resource://" + getPackageName() + "/" +
                videos.get(currentVideoIndex);
        Uri videoUri = Uri.parse(videoPath);
        vvBackground.setVideoURI(videoUri);
        // When the video is over, jump to another activity
        vvBackground.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent intent = new Intent(AnimationActivity.this, classes.get(currentVideoIndex));
                startActivity(intent);
            }
        });
    }

    /**
     * Release the video
     */
    private void releaseVideo() {
        vvBackground.stopPlayback();
    }
}