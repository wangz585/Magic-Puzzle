package com.group0536.puzzlemazing.actions;

import java.util.HashMap;

/**
 * This class represents an action object in the Flux pattern.
 * You should not instantiate this class directly -- instead, use
 * Action.ActionBuilder.
 *
 * This class belongs to our Flux framework. You should not modify this file
 * when adding new features.
 *
 * @author Jimmy
 * @version 1.2
 * @since 1.0
 */
public class Action {
    private final String type;
    private final boolean error;
    private final HashMap<String, Object> payload;

    private Action(ActionBuilder builder) {
        this.type = builder.type;
        this.payload = builder.payload;
        this.error = builder.error;
    }

    public String getType() {
        return type;
    }

    public boolean isError() {
        return error;
    }

    /**
     * Get an entry by key in the payload
     * @param key key of entry
     * @return Object corresponding to key
     */
    public Object getPayloadEntry(String key) {
        return payload.get(key);
    }

    public static class ActionBuilder {
        private final String type;
        private boolean error;
        private HashMap<String, Object> payload;

        public ActionBuilder(String type) {
            this.payload = new HashMap<>();
            this.type = type;
        }

        /**
         * Set whether the action is resulted from an error
         * @param error set to true if action is resulted from an error
         * @return updated Builder
         */
        public ActionBuilder error(boolean error) {
            this.error = error;
            return this;
        }

        /**
         * Load a key-value pair into payload of action object
         * @param key key to load
         * @param value value to load
         * @return updated Builder
         */
        public ActionBuilder load(String key, Object value) {
            if (key == null) {
                throw new IllegalArgumentException("A key in action payload cannot be null.");
            }
            if (value == null) {
                throw new IllegalArgumentException("A value in action payload cannot be null.");
            }
            payload.put(key, value);
            return this;
        }

        public Action build() {
            return new Action(this);
        }
    }
}
