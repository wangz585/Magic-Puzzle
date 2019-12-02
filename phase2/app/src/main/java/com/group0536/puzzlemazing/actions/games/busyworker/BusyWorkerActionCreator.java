package com.group0536.puzzlemazing.actions.games.busyworker;

import android.graphics.Point;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;


public class BusyWorkerActionCreator extends ActionCreator implements BusyWorkerActions{

    public BusyWorkerActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void move(Point position){
        Action action = new Action.ActionBuilder(MOVE)
                .load("position", position)
                .build();
        dispatcher.dispatch(action);
    }

    public void initMap(int level){
        Action action = new Action.ActionBuilder(INIT_MAP)
                .load("level", level)
                .build();
        dispatcher.dispatch(action);
    }


}
