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

import com.googlecode.wicketelements.security.annotations.InstantiateAction;
import org.apache.wicket.markup.html.form.TextField;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class AnnotationSecurityCheckTest {
    private SecurityCheck securityCheck = new AnnotationSecurityCheck();

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindImpliedPermissionsWithNullComponentParam() {
        securityCheck.findImpliedPermissions(null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindImpliedPermissionsWithNullAnnotationParam() {
        securityCheck.findImpliedPermissions(TextField.class, null);
    }

    @Test
    public void testFindImpliedPermissionsWithInstantiationAnnotation() {
        final Set<String> permissions = securityCheck.findImpliedPermissions(InstantiationActionAnnotatedTextField.class, InstantiateAction.class);
        Assert.assertEquals(permissions.size(), 1, "There should be one permission!");
    }

    @Test
    public void testFindImpliedPermissionsWithRenderAnnotation() {
        final Set<String> permissions = securityCheck.findImpliedPermissions(RenderActionAnnotatedTextField.class, InstantiateAction.class);
        Assert.assertEquals(permissions.size(), 1, "There should be one permission!");
    }
}
