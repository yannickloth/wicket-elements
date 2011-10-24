package com.googlecode.wicketelements.components.accordion;

import com.googlecode.jbp.common.requirements.Reqs;
import com.googlecode.wicketelements.components.togglepane.TogglePaneModel;
import com.googlecode.wicketelements.components.togglepane.TogglePaneModelEvent;
import com.googlecode.wicketelements.components.togglepane.TogglePaneModelListener;

import java.util.List;

public abstract class AbstractAccordionModel implements AccordionModel, TogglePaneModelListener {
    boolean maximumOneTogglePaneExpanded;
    private List<TogglePaneModel> expandedTogglePaneModels;

    public boolean isMaximumOneTogglePaneExpanded() {
        return maximumOneTogglePaneExpanded;
    }

    public void setMaximumOneTogglePaneExpanded(final boolean maximumOneTogglePaneExpandedParam) {
        maximumOneTogglePaneExpanded = maximumOneTogglePaneExpandedParam;
    }

    public final void togglePaneEnabled(final TogglePaneModelEvent modelEventParam) {
        //Do nothing
    }

    public final void togglePaneDisabled(final TogglePaneModelEvent modelEventParam) {
        //Do nothing
    }

    public final void togglePaneCollapsed(final TogglePaneModelEvent modelEventParam) {
        //Do nothing
    }

    public final void togglePaneExpanded(final TogglePaneModelEvent modelEventParam) {
        if (maximumOneTogglePaneExpanded) { //collapse all other expanded panes
            Reqs.PRE_COND.Logic.requireTrue(expandedTogglePaneModels.size() <= 1, "There must be maximum one expanded pane.");
            for (final TogglePaneModel current : expandedTogglePaneModels) {
                current.toggleContent();
            }
            expandedTogglePaneModels.add(modelEventParam.getSource());
            Reqs.POST_COND.Logic.requireTrue(expandedTogglePaneModels.size() == 1, "There must be exactly one expanded pane.");
        }
    }
}
