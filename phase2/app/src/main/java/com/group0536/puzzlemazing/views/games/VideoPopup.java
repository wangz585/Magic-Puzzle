package com.group0536.puzzlemazing.views.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.views.Popup;

/**
 * This class represents a popup window that shows a video and dismisses
 * when the video stops. The popup provides the option to allow user skip the video
 * to dismiss the popup earlier.
 *
 * @author Jimmy Lan
 * @see VideoPopupBuilder
 * @see VideoView
 * @since 1.5
 */
public class VideoPopup extends Popup {

    // Components
    private VideoView vvBackground;
    private Button btnSkip;

    // Data
    private boolean allowSkip = true;

    // Listeners
    private View.OnClickListener skipOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            popupWindow.dismiss();
        }
    };

    private VideoPopup(VideoPopupBuilder builder) {
        super(builder);
        allowSkip = builder.allowSkip;
        bindViews();
        addListeners();
        setUpTransitionVideo(builder.rawResourceId);
    }

    private void addListeners() {
        btnSkip.setOnClickListener(skipOnClickListener);
    }

    private void bindViews() {
        vvBackground = popupWindowView.findViewById(R.id.vvBackground);
        btnSkip = popupWindowView.findViewById(R.id.btnSkip);
    }

    private void setUpTransitionVideo(int rawResourceId) {
        String videoPath = "android.resource://" + context.getPackageName() + "/" +
                rawResourceId;
        Uri videoUri = Uri.parse(videoPath);
        vvBackground.setVideoURI(videoUri);
        vvBackground.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseTransitionVideo();
                popupWindow.dismiss();
            }
        });
    }

    private void releaseTransitionVideo() {
        vvBackground.stopPlayback();
    }

    public static class VideoPopupBuilder extends Popup.PopupBuilder {
        private int rawResourceId;
        private boolean allowSkip;

        /**
         * Initialize a popup builder by providing the mandatory fields.
         *
         * @param parent   parent android view of this popup window
         * @param context  the context to apply this popup.
         */
        public VideoPopupBuilder(Activity parent, Context context) {
            super(parent, R.layout.popup_video, context);
        }

        /**
         * Set video resource to be played by the popup.
         *
         * @param rawResourceId id of the video resource.
         */
        public VideoPopupBuilder videoResource(int rawResourceId) {
            this.rawResourceId = rawResourceId;
            return this;
        }

        /**
         * Set whether this video is allowed to be skipped.
         * User can skip a video by default.
         * @param allowSkip true if user can skip this video, false if otherwise.
         */
        public VideoPopupBuilder allowSkip(boolean allowSkip) {
            this.allowSkip = allowSkip;
            return this;
        }
    }
}
