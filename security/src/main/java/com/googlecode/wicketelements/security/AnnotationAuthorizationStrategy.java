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

import com.googlecode.wicketelements.security.annotations.EnableAction;
import com.googlecode.wicketelements.security.annotations.InstantiateAction;
import com.googlecode.wicketelements.security.annotations.RenderAction;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Set;

import static com.googlecode.jbp.common.requirements.Reqs.PARAM_REQ;

/**
 * @author Yannick LOTH
 */
public class AnnotationAuthorizationStrategy implements IAuthorizationStrategy {

    private transient static final Logger LOGGER = LoggerFactory.getLogger("wicketelements.security");
    private SecurityCheck securityCheck;

    public AnnotationAuthorizationStrategy(final SecurityCheck securityCheckParam) {
        PARAM_REQ.Object.requireNotNull(securityCheckParam, "SecurityCheck object must not be null.");
        securityCheck = securityCheckParam;
    }

    public <T extends Component> boolean isInstantiationAuthorized(final Class<T> componentClassParam) {
        LOGGER.debug("Checking if instantiation is authorized for {}", componentClassParam.getName());
        PARAM_REQ.Object.requireNotNull(componentClassParam, "The component's class parameter must not be null.");
        if (securityCheck.isSignInRequired()) {
            LOGGER.debug("Sign in is required.");
            if (!SecureSession.get().isAuthenticated()) {
                LOGGER.debug("User is not authenticated.");
                if (Page.class.isAssignableFrom(componentClassParam)) {
                    LOGGER.debug("Component is a Page.");
                    final Class<? extends Page> p = (Class<? extends Page>) componentClassParam;
                    if (securityCheck.isErrorPage(p) || securityCheck.isSignInPage(p) || securityCheck.isSignOutPage(p)) {
                        LOGGER.debug("Page is an error, sign in or sign out page.");
                        return true;
                    } else {
                        //do not allow other pages to be instantiated if not signed in
                        return false;
                    }
                } //if not a page, simply go on to see if the component has the instantiation rights
            }
        }
        if (securityCheck.isSecurityAnnotatedComponent(componentClassParam)) {
            final Set<String> permissions = securityCheck.findImpliedPermissions(componentClassParam, InstantiateAction.class);
            final boolean constraintsSatisfied = securityCheck.isAllConstraintsSatisfiedForInstantiation(componentClassParam, InstantiateAction.class);
            final boolean userHasPermission = securityCheck.isOnePermissionGivenToUser(permissions);
            return constraintsSatisfied && userHasPermission;
        }
        //no annotations so no permission check is required
        return true;
    }

    public boolean isActionAuthorized(final Component componentParam, final Action actionParam) {
        PARAM_REQ.Object.requireNotNull(componentParam, "The component parameter must not be null.");
        PARAM_REQ.Object.requireNotNull(actionParam, "The action parameter must not be null.");
        final Class<? extends Component> componentClass = componentParam.getClass();
        if (securityCheck.isSecurityAnnotatedComponent(componentClass)) {
            final IUser user = SecureSession.get().getUser();
            if (user != null) {
                Class<? extends Annotation> securityAnnotationClass = null;
                if (Component.RENDER.equals(actionParam)) {
                    securityAnnotationClass = RenderAction.class;
                } else if (Component.ENABLE.equals(actionParam)) {
                    securityAnnotationClass = EnableAction.class;
                    if (!isActionAuthorized(componentParam.getParent(), actionParam)) {
                        return false;
                    }//else go on, check if the user has the permission
                }
                if (securityAnnotationClass == null) {
                    throw new IllegalStateException("Action is unknown (Render or Enable expected).: " + actionParam);
                }
                final boolean constraintsSatisfied = securityCheck.isAllConstraintsSatisfiedForAction(componentParam, securityAnnotationClass);
                final Set<String> permissions = securityCheck.findImpliedPermissions(componentClass, securityAnnotationClass);
                final boolean userHasPermission = securityCheck.isOnePermissionGivenToUser(permissions);
                return constraintsSatisfied && userHasPermission;
            }
        } else {
            LOGGER.debug("No security annotation on the component.  Action is authorized.");
            return true;
        }
        return true;
    }
}
