package com.group0536.puzzlemazing.actions.menu;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;

public class MenuActionCreator extends ActionCreator implements MenuActions {
    public MenuActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void chooseSelectPage(User user){
        Action action = new Action.ActionBuilder(ENTER_SELECT_PAGE)
                .load("user", user)
                .build();
        dispatcher.dispatch(action);
    }

    public void goToSettingPage(User user){
        Action action = new Action.ActionBuilder(ENTER_SETTING_PAGE)
                .load("user", user)
                .build();
        dispatcher.dispatch(action);
    }
}
