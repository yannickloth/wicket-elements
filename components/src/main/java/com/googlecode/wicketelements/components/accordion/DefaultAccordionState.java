package com.googlecode.wicketelements.components.accordion;

import com.googlecode.jbp.common.requirements.Reqs;
import com.googlecode.wicketelements.components.accordion.ui.Accordion;
import com.googlecode.wicketelements.components.togglepane.TogglePaneState;
import com.googlecode.wicketelements.components.togglepane.TogglePaneStateEvent;
import com.googlecode.wicketelements.components.togglepane.TogglePaneStateListener;
import com.googlecode.wicketelements.components.togglepane.ui.TogglePane;

import java.util.List;

public class DefaultAccordionState implements AccordionState, TogglePaneStateListener {
    boolean maximumOneTogglePaneExpanded;
    private Accordion accordion;
    private List<TogglePaneState> expandedTogglePaneStates;

    public DefaultAccordionState(final Accordion accordionParam) {
        Reqs.PARAM_REQ.Object.requireNotNull(accordionParam, "Accordion must not be null.");
        accordion = accordionParam;
    }

    public boolean isMaximumOneTogglePaneExpanded() {
        return maximumOneTogglePaneExpanded;
    }

    public void setMaximumOneTogglePaneExpanded(final boolean maximumOneTogglePaneExpandedParam) {
        maximumOneTogglePaneExpanded = maximumOneTogglePaneExpandedParam;
    }

    public void disableAllTogglePanes() {
        for (final TogglePane current : accordion.getTogglePanes()) {
            final TogglePaneState currentState = current.getTogglePaneState();
            if (currentState.isEnabled()) {
                currentState.toggleEnableState();
            }
        }
    }

    public void enableAllTogglePanes() {
        for (final TogglePane current : accordion.getTogglePanes()) {
            final TogglePaneState currentState = current.getTogglePaneState();
            if (currentState.isDisabled()) {
                currentState.toggleEnableState();
            }
        }
    }

    public void collapseAllTogglePanes() {
        for (final TogglePane current : accordion.getTogglePanes()) {
            final TogglePaneState currentState = current.getTogglePaneState();
            if (currentState.isExpanded()) {
                currentState.toggleContent();
            }
        }
    }

    public void expandAllTogglePanes() {
        for (final TogglePane current : accordion.getTogglePanes()) {
            final TogglePaneState currentState = current.getTogglePaneState();
            if (currentState.isCollapsed()) {
                currentState.toggleContent();
            }
        }
    }

    public final void togglePaneEnabled(final TogglePaneStateEvent stateEventParam) {
        //Do nothing
    }

    public final void togglePaneDisabled(final TogglePaneStateEvent stateEventParam) {
        //Do nothing
    }

    public final void togglePaneCollapsed(final TogglePaneStateEvent stateEventParam) {
        //Do nothing
    }

    public final void togglePaneExpanded(final TogglePaneStateEvent stateEventParam) {
        //TODO check that the source corresponds to a toggle pane that actually belongs to this accordion
        if (true) {
            if (maximumOneTogglePaneExpanded) { //collapse all other expanded panes
                Reqs.PRE_COND.Logic.requireTrue(expandedTogglePaneStates.size() <= 1, "There must be maximum one expanded pane.");
                for (final TogglePaneState current : expandedTogglePaneStates) {
                    current.toggleContent();
                    expandedTogglePaneStates.remove(current);
                }
            }
            expandedTogglePaneStates.add(stateEventParam.getSource());
            Reqs.POST_COND.Logic.requireTrue(expandedTogglePaneStates.size() == 1, "There must be exactly one expanded pane.");
        }
    }
}
