package com.googlecode.wicketelements.components.togglepane;

public interface TogglePaneModelListener<T extends TogglePaneModel> {
    void togglePaneEnabled(final TogglePaneModelEvent<T> modelEventParam);

    void togglePaneDisabled(TogglePaneModelEvent<T> modelEventParam);

    void togglePaneCollapsed(TogglePaneModelEvent<T> modelEventParam);

    void togglePaneExpanded(TogglePaneModelEvent<T> modelEventParam);
}
