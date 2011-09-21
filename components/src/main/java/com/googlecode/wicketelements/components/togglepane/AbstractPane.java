package com.googlecode.wicketelements.components.togglepane;

import org.apache.wicket.Component;

/**
 * An abstract pane may be:
 * - activated/deactivated: it's header is clickable so that it's content may be shown or hidden.
 * - exploded/collapsed: the content is shown or hidden.
 */
public abstract class AbstractPane {
    private Component content;
    private Component header;

    protected abstract Component getHeader(final String headerWicketIdParam);

    protected abstract Component getContent(final String contentWicketIdParam);

    final void toggleContent() {
        content.setVisible(!content.isVisible());
    }

    final void toggleActive() {
        //TODO deactivate the header AjaxFallbackLink to disable toggling of the pane
    }

    final boolean isActive() {
        return false; //TODO return the active state of the AjaxFallbackLink
    }

    final boolean isExploded() {
        return content.isVisible();
    }
}
