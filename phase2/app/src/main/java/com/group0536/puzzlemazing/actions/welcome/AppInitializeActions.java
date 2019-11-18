package com.group0536.puzzlemazing.actions.welcome;

public interface AppInitializeActions {
    String PREFIX = "app-init-";
    String CHECK_UPDATE = PREFIX + "check-update";
    String LOAD_SAVED_TOKEN = PREFIX + "load-saved-token";
    String VERIFY_TOKEN = PREFIX + "verify-token";

    // keys
    String KEY_REQUIRE_UPDATE = "require-update";
    String KEY_SAVED_TOKEN = "saved-token";
    String KEY_TOKEN_VALIDITY = "token-validity";
    String KEY_CURRENT_USER = "current-user";

    String KEY_ERROR_MESSAGE = "error-message";
}
