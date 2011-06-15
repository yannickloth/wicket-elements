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

import com.googlecode.wicketelements.common.parameter.ParamValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.Application;
import org.apache.wicket.Page;

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
        pageClass = pageClassParam;
        addToMap(pageMap);
    }

    public PageTree(final Class<? extends Page> pageClassParam, final List<PageTree> childrenParam) {
        ParamValidator.notNull(pageClassParam);
        ParamValidator.notNull(childrenParam);
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
        ParamValidator.notNull(childrenParam);
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
