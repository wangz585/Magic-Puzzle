package com.group0536.puzzlemazing.views.appinit;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.views.Popup;

class CredentialPopup extends Popup {
    private static final String TAG = "credentialPopup";
    // Components
    private Button btnRegister;
    private Button btnLogIn;
    private EditText txtUsername;
    private EditText txtPassword;

    // Listeners
    private View.OnClickListener registerOnClickListener;
    private View.OnClickListener loginOnClickListener;

    public void setRegisterOnClickListener(View.OnClickListener registerOnClickListener) {
        this.registerOnClickListener = registerOnClickListener;
    }

    public void setLoginOnClickListener(View.OnClickListener loginOnClickListener) {
        this.loginOnClickListener = loginOnClickListener;
    }

    private CredentialPopup(CredentialPopupBuilder builder) {
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
