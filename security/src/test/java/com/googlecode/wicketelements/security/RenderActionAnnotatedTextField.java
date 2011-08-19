package com.googlecode.wicketelements.security;

import com.googlecode.wicketelements.security.annotations.RenderAction;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

@RenderAction(permission = "instantiate")
public class RenderActionAnnotatedTextField extends TextField<String> {
    public RenderActionAnnotatedTextField(String id) {
        super(id);
    }

    public RenderActionAnnotatedTextField(String id, Class<String> type) {
        super(id, type);
    }

    public RenderActionAnnotatedTextField(String id, IModel<String> model) {
        super(id, model);
    }

    public RenderActionAnnotatedTextField(String id, IModel<String> model, Class<String> type) {
        super(id, model, type);
    }
}
