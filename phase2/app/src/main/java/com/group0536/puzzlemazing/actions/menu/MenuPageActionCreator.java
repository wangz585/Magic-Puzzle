package com.group0536.puzzlemazing.actions.menu;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;

public class MenuPageActionCreator extends ActionCreator implements MenuPageActions {
    public MenuPageActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void chooseSelectPage(User player){
        Action action = new Action.ActionBuilder(ENTER_SELECT_PAGE)
                .load("player", player)
                .build();
        dispatcher.dispatch(action);
    }

    public void goToSettingPage(User player){
        Action action = new Action.ActionBuilder(ENTER_SETTING_PAGE)
                .load("player", player)
                .build();
        dispatcher.dispatch(action);
    }
}
