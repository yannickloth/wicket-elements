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
import org.apache.wicket.*;
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
    private RequestCycleFactory newRequestCycleFactory;
    private SessionFactory newSessionFactory;

    public SecureWebApplication(final IAuthorizationStrategy authorizationStrategyParam, final IUnauthorizedComponentInstantiationListener unauthorizedComponentInstantiationListenerParam, final SecurityCheck securityCheckParam, final RequestCycleFactory newRequestCycleFactoryParam, final SessionFactory newSessionFactoryParam) {
        authorizationStrategy = authorizationStrategyParam;
        unauthorizedComponentInstantiationListener = unauthorizedComponentInstantiationListenerParam;
        securityCheck = securityCheckParam;
        newRequestCycleFactory = newRequestCycleFactoryParam;
        newSessionFactory = newSessionFactoryParam;
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
        Session session;
        if (newSessionFactory != null) {
            session = newSessionFactory.newSession(request, response);
        } else {
            session = new SecureSession(request);
        }
        return session;
    }

    @Override
    public RequestCycle newRequestCycle(final Request request, final Response response) {
        RequestCycle rc = null;
        if (newRequestCycleFactory != null) {
            rc = newRequestCycleFactory.newRequestCycle(request, response);
        } else {
            rc = super.newRequestCycle(request, response);
        }
        return rc;
    }

    public SecurityCheck getSecurityCheck() {
        return securityCheck;
    }

    public void setSecurityCheck(final SecurityCheck securityCheckParam) {
        securityCheck = securityCheckParam;
    }

    public SessionFactory getNewSessionFactory() {
        return newSessionFactory;
    }

    public void setNewSessionFactory(final SessionFactory newSessionFactoryParam) {
        newSessionFactory = newSessionFactoryParam;
    }

    public RequestCycleFactory getNewRequestCycleFactory() {
        return newRequestCycleFactory;
    }

    public void setNewRequestCycleFactory(final RequestCycleFactory newRequestCycleFactoryParam) {
        newRequestCycleFactory = newRequestCycleFactoryParam;
    }

    public IUnauthorizedComponentInstantiationListener getUnauthorizedComponentInstantiationListener() {
        return unauthorizedComponentInstantiationListener;
    }

    public void setUnauthorizedComponentInstantiationListener(final IUnauthorizedComponentInstantiationListener unauthorizedComponentInstantiationListenerParam) {
        unauthorizedComponentInstantiationListener = unauthorizedComponentInstantiationListenerParam;
    }

    public IAuthorizationStrategy getAuthorizationStrategy() {
        return authorizationStrategy;
    }

    public void setAuthorizationStrategy(final IAuthorizationStrategy authorizationStrategyParam) {
        authorizationStrategy = authorizationStrategyParam;
    }
}
