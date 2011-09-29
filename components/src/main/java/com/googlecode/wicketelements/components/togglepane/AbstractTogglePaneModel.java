package com.googlecode.wicketelements.components.togglepane;

import com.googlecode.jbp.common.requirements.Reqs;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTogglePaneModel implements TogglePaneModel {
    private final List<TogglePaneModelListener> listeners = new ArrayList<TogglePaneModelListener>();

    public final void addEventListener(final TogglePaneModelListener togglePaneModelListenerParam) {
        Reqs.PARAM_REQ.Object.requireNotNull(togglePaneModelListenerParam, "The listener to add must not be null.");
        listeners.add(togglePaneModelListenerParam);
    }

    /**
     * {@code null is allowed.}
     *
     * @param togglePaneModelListenerParam
     */
    public final void removeEventListener(final TogglePaneModelListener togglePaneModelListenerParam) {
        if (togglePaneModelListenerParam != null) {
            listeners.remove(togglePaneModelListenerParam);
        }
    }

    public final void toggleContent() {
        final boolean visibility = getContentComponent().isVisible();
        getContentComponent().setVisible(!visibility);

        //send event to listeners
        final TogglePaneModelEvent event = new TogglePaneModelEvent(this);
        if (visibility) {
            for (final TogglePaneModelListener listener : listeners) {
                listener.togglePaneCollapsed(event);
            }
        } else {
            for (final TogglePaneModelListener listener : listeners) {
                listener.togglePaneExpanded(event);
            }
        }
    }

    public final void toggleEnableState() {
        final boolean enabled = getTitleComponent().isEnabled();
        getTitleComponent().setEnabled(!enabled);

        //send event to listeners
        final TogglePaneModelEvent event = new TogglePaneModelEvent(this);
        if (enabled) {
            for (final TogglePaneModelListener listener : listeners) {
                listener.togglePaneDisabled(event);
            }
        } else {
            for (final TogglePaneModelListener listener : listeners) {
                listener.togglePaneEnabled(event);
            }
        }
    }

    public final boolean isCollapsed() {
        return !isExpanded();
    }

    public final boolean isExpanded() {
        return getContentComponent().isVisible();
    }

    public final boolean isEnabled() {
        return getTitleComponent().isEnabled();
    }

    public final boolean isDisabled() {
        return !isEnabled();
    }
}

