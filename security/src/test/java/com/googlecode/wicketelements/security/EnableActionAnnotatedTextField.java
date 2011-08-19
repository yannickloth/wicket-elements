package com.googlecode.wicketelements.security;

import com.googlecode.wicketelements.security.annotations.EnableAction;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

@EnableAction(permission = "instantiate")
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
