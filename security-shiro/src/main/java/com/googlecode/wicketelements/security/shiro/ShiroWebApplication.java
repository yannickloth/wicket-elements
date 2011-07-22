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
package com.googlecode.wicketelements.security.shiro;

import com.googlecode.wicketelements.security.SecureWebApplication;
import com.googlecode.wicketelements.security.SessionInvalidator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.wicket.*;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom web application for use with Shiro.  It's method {@code newRequestCycle()} returns a {@code ShiroRequestCycle}
 * which automatically sets the Shiro security manager at the beginning of each request.
 *
 * @author Yannick LOTH
 */
public abstract class ShiroWebApplication extends SecureWebApplication {
    private org.apache.shiro.mgt.SecurityManager securityManager;

    public ShiroWebApplication() {
    }

    protected ShiroWebApplication(final IAuthorizationStrategy authorizationStrategyParam, final IUnauthorizedComponentInstantiationListener unauthorizedInstListenerParam, final SecurityManager securityManagerParam) {
        super(authorizationStrategyParam, unauthorizedInstListenerParam);
        securityManager = securityManagerParam;
    }

    @Override
    public RequestCycle newRequestCycle(final Request request, final Response response) {
        return new ShiroRequestCycle(WebApplication.get(), (WebRequest) request, (WebResponse) response);
    }

    public org.apache.shiro.mgt.SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(final SecurityManager securityManagerParam) {
        securityManager = securityManagerParam;
    }

    public static ShiroWebApplication get() {
        return (ShiroWebApplication) Application.get();
    }

    @Override
    public Session newSession(final Request request, final Response response) {
        final ShiroWebSession s = new ShiroWebSession(request);
        final List<SessionInvalidator> invalidators = new ArrayList<SessionInvalidator>();
        invalidators.add(new ShiroSessionInvalidator());
        s.setInvalidators(invalidators);
        return s;
    }
}
