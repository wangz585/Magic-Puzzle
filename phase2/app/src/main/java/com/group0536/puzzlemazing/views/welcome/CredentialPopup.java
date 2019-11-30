package com.group0536.puzzlemazing.views.welcome;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.views.Popup;

class CredentialPopup extends Popup {
    private static final String TAG = "credentialPopup";
    // Components
    Button btnRegister;
    Button btnLogIn;
    EditText txtUsername;
    EditText txtPassword;

    // Listeners
    View.OnClickListener registerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: Register");
        }
    };
    View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: Login");
        }
    };

    private CredentialPopup(PopupBuilder builder) {
        super(builder);
        bindViews();
        addListeners();
    }

    private void bindViews() {
        btnRegister = popupWindowView.findViewById(R.id.btnRegister);
        btnLogIn = popupWindowView.findViewById(R.id.btnLogin);
        txtUsername = popupWindowView.findViewById(R.id.txtUsername);
        txtPassword = popupWindowView.findViewById(R.id.txtPassword);
    }

    private void addListeners() {
        btnRegister.setOnClickListener(registerOnClickListener);
        btnLogIn.setOnClickListener(loginOnClickListener);
    }

    public static class CredentialPopupBuilder extends Popup.PopupBuilder {
        public CredentialPopupBuilder(Activity parent) {
            super(parent, R.layout.popup_credential);
        }

        @Override
        public Popup build() {
            return new CredentialPopup(this);
        }
    }
}
