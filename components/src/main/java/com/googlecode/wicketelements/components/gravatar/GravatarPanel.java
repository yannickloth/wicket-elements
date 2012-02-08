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
package com.googlecode.wicketelements.components.gravatar;

import com.googlecode.wicketelements.library.behavior.AttributeModifierFactory;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.resource.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This panel shows the user's gravatar avatar, given his email address. If
 * email address is <code>null</code>, the default gravatar is shown.
 * 
 * @author Yannick LOTH
 */
public final class GravatarPanel extends Panel {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private transient static final String MD5 = "MD5";

    private transient static final String DOT = ".";

    private transient static final String DOT_JPG = ".jpg";
    private transient static final char ZERO = '0';
    private transient static final String WICKET_ID_GRAVATAR_IMG = "gravatarImg";
    private transient static final Logger LOGGER = LoggerFactory
            .getLogger(GravatarPanel.class);

    public GravatarPanel(final String idParam, final String emailParam) {
        super(idParam);
        if (emailParam == null) {
            LOGGER.debug(new StringResourceModel("nullEmailParamLogMessage",
                    this, null).getString());
            addDefaultGravatarImage();
        } else {
            try {
                final byte[] hash = MessageDigest.getInstance(MD5).digest(
                        emailParam.getBytes());
                final StringBuilder hashString = new StringBuilder();
                for (final byte elementLocal : hash) {
                    final String hex = Integer.toHexString(elementLocal);
                    if (hex.length() == 1) {
                        hashString.append(ZERO);
                        hashString.append(hex.charAt(hex.length() - 1));
                    } else {
                        hashString.append(hex.substring(hex.length() - 2));
                    }
                }
                final Image imgLocal = new Image(WICKET_ID_GRAVATAR_IMG,(IResource)null);
                imgLocal.add(AttributeModifierFactory
                        .newAttributeAppenderForSrc("http://www.gravatar.com/avatar/"
                                + hashString.toString() + DOT_JPG));
                imgLocal.add(AttributeModifierFactory
                        .newAttributeAppenderForAlt(new StringResourceModel(
                                "gravatarAlt", this, null) + emailParam + DOT));
                add(imgLocal);
            } catch (final NoSuchAlgorithmException ex) {
                LOGGER.info(new StringResourceModel(
                        "md5HashAlgoNotFoundLogMessage", this, null)
                        .getString(), ex);
                addDefaultGravatarImage();
            }
        }
    }

    private void addDefaultGravatarImage() {
        final Image imgLocal = new Image(WICKET_ID_GRAVATAR_IMG,(IResource)null);
        imgLocal.add(AttributeModifierFactory
                .newAttributeAppenderForSrc(new StringResourceModel(
                        "defaultGravatarImageHref", this, null).getString()));
        imgLocal.add(AttributeModifierFactory
                .newAttributeAppenderForAlt(new StringResourceModel(
                        "defaultGravatarImageAlt", this, null).getString()));
        add(imgLocal);

    }
}
