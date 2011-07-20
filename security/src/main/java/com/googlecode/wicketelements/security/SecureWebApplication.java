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

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * 
 * @author Yannick LOTH
 */
public abstract class SecureWebApplication extends WebApplication {

    private Class<? extends Page> signOutPage;
    private boolean loginRequired = false;
    private IAuthenticationStrategy authenticationStrategy = IAuthenticationStrategy.ALLOW_ALL;
    private IAuthorizationStrategy authorizationStrategy;

    public SecureWebApplication(final IAuthorizationStrategy authorizationStrategyParam) {
        authorizationStrategy = authorizationStrategyParam;
    }

    public SecureWebApplication() {
    }

    @Override
    protected void init() {
        super.init();
        signOutPage = SignOutPage.class;
        if (authorizationStrategy != null) {
            getSecuritySettings().setAuthorizationStrategy(authorizationStrategy);
        }
    }

    public static SecureWebApplication get() {
        return (SecureWebApplication) Application.get();
    }

    public abstract Class<? extends Page> getSignInPage();

    public Class<? extends Page> getSignOutPage() {
        return signOutPage;
    }

    public void setSignOutPage(final Class<? extends Page> signOutPage) {
        this.signOutPage = signOutPage;
    }

    @Override
    public Session newSession(final Request request, final Response response) {
        return new SecureSession(request);
    }

    public boolean isLoginRequired() {
        return loginRequired;
    }

    public void setLoginRequired(final boolean loginRequiredParam) {
        loginRequired = loginRequiredParam;
    }

    public IAuthenticationStrategy getAuthenticationStrategy() {
        return authenticationStrategy;
    }

    public void setAuthenticationStrategy(final IAuthenticationStrategy authenticationStrategyParam) {
        authenticationStrategy = authenticationStrategyParam;
    }
}