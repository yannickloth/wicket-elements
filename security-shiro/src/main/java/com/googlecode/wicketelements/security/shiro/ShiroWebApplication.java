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
import org.apache.shiro.mgt.SecurityManager;
import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;

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

    public ShiroWebApplication(final IAuthorizationStrategy authorizationStrategyParam, final org.apache.shiro.mgt.SecurityManager securityManagerParam) {
        super(authorizationStrategyParam);
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
}
