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
package com.googlecode.wicketelements.components.menu;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Yannick LOTH
 */
public class MenuPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public MenuPanel(final String id, final PageTreeModel pages) {
        super(id);
        init(pages);
    }

    private void init(final PageTreeModel pages) {
        populateRepeatingView(pages);
    }

    private void populateRepeatingView(final PageTreeModel pages) {

        final ListView<Class<Page>> lv = new ListView<Class<Page>>("breadcrumbs") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Class<Page>> item) {
                final Class<Page> pageClass = item.getModelObject();
                {
                    final Link<Class<Page>> link =
                            new BookmarkablePageLink<Class<Page>>("breadcrumbLink", pageClass) {

                                private static final long serialVersionUID = 1L;

                                @Override
                                protected void onBeforeRender() {
                                    super.onBeforeRender();
                                    if (pageClass.equals(getPage().getClass())) {
                                        onPageLink(this);
                                        onCurrentPageLink(this);
                                    } else {
                                        onPageLink(this);
                                    }
                                }
                            };
                    {
                        final Label languageLabel = new Label("breadcrumbLabel", "hard label" /*TODO get localized label from somewhere...*/);
                        languageLabel.setRenderBodyOnly(true);
                        link.add(languageLabel);
                    }
                    item.add(link);
                }
                item.setOutputMarkupId(true);
            }
        };
        lv.setRenderBodyOnly(true);
        add(lv);
    }

    /**
     * This method is executed when the link to the current locale is added to
     * the page.  It may be useful, for example, to disable the link, as the
     * locale is already selected, or to add some attribute to the tag.
     * @param pageLink The link for the current page.
     */
    protected void onCurrentPageLink(final Link<Class<Page>> pageLink) {
    }

    /**
     * This method is executed when the link to a locale is added to
     * the page.
     * @param pageLink The link for the specific page.
     */
    protected void onPageLink(final Link<Class<Page>> pageLink) {
    }
}
