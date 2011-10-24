package com.googlecode.wicketelements.components.togglepane;

import java.util.EventObject;

public class TogglePaneModelEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TogglePaneModelEvent(final TogglePaneModel source) {
        super(source);
    }

    @Override
    public TogglePaneModel getSource() {
        return (TogglePaneModel) super.getSource();
    }
}
