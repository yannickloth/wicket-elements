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

import com.googlecode.wicketelements.common.parameter.ParamValidator;
import com.googlecode.wicketelements.security.IPermissionBuilder;

/**
 * Simple permission builder that builds permission by appending the permission
 * detail suffix to the permission base.
 * @author Yannick LOTH
 */
public class SimplePermissionBuilder implements IPermissionBuilder {

    public static final String RENDER = "render";
    public static final String ENABLE = "enable";
    public static final String INSTANTIATE = "instantiate";
    public static final String COLON = ":";

    public String buildInstatiationPermissionString(final String permBase) {
        ParamValidator.notBlank(permBase);
        return permBase.trim().concat(COLON).concat(INSTANTIATE);
    }

    public String buildRenderPermissionString(final String permBase) {
        ParamValidator.notBlank(permBase);
        return permBase.trim().concat(COLON).concat(RENDER);
    }

    public String buildEnablePermissionString(final String permBase) {
        ParamValidator.notBlank(permBase);
        return permBase.trim().concat(COLON).concat(ENABLE);
    }
}
