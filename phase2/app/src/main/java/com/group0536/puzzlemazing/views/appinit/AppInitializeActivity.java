package com.group0536.puzzlemazing.views.appinit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.appinit.AppInitializeActionCreator;
import com.group0536.puzzlemazing.models.AppInitProgress;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.stores.appinit.AppInitializeStore;
import com.group0536.puzzlemazing.stores.appinit.AppInitializeStoreChangeEvent;
import com.group0536.puzzlemazing.utils.ActivityUtil;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.squareup.otto.Subscribe;

/**
 * This activity displays when the application is initializing.
 * Initialization includes loading user preferences and identifying whether
 * the user has logged in before.
 *
 * @author Jimmy
 */
public class AppInitializeActivity extends FluxActivity {
    private static final String TAG = "AppInit";

    // Flux
    AppInitializeStore store;
    AppInitializeActionCreator actionCreator;

    // Components
    ImageView imgLoading;
    AnimationDrawable animLoading;
    TextView tvLoadingMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_initialize);

        bindViews();
    }

    private void bindViews() {
        imgLoading = findViewById(R.id.imgLoading);
        animLoading = ActivityUtil.setLoadingImage(imgLoading);
        tvLoadingMsg = findViewById(R.id.tvLoadingMsg);
    }

    @Override
    protected void initFluxComponents() {
        store = AppInitializeStore.getInstance(dispatcher);
        actionCreator = AppInitializeActionCreator.getInstance(dispatcher, getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerStore(store);
        actionCreator.startInitialization();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterStore(store);
    }

    private boolean isEmpty(String text) {
        return text == null || text.equals("");
    }


    @Subscribe
    public void onAppInitializeStoreChange(AppInitializeStoreChangeEvent e) {
        AppInitProgress progress = store.getProgress();
        if (progress == null) {
            System.exit(1);
            return;
        }

        if (progress.hasError()) {
            promptErrorMessage(progress.getErrorMessage());
            return;
        }

        performActionByProgress(progress);
    }


    /**
     * Perform the guessing_next required initialization based on the current progress
     *
     * @param progress current progress object that does not contain error
     */
    private void performActionByProgress(@NonNull AppInitProgress progress) {
        if (!progress.isUpdateCheckDone()) {
            checkForUpdate();
        } else if (!progress.isLoadSavedTokenDone()) {
            verifySavedToken();
        } else if (!progress.isLogInUserDone()) {
            promptLogIn();
        } else {
            saveUserToken();
            transitToMenu();
        }
    }

    private void transitToMenu() {
        AppInitializeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AppInitializeActivity.this, MenuActivity.class);
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(AppInitializeActivity.this).toBundle());
            }
        });
    }

    private void checkForUpdate() {
        setLoadingMessage(R.string.app_init_checking_update);
        actionCreator.checkUpdate();
    }

    private void verifySavedToken() {
        setLoadingMessage(R.string.app_init_loading_token);
        String token = getTokenFromPreferences();
        actionCreator.verifyToken(token);
    }

    private void saveUserToken() {
        setLoadingMessage(R.string.app_init_saving_token);
        String token = store.getCurrentUser().getToken();
        saveTokenToPreferences(token);
    }

    /**
     * Get previously saved user token from shared preferences.
     * @return previously saved user token
     */
    private String getTokenFromPreferences() {
        SharedPreferences preferences =
                getSharedPreferences("app-init", Context.MODE_PRIVATE);
        return preferences.getString(getString(R.string.app_welcome_key_user_token), "");
    }

    private void saveTokenToPreferences(String token) {
        SharedPreferences preferences = getSharedPreferences("app-init", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(getString(R.string.app_welcome_key_user_token), token);
        editor.apply();
    }

    private void promptLogIn() {
        User currentUser = store.getCurrentUser();
        if (currentUser == null) {
            setLoadingMessage(R.string.app_init_complete_login);
            promptForCredential();
        } else {
            setLoadingMessage(R.string.app_init_waiting_response);
            promptGreeting(currentUser);
        }
    }

    private void promptForCredential() {
        final CredentialPopup credentialPopup = (CredentialPopup)
                new CredentialPopup.CredentialPopupBuilder(this, getApplicationContext())
                .widthPercent(0.7)
                .heightPercent(0.7)
                .animationStyle(R.style.WindowFade)
                .build();
        credentialPopup.show(Gravity.CENTER, 0, 0);
        handleLogInComplete(credentialPopup);
    }

    private void handleLogInComplete(final CredentialPopup credentialPopup) {
        credentialPopup.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                String username = credentialPopup.getUsername();
                String password = credentialPopup.getPassword();

                if (username.isEmpty() || password.isEmpty()) {
                    credentialPopup.show(Gravity.CENTER, 0, 0);
                    return;
                }

                if (credentialPopup.getMode() == CredentialPopup.Mode.LOG_IN) {
                    actionCreator.logIn(username, password);
                } else {
                    actionCreator.register(username, password);
                }
            }
        });
    }

    private void promptGreeting(User currentUser) {
        GreetingPopup greetingPopup = (GreetingPopup) new GreetingPopup.GreetingPopupBuilder(
                this, getApplicationContext())
                .currentUser(currentUser)
                .widthPercent(0.7)
                .heightPercent(0.7)
                .animationStyle(R.style.WindowFade)
                .build();
        greetingPopup.show(Gravity.CENTER, 0, 0);
        handleGreetingComplete(greetingPopup);
    }

    private void handleGreetingComplete(final GreetingPopup greetingPopup) {
        greetingPopup.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (greetingPopup.getMode() == GreetingPopup.Mode.SWITCH_ACCOUNT) {
                    actionCreator.clearUser();
                } else {
                    actionCreator.skipLogIn();
                }
            }
        });
    }

    private void setLoadingMessage(final int messageId) {
        AppInitializeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvLoadingMsg.setText(messageId);
            }
        });
    }

    /**
     * Construct an alert dialog with an error message during initialization time.
     * The dialog allows a user to select try again with the operation in error, or
     * exit the program.
     *
     * @param message the error message to be displayed
     */
    private void promptErrorMessage(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_try_again, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        actionCreator.restartInitialization();
                    }
                })
                .setNegativeButton(R.string.dialog_exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        actionCreator.finishInitialization();
                    }
                });
        AppInitializeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
