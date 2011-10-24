package com.googlecode.wicketelements.components.accordion;

import com.googlecode.wicketelements.components.togglepane.TogglePaneModel;

import java.util.List;

public interface AccordionModel {
    List<? extends TogglePaneModel> getTogglePaneModels();

    boolean isMaximumOneTogglePaneExpanded();

    void setMaximumOneTogglePaneExpanded(boolean maximumOneTogglePaneExpandedParam);
}
