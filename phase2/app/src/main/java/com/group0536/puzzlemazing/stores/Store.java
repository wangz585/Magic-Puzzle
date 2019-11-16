package com.group0536.puzzlemazing.stores;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

/**
 * This class represents a store object which holds specific information
 * relating to a feature, a view, or a custom object.
 * The constructor of a store is protected because there should only be one
 * store for each particular feature, view, or custom object.
 * Please implement Singleton when extending this class for best practices.
 *
 * This class belongs to our Flux framework. You should not modify this file
 * when adding new features.
 *
 * @author Jimmy
 * @version 1.0.5
 * @since 1.0
 */
public abstract class Store {
    private Dispatcher dispatcher;

    protected Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Emit a change event for this store.
     */
    protected void postChange() {
        dispatcher.dispatch(getChangeEvent());
    }

    /**
     * Obtain default change event for the current store
     * @return change event for the current store
     */
    protected abstract StoreChangeEvent getChangeEvent();

    /**
     * This method is called when an action is dispatched.
     * Please make sure to annotate this method by @Subscribe in order for it
     * to work properly.
     * @param action action object dispatched
     */
    public abstract void onAction(Action action);
}
