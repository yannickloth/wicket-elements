package com.googlecode.wicketelements.components.accordion;

import java.util.EventObject;

public class AccordionStateEvent<T extends AccordionState> extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AccordionStateEvent(final T source) {
        super(source);
    }

    @Override
    public T getSource() {
        return (T) super.getSource();
    }
}
