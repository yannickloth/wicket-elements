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


import com.googlecode.wicketelements.security.IUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import static com.googlecode.jbp.common.requirements.Reqs.PARAM_REQ;

/**
 * @author Yannick LOTH
 */
public class ShiroUser implements IUser, Serializable {

    private transient static final Logger LOGGER = LoggerFactory.getLogger(ShiroUser.class);

    public ShiroUser() {
        super();
    }

    public boolean hasPermission(final String permission) {
        PARAM_REQ.String.requireNotBlank(permission, "The permission that is checked for the current user must not be null.");
        LOGGER.debug("Check for permission: {}", permission);
        final Subject currentUser = SecurityUtils.getSubject();
        LOGGER.debug("Current user: {}", currentUser.getPrincipal().toString());
        final boolean permitted = currentUser.isPermitted(new WildcardPermission(permission));
        LOGGER.debug("Permission {} granted: {}", permission, permitted);
        return permitted;
    }
}
