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

import com.googlecode.wicketelements.security.SessionInvalidator;
import org.apache.shiro.SecurityUtils;
import org.apache.wicket.protocol.http.WebSession;

/**
 * Logs out the current Shiro subject.
 */
public class ShiroSessionInvalidator implements SessionInvalidator {

    public void invalidate(final WebSession sessionParam) {
        SecurityUtils.getSubject().logout();
    }
}
