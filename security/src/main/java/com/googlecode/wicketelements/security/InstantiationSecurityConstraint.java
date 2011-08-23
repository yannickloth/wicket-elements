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
package com.googlecode.wicketelements.security;

import org.apache.wicket.Component;

/**
 * Defines a method that permits to check if a security constraint is satisfied before executing an action
 * (instantiation, rendering or enabling) on a Wicket component.
 * Implementations must have a default constructor.  This class is used in security annotations to declare constraints
 * applied to actions on components.  The security mechanisms instantiate this class automatically using the reflective
 * method: {@code Class.newInstance()}.
 *
 * @author Yannick LOTH
 */
public interface InstantiationSecurityConstraint {
    /**
     * Checks if a security constraint is satisfied before instantiating a Wicket component.
     *
     * @param componentClassParam The component on which the action is executed.
     * @param <T>                 The component's class.
     * @return {@code true} if the constraint is satisfied, {@code false} else.
     */
    public <T extends Component> boolean isSatisfiedConstraint(final Class<T> componentClassParam);
}
