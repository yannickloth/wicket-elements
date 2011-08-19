package com.googlecode.wicketelements.security;

import com.googlecode.wicketelements.security.annotations.InstantiateAction;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

@InstantiateAction(permission = "instantiate")
public class InstantiationActionAnnotatedTextField extends TextField<String> {
    public InstantiationActionAnnotatedTextField(String id) {
        super(id);
    }

    public InstantiationActionAnnotatedTextField(String id, Class<String> type) {
        super(id, type);
    }

    public InstantiationActionAnnotatedTextField(String id, IModel<String> model) {
        super(id, model);
    }

    public InstantiationActionAnnotatedTextField(String id, IModel<String> model, Class<String> type) {
        super(id, model, type);
    }
}
