package com.group0536.puzzlemazing.views.games;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.PopupWindow;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.games.GameActionCreator;
import com.group0536.puzzlemazing.stores.games.GameStore;
import com.group0536.puzzlemazing.views.FluxActivity;

public abstract class GameActivity extends FluxActivity {
    GameStore store;
    GameActionCreator actionCreator;

    @Override
    protected void initFluxComponents() {
        store = GameStore.getInstance(dispatcher);
        actionCreator = GameActionCreator.getInstance(dispatcher);
    }



    @Override
    protected void onResume() {
        super.onResume();
        registerStore(store);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterStore(store);
    }

    protected void playIntro(int videoId) {
        popVideowindow(videoId);
    }

    private void popVideowindow(int videoId) {
        VideoPopup videoPopup = (VideoPopup) new VideoPopup.VideoPopupBuilder(this, getApplicationContext())
                .videoResource(videoId)
                .allowSkip(true)
                .animationStyle(R.style.WindowFade)
                .heightPercent(1.0)
                .widthPercent(1.0)
                .focusable(true)
                .build();
        videoPopup.show(Gravity.CENTER, 0, 0);
        handleVideoComplete(videoPopup);
    }

    private void handleVideoComplete(VideoPopup videoPopup) {
        videoPopup.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                actionCreator.initGame();
            }
        });
    }
}
