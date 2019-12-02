package com.group0536.puzzlemazing.stores.global;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.global.PreferenceActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.Arrays;
import java.util.List;

/**
 * A preference store. It handles the logic
 */
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

    /**
     * Populate the list of possible avatars
     */
    private void initializeAvatarCollection() {
        avatars = Arrays.asList("💀", "💩", "🦸", "🕵", "🎅", "🤦", "💂", "👨", "🧛", "🧟");
    }

    /**
     * Get the list of possible avatars
     *
     * @return the list of possible avatars
     */
    public List<String> getAvatars() {
        return avatars;
    }

    /**
     * Populate the list of possible background music
     */
    private void initializeMusicCollection() {
        musicCollection = Arrays.asList(null, R.raw.jingle_bells, R.raw.alphabet_song);
    }

    /**
     * Get the current background music
     *
     * @return current background music
     */
    public int getBackgroundMusic() {
        return currentMusic;
    }

    /**
     * Get the index of which avatar is checked in the list
     *
     * @return the index of which avatar is checked in the list
     */
    public int getCheckedIndex() {
        return checkedIndex;
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new PreferenceStoreChangeEvent();
    }

    /**
     * Get the instance of this store
     *
     * @param dispatcher the dispatcher associated with this store
     * @return the instance of this store
     */
    public static PreferenceStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new PreferenceStore(dispatcher);
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

    /**
     * Set soundTrack to be the current currentMusic
     *
     * @param soundTrack the index of a background music
     */
    private void setCurrentMusic(int soundTrack) {
        currentMusic = musicCollection.get(soundTrack);
    }
}
