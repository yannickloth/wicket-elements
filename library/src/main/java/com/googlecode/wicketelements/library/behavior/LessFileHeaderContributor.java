package com.googlecode.wicketelements.library.behavior;

import com.googlecode.jbp.common.requirements.ParamRequirements;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.StringHeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;

public class LessFileHeaderContributor implements IHeaderContributor {
    private ResourceReference reference;

    public LessFileHeaderContributor(final Class<?> scopeParam, final String nameParam) {
        ParamRequirements.INSTANCE.requireNotNull(scopeParam, "less.js file scope must not be null");
        ParamRequirements.INSTANCE.requireNotBlank(nameParam, "less.js file name must not be blank");
        reference = new ResourceReference(scopeParam, nameParam);
    }

    public void renderHead(final IHeaderResponse iHeaderResponseParam) {
        if (reference == null) {
            throw new IllegalArgumentException("reference cannot be null");
        }
        final CharSequence url = RequestCycle.get().urlFor(reference);
        final String contribution = buildHeaderString(url);
        final IHeaderContributor stringHeaderContributor = new StringHeaderContributor(contribution);
        stringHeaderContributor.renderHead(iHeaderResponseParam);
    }

    private String buildHeaderString(CharSequence urlParam) {
        return "<link rel=\"stylesheet/less\" type=\"text/css\" href=\"" + urlParam + "\">";
    }
}
