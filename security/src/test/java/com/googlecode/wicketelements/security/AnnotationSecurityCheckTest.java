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

import com.googlecode.wicketelements.security.annotations.EnableAction;
import com.googlecode.wicketelements.security.annotations.InstantiateAction;
import com.googlecode.wicketelements.security.annotations.RenderAction;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.WicketTester;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class AnnotationSecurityCheckTest {
    private SecurityCheck securityCheck = new AnnotationSecurityCheck();
    private WicketTester tester;

    @BeforeClass
    private void setUp() {
        tester = new WicketTester();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindImpliedPermissionsWithNullComponentParam() {
        securityCheck.findImpliedPermissions(null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindImpliedPermissionsWithNullAnnotationParam() {
        securityCheck.findImpliedPermissions(TextField.class, null);
    }

    @Test
    public void testFindImpliedPermissionsForInstantiationWithInstantiationAnnotation() {
        final Set<String> permissions = securityCheck.findImpliedPermissions(InstantiationActionAnnotatedTextField.class, InstantiateAction.class);
        Assert.assertEquals(permissions.size(), 1, "There should be one permission!");
    }

    @Test
    public void testFindImpliedPermissionsForInstantiationWithRenderAnnotation() {
        final Set<String> permissions = securityCheck.findImpliedPermissions(RenderActionAnnotatedTextField.class, InstantiateAction.class);
        Assert.assertEquals(permissions.size(), 1, "There should be one permission!");
    }

    @Test
    public void testFindImpliedPermissionsForInstantiationWithEnableAndRenderAnnotation() {
        final Set<String> permissions = securityCheck.findImpliedPermissions(EnableAndRenderActionAnnotatedTextField.class, InstantiateAction.class);
        Assert.assertEquals(permissions.size(), 2, "There should be one permission!");
    }

    @Test
    public void testFindImpliedPermissionsForRenderWithEnableAndRenderAnnotation() {
        final Set<String> permissions = securityCheck.findImpliedPermissions(EnableAndRenderActionAnnotatedTextField.class, RenderAction.class);
        Assert.assertEquals(permissions.size(), 2, "There should be 2 permissions!");
    }

    @Test
    public void testFindImpliedPermissionsForEnableWithEnableAndRenderAnnotation() {
        final Set<String> permissions = securityCheck.findImpliedPermissions(EnableAndRenderActionAnnotatedTextField.class, EnableAction.class);
        Assert.assertEquals(permissions.size(), 1, "There should be 1 permission!");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindSecurityConstraintsForEnableWithNullParameter() {
        final List<Class<? extends SecurityConstraint>> list = securityCheck.findSecurityConstraintsForEnable(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindSecurityConstraintsForRenderWithNullParameter() {
        final List<Class<? extends SecurityConstraint>> list = securityCheck.findSecurityConstraintsForRender(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindSecurityConstraintsForInstantiationWithNullParameter() {
        final List<Class<? extends InstantiationSecurityConstraint>> list = securityCheck.findSecurityConstraintsForInstantiation(null);
    }

    @Test
    public void testFindSecurityConstraintsForEnableWithOneConstraintInEnableAnnotation() {
        final Component component = new EnableActionAnnotatedTextField("tutu");
        final List<Class<? extends SecurityConstraint>> list = securityCheck.findSecurityConstraintsForEnable(component);
        Assert.assertEquals(list.size(), 1, "One constraint is set on the component for enable.");
    }

    @Test
    public void testFindSecurityConstraintsForEnableWithOneConstraintInRenderAnnotation() {
        final Component component = new RenderActionAnnotatedTextField("tutu");
        final List<Class<? extends SecurityConstraint>> list = securityCheck.findSecurityConstraintsForEnable(component);
        Assert.assertEquals(list.size(), 0, "No constraint is set on the component for enable.");
    }

    @Test
    public void testFindSecurityConstraintsForRenderWithOneConstraintInRenderAnnotation() {
        final List<Class<? extends SecurityConstraint>> list = securityCheck.findSecurityConstraintsForRender(new RenderActionAnnotatedTextField("tutu"));
        Assert.assertEquals(list.size(), 1, "One constraint is set on the component for render.");
    }

    @Test
    public void testFindSecurityConstraintsForRenderWithOneConstraintInEnableAnnotation() {
        final List<Class<? extends SecurityConstraint>> list = securityCheck.findSecurityConstraintsForRender(new EnableActionAnnotatedTextField("tutu"));
        Assert.assertEquals(list.size(), 0, "No constraint is set on the component for render.");
    }

    @Test
    public void testFindSecurityConstraintsForInstantiationWithOneConstraintInInstantiationAnnotation() {
        final List<Class<? extends InstantiationSecurityConstraint>> list = securityCheck.findSecurityConstraintsForInstantiation(InstantiationActionAnnotatedTextField.class);
        Assert.assertEquals(list.size(), 1, "One constraint is set on the component class for instantiation.");
    }

    @Test
    public void testFindSecurityConstraintsForInstantiationWithOneConstraintInRenderAnnotation() {
        final List<Class<? extends InstantiationSecurityConstraint>> list = securityCheck.findSecurityConstraintsForInstantiation(RenderActionAnnotatedTextField.class);
        Assert.assertEquals(list.size(), 0, "No constraint is set on the component for instantiation.");
    }
}
