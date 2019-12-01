package com.group0536.puzzlemazing.views.preference;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.preference.PreferenceActionCreator;
import com.group0536.puzzlemazing.stores.global.PreferenceStore;
import com.group0536.puzzlemazing.utils.MusicPlayer;
import com.group0536.puzzlemazing.views.FluxActivity;

public class PreferenceActivity extends FluxActivity {
    private PreferenceActionCreator actionCreator;
    private PreferenceStore store;
    private static MusicPlayer backgroundMusicPlayer;

    // Components
    private RadioGroup radioGroupSongs;
    private RadioButton radioSoundTrack0;
    private RadioButton radioSoundTrack1;
    private RadioButton radioSoundtrack2;

    @Override
    protected void initFluxComponents() {
        store = PreferenceStore.getInstance(dispatcher);
        setContentView(R.layout.activity_setting_page);
        actionCreator = new PreferenceActionCreator(dispatcher);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeMusicPlayer();
        bindViews();
    }

    private void initializeMusicPlayer() {
        if (backgroundMusicPlayer == null) {
            backgroundMusicPlayer = new MusicPlayer();
        }
    }

    private void bindViews() {
        radioGroupSongs = findViewById(R.id.radioGroupBackgroundMusic);
        radioSoundTrack0 = findViewById(R.id.radioSoundtrack0);
        radioSoundTrack1 = findViewById(R.id.radioSoundtrack1);
        radioSoundtrack2 = findViewById(R.id.radioSoundtrack2);
        String radioButtonId = "radioSoundtrack" + store.getCheckedIndex();
        int res = getResources()
                .getIdentifier(radioButtonId, "id", getPackageName());
        RadioButton checkedRadioButton = findViewById(res);
        checkedRadioButton.setChecked(true);
        radioGroupSongs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioSoundtrack0) {
                    backgroundMusicPlayer.stopMusic();
                    actionCreator.clearSound();
                } else {
                    if (checkedId == R.id.radioSoundtrack1) {
                        actionCreator.setSound(1);
                    } else if (checkedId == R.id.radioSoundtrack2) {
                        actionCreator.setSound(2);
                    }
                    backgroundMusicPlayer.playMusic(PreferenceActivity.this, store.getBackgroundMusic());
                }
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
}
