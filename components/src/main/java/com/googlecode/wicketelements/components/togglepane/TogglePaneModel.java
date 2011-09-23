package com.googlecode.wicketelements.components.togglepane;

import org.apache.wicket.Component;

public interface TogglePaneModel {
    Component getContentObject();

    Component getTitleObject();

    void toggleContent();

    void toggleEnableState();

    boolean isCollapsed();

    boolean isExpanded();

    boolean isEnabled();

    boolean isDisabled();
}
