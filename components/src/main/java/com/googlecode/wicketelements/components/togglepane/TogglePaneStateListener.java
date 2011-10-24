package com.googlecode.wicketelements.components.togglepane;

public interface TogglePaneStateListener {
    void togglePaneEnabled(final TogglePaneStateEvent stateEventParam);

    void togglePaneDisabled(TogglePaneStateEvent stateEventParam);

    void togglePaneCollapsed(TogglePaneStateEvent stateEventParam);

    void togglePaneExpanded(TogglePaneStateEvent stateEventParam);
}
