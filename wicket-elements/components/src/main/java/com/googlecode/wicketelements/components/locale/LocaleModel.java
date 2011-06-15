package com.googlecode.wicketelements.components.locale;

import java.util.Locale;
import org.apache.wicket.model.IModel;

/**
 * Model which contains a locale.  Used for localization.
 * @author Yannick LOTH
 */
public class LocaleModel implements IModel<Locale> {

    private Locale locale;

    public LocaleModel(final Locale localeParam) {
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
