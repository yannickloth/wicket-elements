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

import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Yannick LOTH
 */
public abstract class SecureWebPage extends WebPage {

    public SecureWebPage(final IPageMap pageMap, final PageParameters parameters) {
        super(pageMap, parameters);
        init();
    }

    public SecureWebPage(final PageParameters parameters) {
        super(parameters);
        init();
    }

    public SecureWebPage(final IPageMap pageMap, final IModel<?> model) {
        super(pageMap, model);
        init();
    }

    public SecureWebPage(final IPageMap pageMap) {
        super(pageMap);
        init();
    }

    public SecureWebPage(final IModel<?> model) {
        super(model);
        init();
    }

    public SecureWebPage() {
        super();
        init();
    }

    private void init() {
        if (isNotSignInOrSignOutPage() && isSecurityInterceptedPage() && !isErrorPage()) {
            //Do not intercept these pages, as they may be shown even without
            //authentication!
            //This is obvious for SignIn and SignOut, error pages are here too
            //as errors may also occur in SignIn and SignOut pages!
            if (SecureWebApplication.get().isLoginRequired()) {
                if (!SecureSession.get().isAuthenticated()) {
                    throw new RestartResponseAtInterceptPageException(SecureWebApplication.get().getSignInPage());
                }
            }
        }
    }

    private boolean isNotSignInOrSignOutPage() {
        return !getClass().equals(SecureWebApplication.get().getSignInPage())
                && !getClass().equals(SecureWebApplication.get().getSignOutPage());
    }

    public abstract boolean isSecurityInterceptedPage();
}
