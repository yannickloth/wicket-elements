package com.googlecode.wicketelements.components.togglepane;

import java.io.Serializable;

public interface TogglePaneState extends Serializable {

    void toggleContent();

    void toggleEnableState();

    boolean isCollapsed();

    boolean isExpanded();

    boolean isEnabled();

    boolean isDisabled();

    void addEventListener(final TogglePaneStateListener togglePaneStateListenerParam);

    void removeEventListener(final TogglePaneStateListener togglePaneStateListenerParam);
}
