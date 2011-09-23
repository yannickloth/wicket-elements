package com.googlecode.wicketelements.components.togglepane;

public abstract class AbstractTogglePaneModel implements TogglePaneModel {

    public final void toggleContent() {
        final boolean visibility = getContentObject().isVisible();
        getContentObject().setVisible(!visibility);
    }

    public final void toggleEnableState() {
        final boolean enabled = getTitleObject().isEnabled();
        getTitleObject().setEnabled(!enabled);
    }

    public final boolean isCollapsed() {
        return !isExpanded();
    }

    public final boolean isExpanded() {
        return getContentObject().isVisible();
    }

    public final boolean isEnabled() {
        return getTitleObject().isEnabled();
    }

    public final boolean isDisabled() {
        return !isEnabled();
    }
}
