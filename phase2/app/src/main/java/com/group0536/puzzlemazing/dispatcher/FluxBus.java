package com.group0536.puzzlemazing.dispatcher;

import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of OTTO event bus. It is created to
 * better manage registered objects and avoid registration conflicts.
 *
 * This class belongs to our Flux framework. You should not modify this file
 * when adding new features.
 *
 * @author Jimmy
 * @version 1.0
 * @see com.squareup.otto.Bus
 * @since 1.0
 */
public class FluxBus extends Bus {
    private List<Object> registeredItems;

    public FluxBus() {
        super();
        this.registeredItems = new ArrayList<>();
    }

    @Override
    public void register(Object object) {
        if (!registeredItems.contains(object)) {
            registeredItems.add(object);
            super.register(object);
        }
    }

    @Override
    public void unregister(Object object) {
        if (registeredItems.contains(object)) {
            registeredItems.remove(object);
            super.unregister(object);
        }
    }
}
