package com.googlecode.wicketelements.components.locale;

import java.util.Locale;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;

import com.googlecode.wicketelements.library.behavior.AttributeModifierFactory;

/**
 * Panel that renders a list of locale links which, once clicked, change the
 * current locale of the user.
 * 
 * @author Yannick LOTH
 */
public class LanguagesPanel extends Panel {

    public LanguagesPanel(final String id, final LocalesListModel locales) {
        super(id);
        init(locales);
    }

    private void init(final LocalesListModel locales) {
        populateRepeatingView(locales);
    }

    /**
     * This method is executed when the link to a locale is added to the page.
     * 
     * @param localeLink
     *            The link for the specific locale.
     */
    protected void onLocaleLink(final Link<Locale> localeLink) {
    }

    /**
     * This method is executed when the link to the current locale is added to
     * the page. It may be useful, for example, to disable the link, as the
     * locale is already selected, or to add some attribute to the tag.
     * 
     * @param localeLink
     *            The link for the current locale.
     */
    protected void onSelectedLocaleLink(final Link<Locale> localeLink) {
    }

    private void populateRepeatingView(final LocalesListModel locales) {
        final ListView<Locale> lv = new ListView<Locale>("languages",
                locales.getObject()) {

            @Override
            protected void populateItem(final ListItem item) {
                final Locale locale = (Locale) item.getModelObject();
                {
                    final Link<Locale> link = new Link<Locale>("languageLink",
                            new LocaleModel(locale)) {

                        /**
                         * serialVersionUID.
                         */
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void onClick() {
                            final Locale locale = getModelObject();
                            Session.get().setLocale(locale);
                        }
                    };
                    final String upperCaseLocale = locale.getLanguage()
                            .toUpperCase(locale);
                    {
                        final Label languageLabel = new Label("languageLabel",
                                upperCaseLocale);
                        languageLabel.setRenderBodyOnly(true);
                        link.add(languageLabel);
                    }
                    if (locale.equals(Session.get().getLocale())) {
                        onLocaleLink(link);
                        onSelectedLocaleLink(link);
                    } else {
                        onLocaleLink(link);
                    }
                    link.add(AttributeModifierFactory
                            .newAttributeAppenderForTitle(new StringResourceModel(
                                    upperCaseLocale, this, null).getString()));
                    item.add(link);
                }
                item.setOutputMarkupId(true);
            }
        };
        lv.setRenderBodyOnly(true);
        add(lv);
    }
}
