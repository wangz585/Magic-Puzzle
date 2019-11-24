package com.group0536.puzzlemazing.views;

import android.content.Context;
import android.view.View;

import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.dispatcher.FluxBus;
import com.group0536.puzzlemazing.stores.Store;
import com.squareup.otto.ThreadEnforcer;

public abstract class FluxView extends View{
    protected Dispatcher dispatcher;

    public FluxView(Context context) {
        super(context);
        dispatcher = Dispatcher.getDispatcher(new FluxBus(ThreadEnforcer.ANY));
        initFluxComponents();
    }

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

    protected void registerStore(Store store) {
        dispatcher.register(store);
    }

    protected void unregisterStore(Store store) {
        dispatcher.register(store);
    }
}
