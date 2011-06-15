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
import org.apache.wicket.model.IModel;

/**
 * Model which contains a <code>Class&lt;Page&gt;</code>.  Used in navigation menus.
 * @author Yannick LOTH
 */
public class PageModel implements IModel<Class<Page>> {

    private static final long serialVersionUID = 1L;
    private Class<Page> pageClass;

    public PageModel(final Class<Page> pageClassParam) {
        pageClass = pageClassParam;
    }

    public Class<Page> getObject() {
        return pageClass;
    }

    public void setObject(final Class<Page> pageClassParam) {
        pageClass = pageClassParam;
    }

    public void detach() {
    }
}
