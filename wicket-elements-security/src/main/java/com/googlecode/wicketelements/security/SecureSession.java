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

import com.googlecode.jbp.common.requirements.Reqs;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.googlecode.jbp.common.requirements.Reqs.PARAM_REQ;

/**
 * @author Yannick LOTH
 */
public class SecureSession extends WebSession {

    private transient static final Logger LOGGER = LoggerFactory.getLogger(SecureSession.class);
    private IUser user = IUser.NO_PERMISSIONS_USER;
    private List<SessionInvalidator> invalidators = Collections.emptyList();

    public void addInvalidator(final SessionInvalidator invalidatorParam) {
        Reqs.PARAM_REQ.Object.requireNotNull(invalidatorParam, "Impossible to add a invalidator which is null.");
        if (invalidators.equals(Collections.emptyList())) {
            invalidators = new ArrayList<SessionInvalidator>();
        }
        invalidators.add(invalidatorParam);
    }

    public void removeInvalidator(final SessionInvalidator invalidatorParam) {
        invalidators.remove(invalidatorParam);
    }

    public SecureSession(final Request request) {
        super(request);
    }

    public static SecureSession get() {
        return (SecureSession) Session.get();
    }

    public IUser getUser() {
        return user;
    }

    public void switchUser(final IUser userParam) {
        PARAM_REQ.Object.requireNotNull(userParam, "The user parameter must not be null.");
        user = userParam;
    }

    /**
     * To check if the current user is authenticated.
     *
     * @return Returns <code>true</code> if the current user is authenticated,
     *         <code>false</code> else.
     */
    public boolean isAuthenticated() {
        return user != null && user != IUser.NO_PERMISSIONS_USER;
    }

    @Override
    public void invalidate() {
        LOGGER.debug("Invalidating session with user {} and id {}.", user, Session.get().getId());
        commonInvalidate();
        super.invalidate();
    }

    @Override
    public void invalidateNow() {
        LOGGER.debug("Invalidating now session with user: {}", user);
        commonInvalidate();
        super.invalidateNow();
    }

    private void commonInvalidate() {
        user = IUser.NO_PERMISSIONS_USER;
        runCustomInvalidators();
    }

    private void runCustomInvalidators() {
        for (final SessionInvalidator current : invalidators) {
            current.invalidate(WebSession.get());
        }
    }
}
