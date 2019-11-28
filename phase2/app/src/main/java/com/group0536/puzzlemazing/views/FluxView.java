package com.group0536.puzzlemazing.views;

import android.content.Context;
import android.view.View;

import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.dispatcher.FluxBus;
import com.group0536.puzzlemazing.stores.Store;
import com.squareup.otto.ThreadEnforcer;

/**
 * This is a view associating with our Flux design pattern.
 * Extend your view with this class when using the flux design.
 *
 * This class belongs to our Flux framework. You should not modify this file
 * when adding new features.
 *
 * @author Zhenyu Wang
 * @version 1.0.2
 * @since 1.0
 */
public abstract class FluxView extends View{
    protected Dispatcher dispatcher;

    public FluxView(Context context) {
        super(context);
        dispatcher = Dispatcher.getDispatcher(new FluxBus(ThreadEnforcer.ANY));
        initFluxComponents();
    }

    /**
     * Initialize all stores and action creators associating with
     * this view.
     */
    protected abstract void initFluxComponents();

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dispatcher.unregister(this);

    }

    @Override
    protected void onAttachedToWindow(){
        super.onAttachedToWindow();
        dispatcher.register(this);
    }

    /**
     * Register a store relating to this activity
     * @param store store to register
     */
    protected void registerStore(Store store) {
        dispatcher.register(store);
    }

    /**
     * Unregister a store relating to this activity
     * @param store store to register
     */
    protected void unregisterStore(Store store) {
        dispatcher.register(store);
    }
}
