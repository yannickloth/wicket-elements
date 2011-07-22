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

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author Yannick LOTH
 */
public class SecureSession extends WebSession {

    private transient static final Logger LOGGER = LoggerFactory.getLogger(SecureSession.class);
    private IUser user = IUser.ALL_PERMISSIONS_USER;
    private List<SessionInvalidator> invalidators = Collections.emptyList();

    public SecureSession(final Request request) {
        super(request);
    }

    public static SecureSession get() {
        return (SecureSession) Session.get();
    }

    public List<SessionInvalidator> getInvalidators() {
        return invalidators;
    }

    public void setInvalidators(final List<SessionInvalidator> invalidatorsParam) {
        invalidators = invalidatorsParam;
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(final IUser userParam) {
        user = userParam;
    }

    /**
     * To check if the current user is authenticated.
     *
     * @return Returns <code>true</code> if the current user is authenticated,
     *         <code>false</code> else.
     */
    public boolean isAuthenticated() {
        return user != null && user != IUser.ALL_PERMISSIONS_USER;
    }

    @Override
    public void invalidate() {
        LOGGER.debug("Invalidating session with user {} and id {}.", user, Session.get().getId());
        user = null;
        runCustomInvalidators();
        super.invalidate();
    }

    @Override
    public void invalidateNow() {
        LOGGER.debug("Invalidating now session with user: {}", user);
        user = null;
        runCustomInvalidators();
        super.invalidateNow();
    }

    private void runCustomInvalidators() {
        for (final SessionInvalidator current : invalidators) {
            current.invalidate((WebSession) WebSession.get());
        }
    }
}
