package com.group0536.puzzlemazing.views.appinit;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
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

    // Listeners
    private View.OnClickListener switchAccountOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mode = Mode.SWITCH_ACCOUNT;
            popupWindow.dismiss();
        }
    };
    private View.OnClickListener continueOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mode = Mode.CONTINUE;
            popupWindow.dismiss();
        }
    };

    // Data and Rules
    private User currentUser;
    private Mode mode;
    enum Mode {
        CONTINUE,
        SWITCH_ACCOUNT
    }

    Mode getMode() {
        return mode;
    }

    private GreetingPopup(GreetingPopupBuilder builder) {
        super(builder);
        currentUser = builder.currentUser;
        bindViews();
        initViews();
        addListeners();
    }

    private void initViews() {
        String greetingPrefix = getString(R.string.app_welcome_greeting);
        String greetingMessage = String.format(greetingPrefix, currentUser.getUsername());
        tvGreeting.setText(greetingMessage);

        String continuePrefix = getString(R.string.app_welcome_continue);
        String continueMessage = String.format(continuePrefix, currentUser.getUsername());
        btnContinue.setText(continueMessage);
    }

    private void bindViews() {
        btnContinue = popupWindowView.findViewById(R.id.btnContinue);
        btnSwitchAccount = popupWindowView.findViewById(R.id.btnSwitchAccount);
        tvGreeting = popupWindowView.findViewById(R.id.tvGreeting);
    }

    private void addListeners() {
        btnContinue.setOnClickListener(continueOnClickListener);
        btnSwitchAccount.setOnClickListener(switchAccountOnClickListener);
    }

    public static class GreetingPopupBuilder extends Popup.PopupBuilder {
        private User currentUser;

        public GreetingPopupBuilder(Activity parent, Context context) {
            super(parent, R.layout.popup_greeting, context);
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
