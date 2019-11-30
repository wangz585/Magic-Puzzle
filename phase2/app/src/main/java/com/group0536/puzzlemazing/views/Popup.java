package com.group0536.puzzlemazing.views;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.group0536.puzzlemazing.R;

public class Popup {
    private Activity parent;
    private PopupWindow popupWindow;
    private View parentContentView;

    private Popup(PopupBuilder builder) {
        this.parent = builder.parent;
        this.popupWindow = builder.popupWindow;
        this.parentContentView = builder.parentContentView;
    }

    public void show(final int gravity, final int x, final int y) {
        parent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(parentContentView, gravity, x, y);
            }
        });

    }

    public static class PopupBuilder {
        private Activity parent;
        private PopupWindow popupWindow;
        private View parentContentView;
        private LayoutInflater inflater;

        public PopupBuilder(Activity parent, int layoutId) {
            this.parent = parent;
            parentContentView = parent.findViewById(android.R.id.content);
            inflater = (LayoutInflater)
                    parent.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            constructDefaultWindow(layoutId);
        }

        private void constructDefaultWindow(int layoutId) {
            View windowView = inflater.inflate(layoutId, null);
            int defaultWidth = windowView.getWidth();
            int defaultHeight = windowView.getHeight();
            popupWindow = new PopupWindow(windowView, defaultWidth, defaultHeight, true);
        }

        public PopupBuilder widthPercent(double widthPercent) {
            int width = (int) (parentContentView.getWidth() * widthPercent);
            popupWindow.setWidth(width);
            return this;
        }

        public PopupBuilder heightPercent(double heightPercent) {
            int height = (int) (parentContentView.getHeight() * heightPercent);
            popupWindow.setHeight(height);
            return this;
        }

        public PopupBuilder focusable(boolean focusable) {
            popupWindow.setFocusable(focusable);
            return this;
        }

        public PopupBuilder animationStyle(int animResourceId) {
            popupWindow.setAnimationStyle(animResourceId);
            return this;
        }

        public Popup build() {
            return new Popup(this);
        }
    }
}
