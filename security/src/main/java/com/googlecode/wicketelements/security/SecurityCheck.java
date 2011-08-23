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
import org.apache.wicket.Page;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface SecurityCheck {

    <T extends Component> List<Class<? extends SecurityConstraint>> findSecurityConstraintsForEnable(final T componentParam);

    <T extends Component> List<Class<? extends SecurityConstraint>> findSecurityConstraintsForRender(final T componentParam);

    <T extends Component> List<Class<? extends InstantiationSecurityConstraint>> findSecurityConstraintsForInstantiation(final Class<T> componentClassParam);

    <T extends Component, A extends Annotation> Set<String> findImpliedPermissions(final Class<T> componentClassParam, final Class<A> actionAnnotationClass);

    <T extends Component> boolean isAllSecurityConstraintsSatisfiedForInstantiation(final Class<T> componentClassParam, final List<Class<? extends InstantiationSecurityConstraint>> securityConstraintsParam);

    <T extends Component> boolean isAllSecurityConstraintsSatisfiedForAction(final T componentParam, List<Class<? extends SecurityConstraint>> constraintClassesParam);

    boolean isApplicationWithSignInPageSpecified();

    boolean isAtLeastOnePermissionGivenToUser(final Collection<String> permissionsParam);

    boolean isSecurityAnnotatedComponent(final Class<? extends Component> componentClassParam);

    Class<? extends Page> signInPage();

    Class<? extends Page> signOutPage();

    boolean isSignInRequired();

    boolean isErrorPage(final Class<? extends Page> pageClassParam);

    boolean isSignInPage(final Class<? extends Page> pageClassParam);

    boolean isSignOutPage(final Class<? extends Page> pageClassParam);
}
