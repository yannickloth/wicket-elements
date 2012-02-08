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
package com.googlecode.wicketelements.components.locale;

import org.apache.wicket.model.IModel;

import java.util.Locale;

import static com.googlecode.jbp.common.requirements.Reqs.PARAM_REQ;

/**
 * Model which contains a locale.  Used for localization.
 *
 * @author Yannick LOTH
 */
public class LocaleModel implements IModel<Locale> {

    private Locale locale;

    public LocaleModel(final Locale localeParam) {
        PARAM_REQ.Object.requireNotNull(localeParam, "The locale parameter must not be null.");
        locale = localeParam;
    }

    public Locale getObject() {
        return locale;
    }

    public void setObject(final Locale localeParam) {
        locale = localeParam;
    }

    public void detach() {
    }
}
