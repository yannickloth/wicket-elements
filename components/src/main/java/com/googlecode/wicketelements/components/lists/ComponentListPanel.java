/*
 *  Copyright 2011 Yannick LOTH.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package com.googlecode.wicketelements.components.lists;

import com.googlecode.jbp.common.requirements.PreCondition;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Objects;

/**
 * This panel renders a list of components.  It's very useful when the quantity of components you want to render is set
 * dynamically, as you don't have to write a HTML tag placeholder with it's Wicket identifier for each component.
 *
 */
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
