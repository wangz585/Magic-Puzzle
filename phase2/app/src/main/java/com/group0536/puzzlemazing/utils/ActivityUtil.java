package com.group0536.puzzlemazing.utils;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.group0536.puzzlemazing.R;

/**
 * This class collects common utility functions that share across
 * different activities.
 *
 * @author Jimmy
 */
public final class ActivityUtil {
    private ActivityUtil() {
        throw new UnsupportedOperationException(
                "Instantiating a util class is not allowed.");
    }

    /**
     * Set image view to display loading animation.
     *
     * @param imageView target image view
     * @return Animation drawable, related to the loading animation,
     * that is contained in the image view
     */
    public static AnimationDrawable setLoadingImage(ImageView imageView) {
        imageView.setBackgroundResource(R.drawable.loading_animation);
        AnimationDrawable animLoading = (AnimationDrawable) imageView.getBackground();
        animLoading.start();
        return animLoading;
    }
}
