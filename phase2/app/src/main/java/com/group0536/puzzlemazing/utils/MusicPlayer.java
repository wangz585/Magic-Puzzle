package com.group0536.puzzlemazing.utils;

import android.app.Activity;
import android.media.MediaPlayer;

public class MusicPlayer {

    private MediaPlayer musicPlayer;
    private int currentMusic;

    public MusicPlayer() {
        musicPlayer = new MediaPlayer();
        musicPlayer.setLooping(true);
    }


    public void playMusic(Activity activity, int music) {
        if (music != currentMusic) {
            if (musicPlayer.isPlaying()) {
                musicPlayer.stop();
            }
            musicPlayer = MediaPlayer.create(activity, music);
            musicPlayer.start();
            currentMusic = music;
        }
    }

    public void stopMusic() {
        if (musicPlayer.isPlaying()) {
            musicPlayer.stop();
            currentMusic = 0;
        }

    }

}
