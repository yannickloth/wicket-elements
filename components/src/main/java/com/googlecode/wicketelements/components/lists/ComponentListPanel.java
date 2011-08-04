package com.googlecode.wicketelements.components.lists;

import com.googlecode.jbp.common.requirements.ParamRequirements;
import com.googlecode.jbp.common.requirements.PreCondition;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Objects;

public class ComponentListPanel extends Panel {
    public static final String LIST_ELEMENT_WICKET_ID = "component";

    public ComponentListPanel(final String id, final ComponentListModel componentListModelParam) {
        super(id, componentListModelParam);
        init(componentListModelParam);
    }

    private void init(final ComponentListModel componentListModelParam) {
        add(new ListView("componentList", componentListModelParam) {
            @Override
            protected void populateItem(final ListItem item) {
                final Component component = (Component) item.getModelObject();
                PreCondition.INSTANCE.requireNotBlank(component.getId(), "The ComponentList elements must have an id which is not blank.");
                PreCondition.INSTANCE.requireTrue(Objects.equal(LIST_ELEMENT_WICKET_ID, component.getId()), "The ComponentList elements must have the id: " + LIST_ELEMENT_WICKET_ID);
                item.add(component);
            }
        });
    }
}
