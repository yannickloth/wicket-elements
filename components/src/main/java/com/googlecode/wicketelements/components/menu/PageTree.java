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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.googlecode.jbp.common.requirements.Reqs.PARAM_REQ;

/**
 * Class that represents a Wicket page tree (hierarchy).  Used to show
 * navigation and breadcrumb menus.
 *
 * @author Yannick LOTH
 */
public class PageTree {

    private Class<? extends Page> pageClass;
    private List<PageTree> children;
    private PageTree parent;
    private Map<Class<? extends Page>, PageTree> pageMap = new HashMap<Class<? extends Page>, PageTree>();

    public PageTree() {
    }

    public PageTree(final Class<? extends Page> pageClassParam) {
        PARAM_REQ.Object.requireNotNull(pageClassParam, "Page class parameter must not be null.");
        pageClass = pageClassParam;
        addToMap(pageMap);
    }

    public PageTree(final Class<? extends Page> pageClassParam, final List<PageTree> childrenParam) {
        PARAM_REQ.Object.requireNotNull(pageClassParam, "Page class parameter must not be null.");
        PARAM_REQ.Object.requireNotNull(childrenParam, "Childre trees list must not be null.");
        pageClass = pageClassParam;
        children = childrenParam;
        if (childrenParam != null) {
            for (final PageTree child : childrenParam) {
                child.parent = this;
            }
        }
        addToMap(pageMap);
    }

    public PageTree(final List<PageTree> childrenParam) {
        PARAM_REQ.Object.requireNotNull(childrenParam, "Children trees list must not be null.");
        children = childrenParam;
        addToMap(pageMap);
    }

    protected final void addToMap(final Map<Class<? extends Page>, PageTree> mapParam) {
        if (pageClass != null) {
            mapParam.put(pageClass, this);
        }
        if (children != null) {
            for (final PageTree child : children) {
                child.addToMap(mapParam);
            }
        }
    }

    public PageTree getPageTree(final Class<? extends Page> pageClass) {
        PARAM_REQ.Object.requireNotNull(pageClass, "Page class parameter must not be null.");
        return pageMap.get(pageClass);
    }

    public Class<? extends Page> getPageClass() {
        return pageClass;
    }

    public void setPageClass(final Class<? extends Page> pageClassParam) {
        pageClass = pageClassParam;
    }

    public PageTree getParent() {
        return parent;
    }

    public List<PageTree> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children == null;
    }

    public boolean isRoot() {
        return parent == null;
    }
}
