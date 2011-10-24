package com.googlecode.wicketelements.components.togglepane;

public interface TogglePaneModelListener {
    void togglePaneEnabled(final TogglePaneModelEvent modelEventParam);

    void togglePaneDisabled(TogglePaneModelEvent modelEventParam);

    void togglePaneCollapsed(TogglePaneModelEvent modelEventParam);

    void togglePaneExpanded(TogglePaneModelEvent modelEventParam);
}
