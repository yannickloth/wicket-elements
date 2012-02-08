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

import org.apache.wicket.Application;
import org.apache.wicket.model.IModel;

/**
 * Abstract model that contains a list of pages.
 *
 * @author Yannick LOTH
 */
public class PageTreeModel implements IModel<PageTree> {

    private static final long serialVersionUID = 1L;
    private PageTree pageTree;

    public PageTreeModel() {
    }

    public PageTreeModel(final PageTree pageTreeParam) {
        pageTree = pageTreeParam;
    }

    public PageTree getObject() {
        if (pageTree == null) {
            return new PageTree(Application.get().getHomePage());
        } else {
            return pageTree;
        }
    }

    public void setObject(final PageTree pageTreeParam) {
        pageTree = pageTreeParam;
    }

    public void detach() {
    }
}
