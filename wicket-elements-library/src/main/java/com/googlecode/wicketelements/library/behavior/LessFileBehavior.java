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
package com.googlecode.wicketelements.library.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import static com.googlecode.jbp.common.requirements.Reqs.PARAM_REQ;

public class LessFileBehavior extends Behavior {
    private final ResourceReference reference;

    public LessFileBehavior(final Class<?> scopeParam, final String nameParam) {
        PARAM_REQ.Object.requireNotNull(scopeParam, "less.js file scope must not be null");
        PARAM_REQ.String.requireNotBlank(nameParam, "less.js file name must not be blank");
        reference = new PackageResourceReference(scopeParam, nameParam);
    }

    @Override
    public void renderHead(final Component componentParam, final IHeaderResponse iHeaderResponseParam) {
        if (reference == null) {
            throw new IllegalArgumentException("reference cannot be null");
        }
        final CharSequence url = RequestCycle.get().urlFor(reference, null);
        final String contribution = buildHeaderString(url);
        iHeaderResponseParam.renderString(contribution);
    }

    private String buildHeaderString(CharSequence urlParam) {
        return "<link rel=\"stylesheet/less\" type=\"text/css\" href=\"" + urlParam + "\">";
    }
}
