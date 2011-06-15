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

import com.googlecode.wicketelements.common.parameter.ParamValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic sign out page.
 * @author Yannick LOTH
 */
public final class SignOutPage extends WebPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignOutPage.class);
    public static final String REDIRECTPAGE_PARAM = "redirectPage";

    public SignOutPage(final PageParameters params) {
        ParamValidator.notNull(params);
        final String page = params.getString(REDIRECTPAGE_PARAM);
        Class<? extends Page> pageClass;
        if (page != null && !StringUtils.isBlank(page)) {
            LOGGER.debug("Redirect page not blank: {}", page);
            try {
                pageClass = (Class<? extends Page>) Class.forName(page);
            } catch (ClassNotFoundException ex) {
                LOGGER.debug("Class not found for redirect page: {}", page);
                pageClass = getApplication().getHomePage();
            }
        } else {
            LOGGER.debug("No redirect page, redirecting to application home page.");
            pageClass = getApplication().getHomePage();
        }
        getSession().invalidateNow();
        getRequestCycle().setRedirect(true);
        setResponsePage(pageClass);
    }
}
