package com.googlecode.wicketelements.components.accordion.ui;


import com.googlecode.jbp.common.requirements.Reqs;
import com.googlecode.wicketelements.components.accordion.AccordionState;
import com.googlecode.wicketelements.components.accordion.DefaultAccordionState;
import com.googlecode.wicketelements.components.togglepane.ui.TogglePane;
import com.googlecode.wicketelements.library.behavior.AttributeAppenderFactory;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Accordion extends Panel {
    private static final String TOGGLE_PANES_LIST_WICKET_ID = "we-accordion-togglePanes";
    private static final String TOGGLE_PANES_LIST_ELEMENT_WICKET_ID = "we-accordion-togglePane";
    private List<TogglePane> togglePanes = new ArrayList<TogglePane>();
    private AccordionState state;

    public static final String getTogglePanesListElementWicketId() {
        return TOGGLE_PANES_LIST_ELEMENT_WICKET_ID;
    }

    public void addTogglePane(int index, final TogglePane togglePaneParam) {
        Reqs.PARAM_REQ.Object.requireNotNull(togglePaneParam, "Toggle pane must not be null.");
        Reqs.PRE_COND.Logic.requireFalse(togglePanes.contains(togglePaneParam), "Toggle pane must not already be contained in the accordion.");
        Reqs.PRE_COND.Logic.requireTrue(index < togglePanes.size(), "Index must be smaller than value returned by togglePanesQuantity()");
        togglePanes.add(index, togglePaneParam);
        togglePaneParam.addComponentToUpdateOnAjaxRequest(this);
    }

    public void addTogglePane(final TogglePane togglePaneParam) {
        Reqs.PARAM_REQ.Object.requireNotNull(togglePaneParam, "Toggle pane must not be null.");
        Reqs.PRE_COND.Logic.requireFalse(togglePanes.contains(togglePaneParam), "Toggle pane must not already be contained in the accordion.");
        togglePanes.add(togglePaneParam);
        togglePaneParam.addComponentToUpdateOnAjaxRequest(this);
    }

    public void removeTogglePane(final TogglePane togglePaneParam) {
        togglePanes.remove(togglePaneParam);
        togglePaneParam.removeComponentToUpdateOnAjaxRequest(this);
    }

    public int togglePanesQuantity() {
        return togglePanes.size();
    }

    public final AccordionState getAccordionState() {
        return state;
    }

    public final List<? extends TogglePane> getTogglePanes() {
        return Collections.unmodifiableList(togglePanes);
    }

    public Accordion(final String idParam) {
        super(idParam);
        state = new DefaultAccordionState(this);
        setOutputMarkupId(true);
        final ListView<TogglePane> lv = new ListView<TogglePane>(TOGGLE_PANES_LIST_WICKET_ID, togglePanes) {
            @Override
            protected void populateItem(final ListItem<TogglePane> item) {
                final TogglePane togglePane = item.getModelObject();
                item.add(togglePane);
            }
        };
        add(lv);
        add(AttributeAppenderFactory.newAttributeAppenderForClass("we-accordion"));
    }
}
