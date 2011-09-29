package com.googlecode.wicketelements.components.togglepane;

import org.apache.wicket.Component;

public interface TogglePaneModel {
    Component getContentComponent();

    Component getTitleComponent();

    void toggleContent();

    void toggleEnableState();

    boolean isCollapsed();

    boolean isExpanded();

    boolean isEnabled();

    boolean isDisabled();

    void addEventListener(final TogglePaneModelListener togglePaneModelListenerParam);

    void removeEventListener(final TogglePaneModelListener togglePaneModelListenerParam);
}
