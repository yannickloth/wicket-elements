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

import com.googlecode.jbp.common.requirements.Reqs;
import com.googlecode.wicketelements.security.RequestCycleFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;

/**
 * This request cycle factory returns a ShiroRequestCycle.
 *
 * @author Yannick LOTH
 */
public class ShiroRequestCycleFactory implements RequestCycleFactory {
    private final SecurityManager securityManager;

    public ShiroRequestCycleFactory(final org.apache.shiro.mgt.SecurityManager securityManagerParam) {
        Reqs.PARAM_REQ.Object.requireNotNull(securityManagerParam, "Shiro request cycle cannot be used without a non null security manager.");
        securityManager = securityManagerParam;
    }

    public RequestCycle newRequestCycle(final Request request, final Response response) {
        return new ShiroRequestCycle(WebApplication.get(), (WebRequest) request, (WebResponse) response, securityManager);
    }
}
