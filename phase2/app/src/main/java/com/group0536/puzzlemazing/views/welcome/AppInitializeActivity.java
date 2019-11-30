package com.group0536.puzzlemazing.views.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.welcome.AppInitializeActionCreator;
import com.group0536.puzzlemazing.models.AppInitProgress;
import com.group0536.puzzlemazing.stores.welcome.AppInitializeStore;
import com.group0536.puzzlemazing.stores.welcome.AppInitializeStoreChangeEvent;
import com.group0536.puzzlemazing.utils.ActivityUtil;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.Popup;
import com.squareup.otto.Subscribe;

/**
 * This activity displays when the application is initializing.
 * Initialization includes loading user preferences and identifying whether
 * the user has logged in before.
 *
 * @author Jimmy
 */
public class AppInitializeActivity extends FluxActivity {
    private final String TAG = "AppInit";

    // Flux
    AppInitializeStore store;
    AppInitializeActionCreator actionCreator;

    // Components
    ImageView imgLoading;
    AnimationDrawable animLoading;
    TextView tvLoadingMsg;
    View popup;

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
        actionCreator = new AppInitializeActionCreator(dispatcher);
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
        updateUI();
    }

    /**
     * Updates user interface based on the current store information.
     */
    private void updateUI() {
        AppInitProgress progress = store.getProgress();
        if (progress == null) {
            System.exit(1);
            return;
        }

        if (progress.hasError()) {
            promptErrorMessage(progress.getErrorMessageId());
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
            loadSavedToken();
        } else if (!progress.isLogInUserDone()) {
            logInUser();
        }
    }

    private void checkForUpdate() {
        setLoadingMessage(R.string.app_init_checking_update);
        actionCreator.checkUpdate();
    }

    private void loadSavedToken() {
        setLoadingMessage(R.string.app_init_loading_token);
        actionCreator.loadSavedToken(getApplicationContext());
    }

    private void logInUser() {
        String token = store.getSavedToken();

        if (isEmpty(token)) {
            Popup credentialPopup = new Popup.PopupBuilder(this, R.layout.popup_credential)
                    .focusable(false)
                    .widthPercent(0.6)
                    .heightPercent(0.6)
                    .animationStyle(R.style.WindowFade)
                    .build();
            credentialPopup.show(Gravity.CENTER, 0, 0);
            setLoadingMessage(R.string.app_init_complete_login);
        } else {
            setLoadingMessage(R.string.app_init_logging_in);
            actionCreator.verifyToken(token);
        }
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
     * @param messageId the resource id of the error message
     */
    private void promptErrorMessage(int messageId) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(messageId)
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
