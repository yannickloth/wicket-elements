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

import com.googlecode.jbp.common.requirements.ParamRequirements;
import com.googlecode.wicketelements.common.annotation.AnnotationHelper;
import com.googlecode.wicketelements.security.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.*;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IApplicationSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yannick LOTH
 */
public class AnnotationAuthorizationStrategy implements IAuthorizationStrategy, IUnauthorizedComponentInstantiationListener {

    private transient static final Logger LOGGER = LoggerFactory.getLogger(AnnotationAuthorizationStrategy.class);

    public AnnotationAuthorizationStrategy() {
    }

    private boolean isNotSignInOrSignOutPage(final Class<? extends Page> pageClassParam) {
        return !pageClassParam.equals(signInPage())
                && !pageClassParam.equals(signOutPage());
    }

    private boolean isErrorPage(final Class<? extends Page> pageClassParam) {
        final IApplicationSettings settings = Application.get().getApplicationSettings();
        return pageClassParam.isAssignableFrom(settings.getAccessDeniedPage())
                || pageClassParam.isAssignableFrom(settings.getInternalErrorPage())
                || pageClassParam.isAssignableFrom(settings.getPageExpiredErrorPage());
    }

    private boolean isSignInRequired() {
        return AnnotationHelper.hasAnnotation(Application.get().getClass(), SignInRequired.class);
    }

    public Class<? extends Page> signInPage() {
        Class<? extends Page> page = Application.get().getHomePage();
        if (AnnotationHelper.hasAnnotation(Application.get().getClass(), SignIn.class)) {
            final SignIn annot = AnnotationHelper.getAnnotation(Application.get().getClass(), SignIn.class);
            page = annot.page();
        }
        return page;
    }

    public Class<? extends Page> signOutPage() {
        Class<? extends Page> page = null;
        if (AnnotationHelper.hasAnnotation(Application.get().getClass(), SignOut.class)) {
            final SignOut annot = AnnotationHelper.getAnnotation(Application.get().getClass(), SignOut.class);
            page = annot.page();
        }
        return page;
    }

    public <T extends Component> boolean isInstantiationAuthorized(final Class<T> componentClassParam) {
        LOGGER.debug("Checking if instantiation is authorized for {}", componentClassParam.getName());
        ParamRequirements.INSTANCE.requireNotNull(componentClassParam);
        if (isSignInRequired()) {
            LOGGER.debug("Sign in is required.");
            if (!SecureSession.get().isAuthenticated()) {
                LOGGER.debug("User is not authenticated.");
                if (Page.class.isAssignableFrom(componentClassParam)) {
                    LOGGER.debug("Component is a Page.");
                    final Class<? extends Page> p = (Class<? extends Page>) componentClassParam;
                    if (!isErrorPage(p) && isNotSignInOrSignOutPage(p)) {
                        LOGGER.debug("Not an error, sign in or sign out page.");
                        return false;
                    }
                }
            }
        }
        if (AnnotationHelper.hasAnnotation(componentClassParam, InstantiateAction.class)) { //permission check required
            final InstantiateAction action = AnnotationHelper.getAnnotation(componentClassParam, InstantiateAction.class);
            final String permission = action.permission();
            if (StringUtils.isBlank(permission)) {
                return true;
            }
            LOGGER.debug("Instantiation permission: {}", permission);
            if (SecureSession.get().isAuthenticated()) {
                LOGGER.debug("User authenticated");
                final IUser user = SecureSession.get().getUser();
                return (user != null && user.hasPermission(permission));
            } else {
                return false;
            }
        }
        //no annotation so no permission check is required
        return true;
    }

    public boolean isActionAuthorized(final Component componentParam, final Action actionParam) {
        ParamRequirements.INSTANCE.requireNotNull(componentParam);
        ParamRequirements.INSTANCE.requireNotNull(actionParam);
        final Class<? extends Component> compClass = componentParam.getClass();
        final IUser user = SecureSession.get().getUser();
        if (user != null) {
            if (Component.RENDER.equals(actionParam)) {
                LOGGER.debug("Action=RENDER");
                if (AnnotationHelper.hasAnnotation(componentParam.getClass(), RenderAction.class)) {
                    final RenderAction action = AnnotationHelper.getAnnotation(componentParam.getClass(), RenderAction.class);
                    final String permission = action.permission();
                    LOGGER.debug("RENDER permission: {}", permission);
                    return user.hasPermission(permission);
                }
            } else if (Component.ENABLE.equals(actionParam)) {
                LOGGER.debug("Action=ENABLE");
                if (AnnotationHelper.hasAnnotation(componentParam.getClass(), EnableAction.class)) {
                    final EnableAction action = AnnotationHelper.getAnnotation(componentParam.getClass(), EnableAction.class);
                    final String permission = action.permission();
                    LOGGER.debug("ENABLE permission: {}", permission);
                    //if parent is disabled, the component is also disabled if rendered
                    return (isActionAuthorized(componentParam.getParent(), actionParam) && user.hasPermission(permission));
                }
            }
        } else {
            LOGGER.debug("PermissionBase annotation not present, thus permission granted on action.");
            return true;
        }
        return true;
    }

    public void onUnauthorizedInstantiation(final Component componentParam) {
        ParamRequirements.INSTANCE.requireNotNull(componentParam);
        if (!SecureSession.get().isAuthenticated()) {
            LOGGER.debug("Unauthorized and user not authenticated.");
            if (signInPage() != Application.get().getHomePage()) {
                LOGGER.debug("Setting sign in page as response.");
                throw new RestartResponseAtInterceptPageException(signInPage());
            }
        }
        LOGGER.debug("Setting access denied page as response.");
        throw new RestartResponseException(WebApplication.get().getApplicationSettings().getAccessDeniedPage());

    }
}
