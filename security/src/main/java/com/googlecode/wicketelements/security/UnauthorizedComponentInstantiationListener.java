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
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.protocol.http.WebApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.googlecode.jbp.common.requirements.Reqs.PARAM_REQ;


public class UnauthorizedComponentInstantiationListener implements IUnauthorizedComponentInstantiationListener {
    private transient static final Logger LOGGER = LoggerFactory.getLogger("wicketelements.security");
    private final SecurityCheck securityCheck;

    public UnauthorizedComponentInstantiationListener(final SecurityCheck securityCheckParam) {
        PARAM_REQ.Object.requireNotNull(securityCheckParam, "The SecurityCheck object must not be null.");
        securityCheck = securityCheckParam;
    }

    public void onUnauthorizedInstantiation(final Component componentParam) {
        PARAM_REQ.Object.requireNotNull(componentParam, "The component parameter must not be null.");
        if (!SecureSession.get().isAuthenticated()) {
            LOGGER.debug("Unauthorized and user not authenticated.");
            if (securityCheck.isApplicationWithSignInPageSpecified()) {
                LOGGER.debug("Application has a sign in page specified. Setting sign in page as response.");
                throw new RestartResponseAtInterceptPageException(securityCheck.signInPage());
            }
        }
        LOGGER.debug("Setting access denied page as response.");
        throw new RestartResponseException(WebApplication.get().getApplicationSettings().getAccessDeniedPage());
    }
}
