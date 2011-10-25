package com.googlecode.wicketelements.components.togglepane.ui;

import com.googlecode.jbp.common.requirements.Reqs;
import com.googlecode.wicketelements.components.togglepane.DefaultTogglePaneState;
import com.googlecode.wicketelements.components.togglepane.TogglePaneState;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.HashSet;
import java.util.Set;

public abstract class TogglePane extends Panel {
    private static final String TITLE_WICKET_ID = "title";
    private static final String CONTENT_WICKET_ID = "content";
    private TogglePaneState state;
    private Component titleComponent;
    private Component contentComponent;
    private Set<Component> componentsToUpdateOnAjaxRequest = new HashSet<Component>();

    public final void addComponentToUpdateOnAjaxRequest(final Component componentParam) {
        Reqs.PARAM_REQ.Object.requireNotNull(componentParam, "Component to update on ajax request must not be null.");
        componentsToUpdateOnAjaxRequest.add(componentParam);
    }

    public final void removeComponentToUpdateOnAjaxRequest(final Component componentParam) {
        if (componentParam != null) {
            componentsToUpdateOnAjaxRequest.remove(componentParam);
        }
    }

    public final Component getTitleComponent() {
        return titleComponent;
    }

    public final Component getContentComponent() {
        return contentComponent;
    }

    public final TogglePaneState getTogglePaneState() {
        return state;
    }

    public abstract Component createTitleComponent(final String titleWicketIdParam);

    public abstract Component createContentComponent(final String contentWicketIdParam);

    /**
     * Updates all components that need to be updated in case of an AJAX request.
     *
     * @param targetParam
     */
    public final void handleAjaxOnClickEvent(final AjaxRequestTarget targetParam) {
        //update state of togglePane
        handleOnClickEvent();
        //update components that must be updated because of the event
        updateComponentsForAjaxRequest(targetParam);
    }

    /**
     * Similar to {@code handleAjaxOnClickEvent(AjaxRequestTarget t)} but should be used when the request is not an AJAX request.
     */
    public final void handleOnClickEvent() {
        //update state of togglePane
        getTogglePaneState().toggleContent();
    }

    public final void updateComponentsForAjaxRequest(final AjaxRequestTarget targetParam) {
        for (final Component current : componentsToUpdateOnAjaxRequest) {
            targetParam.addComponent(current);
        }
        targetParam.addComponent(this);
    }

    public TogglePane(final String idParam) {
        super(idParam);
        titleComponent = createTitleComponent(TITLE_WICKET_ID);
        contentComponent = createContentComponent(CONTENT_WICKET_ID);
        add(titleComponent);
        add(contentComponent);
        contentComponent.setVisible(false);
        setOutputMarkupId(true);
        state = new DefaultTogglePaneState(this);
    }
}
