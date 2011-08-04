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
package com.googlecode.wicketelements.components.layouts.box;

import com.googlecode.wicketelements.components.lists.ComponentListModel;
import com.googlecode.wicketelements.components.lists.ComponentListPanel;
import com.googlecode.wicketelements.library.behavior.AttributeModifierFactory;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.list.ListItem;

/**
 * Layout which either stacks its components on top of each other or places them in a row.
 */
public class BoxLayout extends ComponentListPanel {
    private BoxLayoutDirection direction = BoxLayoutDirection.LEFT_TO_RIGHT;

    public enum BoxLayoutDirection {
        LEFT_TO_RIGHT, TOP_TO_BOTTOM;
    }

    public BoxLayout(final String id, final ComponentListModel componentListModelParam) {
        super(id, componentListModelParam);
        add(CSSPackageResource.getHeaderContribution(BoxLayout.class, "../layout.css"));
    }

    public BoxLayout(final String id, final ComponentListModel componentListModelParam, final BoxLayoutDirection directionParam) {
        super(id, componentListModelParam);
        add(CSSPackageResource.getHeaderContribution(BoxLayout.class, "../layout.css"));
        setDirection(directionParam);
    }

    public void setDirection(final BoxLayoutDirection directionParam) {
        direction = directionParam == null ? BoxLayoutDirection.LEFT_TO_RIGHT : directionParam;
    }

    @Override
    protected void onItem(final ListItem<Component> itemParam) {
        super.onItem(itemParam);
        itemParam.getModelObject().setRenderBodyOnly(true);
        switch (direction) {
            case LEFT_TO_RIGHT:
                itemParam.add(AttributeModifierFactory.newAttributeModifierForClass("we-layout-floatLeft"));
                break;
            case TOP_TO_BOTTOM:
                itemParam.add(AttributeModifierFactory.newAttributeModifierForClass("we-layout-clearBoth"));
                break;
            default:
        }
    }
}
