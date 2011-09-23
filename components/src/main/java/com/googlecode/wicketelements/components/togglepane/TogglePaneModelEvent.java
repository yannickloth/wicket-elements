package com.googlecode.wicketelements.components.togglepane;

import java.util.EventObject;

public class TogglePaneModelEvent<T extends TogglePaneModel> extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TogglePaneModelEvent(final T source) {
        super(source);
    }

    @Override
    public T getSource() {
        return (T) super.getSource();
    }
}
