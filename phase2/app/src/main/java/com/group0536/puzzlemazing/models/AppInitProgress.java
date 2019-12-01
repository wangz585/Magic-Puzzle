package com.group0536.puzzlemazing.models;

import android.content.Context;

/**
 * This class stores the progress during the initialization of application.
 *
 * @author Jimmy Lan
 */
public class AppInitProgress {
    // Steps required to initialize application
    private boolean isUpdateCheckDone;
    private boolean isLoadSavedTokenDone;
    private boolean isLogInUserDone;

    /**
     * errorMessage associating with errors. It is empty if no error occurred.
     */
    private String errorMessage;

    /**
     * The context where the app initialization is taking place.
     * This is needed for the access of error message strings.
     */
    private Context context;

    public AppInitProgress(Context context) {
        this.context = context;
    }

    public boolean isUpdateCheckDone() {
        return isUpdateCheckDone;
    }

    public void setUpdateCheckDone(boolean updateCheckDone) {
        isUpdateCheckDone = updateCheckDone;
    }

    public boolean isLoadSavedTokenDone() {
        return isLoadSavedTokenDone;
    }

    public void setLoadSavedTokenDone(boolean loadSavedTokenDone) {
        isLoadSavedTokenDone = loadSavedTokenDone;
    }

    public boolean isLogInUserDone() {
        return isLogInUserDone;
    }

    public void setLogInUserDone(boolean logInUserDone) {
        isLogInUserDone = logInUserDone;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(int errorMessageId) {
        errorMessage = context.getString(errorMessageId);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean hasError() {
        return !isEmpty(errorMessage);
    }

    private boolean isEmpty(String text) {
        return text == null || text.equals("");
    }
}
