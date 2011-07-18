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

import org.apache.shiro.SecurityUtils;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;

/**
 * Custom request cycle that web applications secured with Shiro should use to automatically set the security manager at
 * the beginning of each request processing.
 *
 * @author Yannick LOTH
 */
public class ShiroRequestCycle extends WebRequestCycle {
    public ShiroRequestCycle(final WebApplication application, final WebRequest request, final Response response) {
        super(application, request, response);
    }

    @Override
    protected void onBeginRequest() {
        super.onBeginRequest();
        SecurityUtils.setSecurityManager(ShiroWebApplication.get().getSecurityManager());
    }
}
