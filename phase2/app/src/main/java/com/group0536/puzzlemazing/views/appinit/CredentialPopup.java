package com.group0536.puzzlemazing.views.appinit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.views.Popup;

class CredentialPopup extends Popup {
    private static final String TAG = "credentialPopup";
    // Components
    private Button btnRegister;
    private Button btnLogIn;
    private EditText txtUsername;
    private EditText txtPassword;
    private TextView tvErrorMessage;

    // Rules
    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MIN_PASSWORD_LENGTH = 6;
    enum Mode {
        LOG_IN,
        REGISTER
    }

    // Data: to be accessed from other activities
    private String username = "";
    private String password = "";
    private Mode mode = Mode.LOG_IN;

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    Mode getMode() {
        return mode;
    }

    // Listeners
    private View.OnClickListener authenticationButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            username = txtUsername.getText().toString();
            password = txtPassword.getText().toString();
            setModeByButton((Button) view);
            if (!containsError(username, password)) {
                popupWindow.dismiss();
            }
        }
    };

    private void setModeByButton(Button btn) {
        String btnText = btn.getText().toString();
        String registerString = getString(R.string.app_welcome_register);
        if (btnText.equals(registerString)) {
            mode = Mode.REGISTER;
        } else {
            mode = Mode.LOG_IN;
        }
    }

    private CredentialPopup(CredentialPopupBuilder builder) {
        super(builder);
        bindViews();
        addListeners();
        tvErrorMessage.setText(builder.errorMessage);
    }

    private void bindViews() {
        btnRegister = popupWindowView.findViewById(R.id.btnRegister);
        btnLogIn = popupWindowView.findViewById(R.id.btnLogin);
        txtUsername = popupWindowView.findViewById(R.id.txtUsername);
        txtPassword = popupWindowView.findViewById(R.id.txtPassword);
        tvErrorMessage = popupWindowView.findViewById(R.id.tvErrorMessage);
    }

    private void addListeners() {
        btnRegister.setOnClickListener(authenticationButtonOnClickListener);
        btnLogIn.setOnClickListener(authenticationButtonOnClickListener);
    }

    private String getString(int resourceId) {
        return context.getString(resourceId);
    }


    /**
     * Check whether the username and password are of valid format.
     * Return true if there are errors associated with them, false
     * if otherwise.
     * @param username username to be checked
     * @param password password to be checked
     * @return true if error exists, false otherwise
     */
    private boolean containsError(String username, String password) {
        String errorMessage = "";

        if (username.length() == 0 || password.length() == 0) {
            errorMessage = getString(
                    R.string.app_welcome_error_credential_required);
        }  else if (username.length() < MIN_USERNAME_LENGTH) {
            String messageTemplate = getString(
                    R.string.app_welcome_error_username_length);
            errorMessage = String.format(messageTemplate, MIN_USERNAME_LENGTH);
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            String messageTemplate = getString(
                    R.string.app_welcome_error_password_length);
            errorMessage = String.format(messageTemplate, MIN_PASSWORD_LENGTH);
        }

        boolean hasError = !errorMessage.isEmpty();
        if (hasError) {
            tvErrorMessage.setText(errorMessage);
        }
        return hasError;
    }


    public static class CredentialPopupBuilder extends Popup.PopupBuilder {
        private String errorMessage;

        public CredentialPopupBuilder(Activity parent, Context context) {
            super(parent, R.layout.popup_credential, context);
        }

        public CredentialPopupBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        @Override
        public Popup build() {
            return new CredentialPopup(this);
        }
    }
}
