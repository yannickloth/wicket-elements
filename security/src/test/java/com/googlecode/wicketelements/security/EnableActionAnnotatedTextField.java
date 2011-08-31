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
import com.googlecode.wicketelements.security.constraints.action.AlwaysSatisfiedActionConstraint;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

@EnableAction(permission = "enable", constraints = AlwaysSatisfiedActionConstraint.class)
public class EnableActionAnnotatedTextField extends TextField<String> {
    public EnableActionAnnotatedTextField(String id) {
        super(id);
    }

    public EnableActionAnnotatedTextField(String id, Class<String> type) {
        super(id, type);
    }

    public EnableActionAnnotatedTextField(String id, IModel<String> model) {
        super(id, model);
    }

    public EnableActionAnnotatedTextField(String id, IModel<String> model, Class<String> type) {
        super(id, model, type);
    }
}
