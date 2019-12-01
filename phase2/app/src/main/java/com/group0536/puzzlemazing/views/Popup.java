package com.group0536.puzzlemazing.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * <p>This class represents and manages a popup window inflated from an activity
 * or android view. It contains a builder to help building a popup and helper methods
 * on the display of a popup.</p>
 * <p>We recommend you treat this class as an <i>abstract</i> definition of a popup. It is
 * generally encouraged for users or implementors of this class to define their own
 * specific popup version extending this class.</p>
 *
 * @author Jimmy Lan
 * @see PopupWindow
 * @see PopupBuilder
 * @version 1.0
 * @since 1.0
 */
public class Popup {
    protected Activity parent;
    protected View parentContentView;
    protected PopupWindow popupWindow;
    protected View popupWindowView;

    protected Context context;

    protected Popup(PopupBuilder builder) {
        this.parent = builder.parent;
        this.popupWindow = builder.popupWindow;
        this.parentContentView = builder.parentContentView;
        this.popupWindowView = builder.popupWindowView;
        this.context = builder.context;
    }

    /**
     * <p>Show the popup window on top of its parent view.</p>
     * <p>See {@link android.view.WindowManager.LayoutParams} for specific information
     * about how gravity and the parameters x, y are related.</p>
     *
     * @param gravity gravity controlling the placement of this window
     * @param x the popup's x-location offset
     * @param y the popup's y-location offset
     */
    public void show(final int gravity, final int x, final int y) {
        parent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(parentContentView, gravity, x, y);
            }
        });
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public View getPopupWindowView() {
        return popupWindowView;
    }

    protected String getString(int resourceId) {
        return context.getString(resourceId);
    }

    /**
     * <p>This is the builder class for a {@link #Popup}, it includes building methods to
     * create, manipulate, and inflate a functioning popup.</p>
     * <p>We recommend implementors of the <tt>Popup</tt> class to always restrict
     * direct initialization of the class, and hence enforce the usage of a builder.</p>
     *
     * @author Jimmy Lan
     * @see Popup
     * @see LayoutInflater
     */
    public static class PopupBuilder {
        private final Activity parent;
        private View parentContentView;
        private PopupWindow popupWindow;
        private View popupWindowView;
        private LayoutInflater inflater;
        private final Context context;

        /**
         * Initialize a popup builder by providing the mandatory fields.
         * @param parent parent android view of this popup window
         * @param layoutId the layout resource ID associating with this popup.
         *                 Use {@code R.layout} to access the xml files corresponding
         *                 to layouts.
         * @param context the context to apply this popup.
         */
        public PopupBuilder(Activity parent, int layoutId, Context context) {
            this.parent = parent;
            this.context = context;
            parentContentView = parent.findViewById(android.R.id.content);
            inflater = (LayoutInflater)
                    parent.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            constructDefaultWindow(layoutId);
        }

        private void constructDefaultWindow(int layoutId) {
            popupWindowView = inflater.inflate(layoutId, null);
            int defaultWidth = popupWindowView.getWidth();
            int defaultHeight = popupWindowView.getHeight();
            popupWindow = new PopupWindow(popupWindowView, defaultWidth, defaultHeight, true);
        }

        /**
         * Adjust the width of the popup to a percentage of the original layout width.
         * @param widthPercent a double value between 0.0d and 1.0d.
         * @return {@link #PopupBuilder}
         */
        public PopupBuilder widthPercent(double widthPercent) {
            int width = (int) (parentContentView.getWidth() * widthPercent);
            popupWindow.setWidth(width);
            return this;
        }

        /**
         * Adjust the height of the popup to a percentage of the original layout height.
         * @param heightPercent a double value between 0.0d and 1.0d.
         * @return {@link #PopupBuilder}
         */
        public PopupBuilder heightPercent(double heightPercent) {
            int height = (int) (parentContentView.getHeight() * heightPercent);
            popupWindow.setHeight(height);
            return this;
        }

        /**
         * <p>Modify the visibility of the popup window. Observe that when
         * the popup window is not focusable, the popup window cannot be closed
         * by clicking on its parent view.</p>
         * <p>By default, focusable is true unless set to false using this method.</p>
         *
         * @param focusable true if focus is granted to the popup widow, false otherwise.
         * @return {@link #PopupBuilder}
         */
        public PopupBuilder focusable(boolean focusable) {
            popupWindow.setFocusable(focusable);
            return this;
        }

        /**
         * Sets the animation used for this popup window.
         *
         * @param animResourceId the animation resource id corresponding to
         *                       the animation style.
         * @return {@link #PopupBuilder}
         */
        public PopupBuilder animationStyle(int animResourceId) {
            popupWindow.setAnimationStyle(animResourceId);
            return this;
        }

        public Popup build() {
            return new Popup(this);
        }
    }
}
