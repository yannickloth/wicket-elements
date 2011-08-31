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

import com.googlecode.wicketelements.security.SecureSession;
import com.googlecode.wicketelements.security.SessionFactory;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;

/**
 * SessionFactory that builds new sessions for applications that rely on Apache Shiro for security.  Basically, the
 * returned sessions will all logout the subject from the Shiro session when they are invalidated.
 *
 * @author Yannick LOTH
 */
public class ShiroSessionFactory implements SessionFactory {
    public Session newSession(final Request request, final Response response) {
        final SecureSession s = new SecureSession(request);
        s.addInvalidator(new ShiroSessionInvalidator());
        return s;
    }
}
