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

/**
 *
 * @author Yannick LOTH
 */
public interface IUser {

    public static final IUser ALL_PERMISSIONS_USER = new IUser() {

        public boolean hasPermission(String permission) {
            return true;
        }
    };

    /**
     * To check if the current user has the specified permission.
     * @param permission The permission to check for.
     * @return Returns <code>true</code> if the current user has the specified
     * permission, <code>false</code> else.
     */
    boolean hasPermission(final String permission);
}
