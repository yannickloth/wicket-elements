package com.googlecode.wicketelements.components.accordion;

import java.util.EventObject;

public class AccordionModelEvent<T extends AccordionModel> extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AccordionModelEvent(final T source) {
        super(source);
    }

    @Override
    public T getSource() {
        return (T) super.getSource();
    }
}
