package com.group0536.puzzlemazing.actions.games.busyworker;

import com.group0536.puzzlemazing.actions.games.GameCommonActions;

public interface BusyWorkerActions extends GameCommonActions {
    String PREFIX = "busy-worker_worker-";
    String MOVE = PREFIX + "move";
    String INIT_MAP = PREFIX + "init-map";
}
