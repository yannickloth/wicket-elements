package com.googlecode.wicketelements.components.accordion;

import java.io.Serializable;

public interface AccordionState extends Serializable {

    boolean isMaximumOneTogglePaneExpanded();

    void setMaximumOneTogglePaneExpanded(final boolean maximumOneTogglePaneExpandedParam);

    void disableAllTogglePanes();

    void enableAllTogglePanes();

    void collapseAllTogglePanes();

    void expandAllTogglePanes();
}
