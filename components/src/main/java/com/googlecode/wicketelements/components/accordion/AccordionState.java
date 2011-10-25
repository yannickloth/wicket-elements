package com.googlecode.wicketelements.components.accordion;

import com.googlecode.wicketelements.components.togglepane.TogglePaneStateListener;

import java.io.Serializable;

public interface AccordionState extends Serializable, TogglePaneStateListener {

    boolean isMaximumOneTogglePaneExpanded();

    void setMaximumOneTogglePaneExpanded(final boolean maximumOneTogglePaneExpandedParam);

    void disableAllTogglePanes();

    void enableAllTogglePanes();

    void collapseAllTogglePanes();

    void expandAllTogglePanes();
}
