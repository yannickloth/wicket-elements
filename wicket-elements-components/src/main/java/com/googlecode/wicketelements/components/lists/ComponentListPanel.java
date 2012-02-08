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

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Objects;

import static com.googlecode.jbp.common.requirements.Reqs.PRE_COND;

/**
 * This panel renders a list of components.  It's very useful when the quantity of components you want to render is set
 * dynamically, as you don't have to write a HTML tag placeholder with it's Wicket identifier for each component.
 */
public class ComponentListPanel extends Panel {
    public static final String LIST_ELEMENT_WICKET_ID = "component";

    public ComponentListPanel(final String id, final ComponentListModel componentListModelParam) {
        super(id, componentListModelParam);
        init(componentListModelParam);
    }

    private void init(final ComponentListModel componentListModelParam) {
        final ListView<Component> listView = new ListView<Component>("componentList", componentListModelParam) {
            @Override
            protected void populateItem(final ListItem<Component> itemParam) {
                final Component component = itemParam.getModelObject();
                PRE_COND.String.requireNotBlank(component.getId(), "The ComponentList elements must have an id which is not blank.");
                PRE_COND.Logic.requireTrue(Objects.equal(LIST_ELEMENT_WICKET_ID, component.getId()), "The ComponentList elements must have the id: " + LIST_ELEMENT_WICKET_ID);
                itemParam.add(component);
                onItem(itemParam);
                if (isFirstItem(itemParam)) {
                    onFirstItem(itemParam);
                }
                if (isLastItem(itemParam)) {
                    onLastItem(itemParam);
                }
            }
        };
        add(listView);
    }

    private boolean isFirstItem(final ListItem<Component> itemParam) {
        return true;
    }

    private boolean isLastItem(final ListItem<Component> itemParam) {
        return true;
    }

    protected void onItem(final ListItem<Component> itemParam) {
    }

    protected void onFirstItem(final ListItem<Component> itemParam) {
    }

    protected void onLastItem(final ListItem<Component> itemParam) {
    }
}
