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

import com.googlecode.wicketelements.security.annotations.SignOut;
import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author Yannick LOTH
 */
@SignOut(page = SignOutPage.class)
public abstract class SecureWebApplication extends WebApplication {

    private IAuthorizationStrategy authorizationStrategy;
    private IUnauthorizedComponentInstantiationListener unauthorizedComponentInstantiationListener;
    private SecurityCheck securityCheck;

    public SecureWebApplication(final IAuthorizationStrategy authorizationStrategyParam, final IUnauthorizedComponentInstantiationListener unauthorizedInstListenerParam, final SecurityCheck securityCheckParam) {
        authorizationStrategy = authorizationStrategyParam;
        unauthorizedComponentInstantiationListener = unauthorizedInstListenerParam;
        securityCheck = securityCheckParam;
    }

    public SecureWebApplication() {
    }

    @Override
    protected void init() {
        super.init();
        if (authorizationStrategy != null) {
            getSecuritySettings().setAuthorizationStrategy(authorizationStrategy);
            getSecuritySettings().setUnauthorizedComponentInstantiationListener(unauthorizedComponentInstantiationListener);
        }
    }

    public static SecureWebApplication get() {
        return (SecureWebApplication) Application.get();
    }

    @Override
    public Session newSession(final Request request, final Response response) {
        return new SecureSession(request);
    }

    public SecurityCheck getSecurityCheck() {
        return securityCheck;
    }
}
