package com.group0536.puzzlemazing.stores.global;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.preference.PreferenceActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.Arrays;
import java.util.List;

public class PreferenceStore extends Store implements PreferenceActions {
    private String savedToken;
    private static PreferenceStore instance;
    private List<Integer> musicCollection;
    private int currentMusic;
    private int checkedIndex;
    private List<String> avatars;
    private String selectedAvatar;

    protected PreferenceStore(Dispatcher dispatcher) {
        super(dispatcher);
        initializeMusicCollection();
        initializeAvatarCollection();
    }

    private void initializeAvatarCollection() {
        avatars = Arrays.asList("💀", "💩", "🦸", "🕵", "🎅", "🤦", "💂", "👨", "🧛", "🧟");
    }

    public List<String> getAvatars() {
        return avatars;
    }

    private void initializeMusicCollection() {
        musicCollection = Arrays.asList(null, R.raw.jingle_bells, R.raw.alphabet_song);
    }

    public int getBackgroundMusic() {
        return currentMusic;
    }

    public int getCheckedIndex() {
        return checkedIndex;
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new PreferenceStoreChangeEvent();
    }

    public static com.group0536.puzzlemazing.stores.global.PreferenceStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new com.group0536.puzzlemazing.stores.global.PreferenceStore(dispatcher);
        }
        return instance;
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case SET_SOUND:
                checkedIndex = (int) action.getPayloadEntry("soundTrack");
                setCurrentMusic(checkedIndex);
                break;
            case CLEAR_SOUND:
                currentMusic = 0;
                break;
            case SET_AVATAR:
                selectedAvatar = (String) action.getPayloadEntry("avatarSelected");
                break;
        }
    }

    private void setCurrentMusic(int soundTrack) {
        currentMusic = musicCollection.get(soundTrack);
    }
}
