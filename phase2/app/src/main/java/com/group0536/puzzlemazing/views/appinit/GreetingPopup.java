package com.group0536.puzzlemazing.views.appinit;

import android.app.Activity;
import android.content.res.Resources;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.views.Popup;

public class GreetingPopup extends Popup {
    private static final String TAG = "greetingPopup";
    // Components
    private Button btnContinue;
    private Button btnSwitchAccount;
    private TextView tvGreeting;

    // Data
    private User currentUser;

    private GreetingPopup(GreetingPopupBuilder builder) {
        super(builder);
        currentUser = builder.currentUser;
        bindViews();
        initViews();
        addListeners();
    }

    private void initViews() {
        String greetingPrefix = Resources.getSystem().getString(R.string.app_welcome_continue);
        String greetingMessage = greetingPrefix + currentUser.getNickname();
        tvGreeting.setText(greetingMessage);

        String continuePrefix = Resources.getSystem().getString(R.string.app_welcome_continue);
        String continueMessage = continuePrefix + currentUser.getNickname();
        btnContinue.setText(continueMessage);
    }

    private void bindViews() {
        btnContinue = popupWindowView.findViewById(R.id.btnContinue);
        btnSwitchAccount = popupWindowView.findViewById(R.id.btnSwitchAccount);
        tvGreeting = popupWindowView.findViewById(R.id.tvLoadingMsg);
    }

    private void addListeners() {

    }

    public static class GreetingPopupBuilder extends Popup.PopupBuilder {
        private User currentUser;

        public GreetingPopupBuilder(Activity parent) {
            super(parent, R.layout.popup_greeting);
        }

        /**
         * Set the user that this greeting popup greets.
         * @param currentUser User object
         * @return {@link #GreetingPopupBuilder}
         * @see User
         */
        public GreetingPopupBuilder currentUser(User currentUser) {
            this.currentUser = currentUser;
            return this;
        }

        public GreetingPopup build() {
            return new GreetingPopup(this);
        }
    }
}
