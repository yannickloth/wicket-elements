package com.googlecode.wicketelements.components.togglepane;

import java.util.EventObject;

public class TogglePaneStateEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param sourceParam The object on which the Event initially occurred.
     * @throws IllegalArgumentException if sourceParam is null.
     */
    public TogglePaneStateEvent(final TogglePaneState sourceParam) {
        super(sourceParam);
    }

    @Override
    public TogglePaneState getSource() {
        return (TogglePaneState) super.getSource();
    }
}
