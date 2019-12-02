package com.group0536.puzzlemazing.views.global;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.global.PreferenceActionCreator;
import com.group0536.puzzlemazing.stores.global.PreferenceStore;
import com.group0536.puzzlemazing.utils.MusicPlayer;
import com.group0536.puzzlemazing.views.FluxActivity;

/**
 * This is an activity responsible for settings
 */
public class PreferenceActivity extends FluxActivity {
    private PreferenceActionCreator actionCreator;
    private PreferenceStore store;
    private static MusicPlayer backgroundMusicPlayer;

    // Components
    private RadioGroup radioGroupSongs;
    private RadioButton radioSoundTrack0;
    private RadioButton radioSoundTrack1;
    private RadioButton radioSoundtrack2;
    private Spinner spinnerAvatars;

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

    /**
     * Initialize the music player responsible for playing the background music
     */
    private void initializeMusicPlayer() {
        if (backgroundMusicPlayer == null) {
            backgroundMusicPlayer = new MusicPlayer();
        }
    }

    /**
     * Initialize all the components on this activity
     */
    private void bindViews() {
        initializeAvatarSpinner();
        initializeMusicRadioButton();

    }

    /**
     * Initialize the radio buttons for choosing background music
     */
    private void initializeMusicRadioButton() {
        radioGroupSongs = findViewById(R.id.radioGroupBackgroundMusic);
        radioSoundTrack0 = findViewById(R.id.radioSoundtrack0);
        radioSoundTrack1 = findViewById(R.id.radioSoundtrack1);
        radioSoundtrack2 = findViewById(R.id.radioSoundtrack2);
        String radioButtonId = "radioSoundtrack" + store.getCheckedIndex();
        int res = getResources()
                .getIdentifier(radioButtonId, "id", getPackageName());
        RadioButton checkedRadioButton = findViewById(res);
        checkedRadioButton.setChecked(true);
        setMusicClickEvent();
    }

    /**
     * Set the click event for radio button group. When a music is selected, it is played by
     * the media player
     */
    private void setMusicClickEvent() {
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

    /**
     * Initialize the spinner for choosing avatar
     */
    private void initializeAvatarSpinner() {
        spinnerAvatars = findViewById(R.id.spinAvatar);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, store.getAvatars());
        spinnerAvatars.setAdapter(spinnerArrayAdapter);
        spinnerAvatars.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String avatarSelected = parent.getItemAtPosition(position).toString();
                actionCreator.setAvatar(avatarSelected);
            }

            // When no avatar is selected, the default is the first avatar
            public void onNothingSelected(AdapterView<?> parent) {
                String avatarSelected = parent.getItemAtPosition(0).toString();
                actionCreator.setAvatar(avatarSelected);
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
