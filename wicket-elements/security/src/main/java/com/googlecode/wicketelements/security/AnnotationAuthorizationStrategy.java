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

import com.googlecode.wicketelements.common.annotation.AnnotationHelper;
import com.googlecode.wicketelements.common.parameter.ParamValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Yannick LOTH
 */
public class AnnotationAuthorizationStrategy implements IAuthorizationStrategy, IUnauthorizedComponentInstantiationListener {

    private transient static final Logger LOGGER = LoggerFactory.getLogger(AnnotationAuthorizationStrategy.class);
    private final IPermissionBuilder permissionBuilder;

    public AnnotationAuthorizationStrategy(final IPermissionBuilder permissionBuilderParam) {
        ParamValidator.notNull(permissionBuilderParam);
        permissionBuilder = permissionBuilderParam;
    }

    public <T extends Component> boolean isInstantiationAuthorized(final Class<T> componentClassParam) {
        ParamValidator.notNull(componentClassParam);
        if (AnnotationHelper.hasAnnotation(componentClassParam, PermissionBase.class)) { //permission check required
            final PermissionBase annot = componentClassParam.getAnnotation(PermissionBase.class);
            LOGGER.debug("PermissionBase annotation present");
            final String permissionBase = annot.value();
            LOGGER.debug("PermissionBase annotation value: {}", permissionBase);
            final String permission = permissionBuilder.buildInstatiationPermissionString(permissionBase);
            LOGGER.debug("Instantiation permission: {}", permission);
            if (SecureSession.get().isAuthenticated()) {
                LOGGER.debug("User authenticated");
                final IUser user = SecureSession.get().getUser();
                return (user != null && user.hasPermission(permission));
            }
            LOGGER.debug("User not authenticated, set sign in page as response");
            throw new RestartResponseAtInterceptPageException(SecureWebApplication.get().getSignInPage());
        }
        LOGGER.debug("PermissionBase annotation not present, thus permission granted on instantiation.");
        //no annotation so no permission check is required
        return true;
    }

    public boolean isActionAuthorized(final Component componentParam, final Action actionParam) {
        ParamValidator.notNull(componentParam);
        ParamValidator.notNull(actionParam);
        final Class<? extends Component> compClass = componentParam.getClass();
        if (AnnotationHelper.hasAnnotation(compClass, PermissionBase.class)) { //permission check required
            final PermissionBase annot = AnnotationHelper.getAnnotation(compClass, PermissionBase.class);
            LOGGER.debug("PermissionBase annotation present.");
            final String permissionBase = annot.value();
            LOGGER.debug("PermissionBase annotation value: {}", permissionBase);
            if (StringUtils.isBlank(permissionBase)) {
                return false;
            }
            final IUser user = SecureSession.get().getUser();
            if (Component.RENDER.equals(actionParam)) {
                LOGGER.debug("Action=RENDER");
                final String permission = permissionBuilder.buildRenderPermissionString(permissionBase);
                LOGGER.debug("RENDER permission: {}", permission);
                return (user != null && user.hasPermission(permission));
            } else if (Component.ENABLE.equals(actionParam)) {
                LOGGER.debug("Action=ENABLE");
                final String permission = permissionBuilder.buildEnablePermissionString(permissionBase);
                LOGGER.debug("ENABLE permission: {}", permission);
                //if parent is disabled, the component is also disabled if rendered
                return (isActionAuthorized(componentParam.getParent(), actionParam) && user != null && user.hasPermission(permission));
            }
        }
        LOGGER.debug("PermissionBase annotation not present, thus permission granted on action.");
        return true;
    }

    public void onUnauthorizedInstantiation(final Component componentParam) {
        LOGGER.debug("Unauthorized instantiation, setting sign in page as response.");
        throw new RestartResponseAtInterceptPageException(SecureWebApplication.get().getSignInPage());
    }
}
