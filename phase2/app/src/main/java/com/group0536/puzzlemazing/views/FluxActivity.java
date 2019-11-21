package com.group0536.puzzlemazing.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.dispatcher.FluxBus;
import com.group0536.puzzlemazing.stores.Store;
import com.squareup.otto.ThreadEnforcer;

/**
 * This is an activity associating with our Flux design pattern.
 * Extend your view with this class when using the flux design.
 *
 * This class belongs to our Flux framework. You should not modify this file
 * when adding new features.
 *
 * @author Jimmy
 * @version 1.3.2
 * @since 1.0
 */
public abstract class FluxActivity extends AppCompatActivity {
    protected Dispatcher dispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatcher = Dispatcher.getDispatcher(new FluxBus(ThreadEnforcer.ANY));
        initFluxComponents();
    }

    /**
     * Initialize all stores and action creators associating with
     * this view.
     */
    protected abstract void initFluxComponents();

    /**
     * Register a store relating to this activity
     * @param store store to register
     */
    protected void registerStore(Store store) {
        dispatcher.register(store);
    }

    /**
     * Unregister a store relating to this activity
     * @param store store to unregister
     */
    protected void unregisterStore(Store store) {
        dispatcher.register(store);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dispatcher.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dispatcher.unregister(this);
    }
}
