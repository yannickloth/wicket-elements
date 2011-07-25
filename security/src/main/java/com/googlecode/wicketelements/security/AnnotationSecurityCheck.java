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

import com.googlecode.jbp.common.requirements.ParamRequirements;
import com.googlecode.wicketelements.common.annotation.AnnotationHelper;
import com.googlecode.wicketelements.security.annotations.SignIn;
import com.googlecode.wicketelements.security.annotations.SignInRequired;
import com.googlecode.wicketelements.security.annotations.SignOut;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.settings.IApplicationSettings;

public class AnnotationSecurityCheck implements SecurityCheck {
    public boolean isSignInPage(final Class<? extends Page> pageClassParam) {
        ParamRequirements.INSTANCE.requireNotNull(pageClassParam);
        return pageClassParam.equals(signInPage());
    }

    public boolean isSignOutPage(final Class<? extends Page> pageClassParam) {
        ParamRequirements.INSTANCE.requireNotNull(pageClassParam);
        return pageClassParam.equals(signOutPage());
    }

    public boolean isErrorPage(final Class<? extends Page> pageClassParam) {
        ParamRequirements.INSTANCE.requireNotNull(pageClassParam);
        final IApplicationSettings settings = Application.get().getApplicationSettings();
        return pageClassParam.isAssignableFrom(settings.getAccessDeniedPage())
                || pageClassParam.isAssignableFrom(settings.getInternalErrorPage())
                || pageClassParam.isAssignableFrom(settings.getPageExpiredErrorPage());
    }

    public boolean isSignInRequired() {
        return AnnotationHelper.hasAnnotation(Application.get().getClass(), SignInRequired.class);
    }

    public Class<? extends Page> signInPage() {
        Class<? extends Page> page = Application.get().getHomePage();
        if (AnnotationHelper.hasAnnotation(Application.get().getClass(), SignIn.class)) {
            final SignIn annot = AnnotationHelper.getAnnotation(Application.get().getClass(), SignIn.class);
            page = annot.page();
        }
        return page;
    }

    public Class<? extends Page> signOutPage() {
        Class<? extends Page> page = null;
        if (AnnotationHelper.hasAnnotation(Application.get().getClass(), SignOut.class)) {
            final SignOut annot = AnnotationHelper.getAnnotation(Application.get().getClass(), SignOut.class);
            page = annot.page();
        }
        return page;
    }
}
