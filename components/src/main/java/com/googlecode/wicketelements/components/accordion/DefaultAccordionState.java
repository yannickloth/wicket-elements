package com.googlecode.wicketelements.components.accordion;

import com.googlecode.jbp.common.requirements.Reqs;
import com.googlecode.wicketelements.components.accordion.ui.Accordion;
import com.googlecode.wicketelements.components.togglepane.TogglePaneState;
import com.googlecode.wicketelements.components.togglepane.TogglePaneStateEvent;
import com.googlecode.wicketelements.components.togglepane.ui.TogglePane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultAccordionState implements AccordionState {
    boolean maximumOneTogglePaneExpanded;
    private final Accordion accordion;
    private final List<TogglePaneState> expandedTogglePaneStates = new ArrayList<TogglePaneState>();

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
        expandedTogglePaneStates.remove(stateEventParam.getSource());
    }

    public final void togglePaneExpanded(final TogglePaneStateEvent stateEventParam) {
        //TODO check that the source corresponds to a toggle pane that actually belongs to this accordion
        final TogglePaneState state = stateEventParam.getSource();
        Reqs.PRE_COND.Logic.requireTrue(isTogglePaneBelongingToThisAccordion(state), "Expanded toggle pane does not belong to this accordion!");
        if (maximumOneTogglePaneExpanded) {
            Reqs.PRE_COND.Logic.requireTrue(expandedTogglePaneStates.size() <= 1, "There must be maximum one expanded pane.");
            final List<TogglePaneState> expandedTogglePanesStatesCopy = new ArrayList<TogglePaneState>(expandedTogglePaneStates);
            for (final TogglePaneState current : expandedTogglePanesStatesCopy) {
                current.toggleContent(); //collapse all expanded panes
            }
        }
        expandedTogglePaneStates.add(stateEventParam.getSource());
        Reqs.POST_COND.Logic.requireTrue(expandedTogglePaneStates.size() == 1, "There must be exactly one expanded pane.");
    }

    private boolean isTogglePaneBelongingToThisAccordion(final TogglePaneState togglePaneStateParam) {
        Reqs.PARAM_REQ.Object.requireNotNull(togglePaneStateParam, "TogglePaneState parameter must not be null.");
        boolean belongs = false;
        final Iterator<TogglePane> it = accordion.getTogglePanes().iterator();
        while (!belongs && it.hasNext()) {
            belongs = togglePaneStateParam.equals(it.next().getTogglePaneState());
        }
        return belongs;
    }
}
