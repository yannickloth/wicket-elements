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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.Model;

import static com.googlecode.jbp.common.requirements.Reqs.PARAM_REQ;

/**
 * Factory to create an attribute modifier for various attributes.
 *
 * @author Yannick LOTH
 */
public final class AttributeModifierFactory {

    private static final String CLASS_ATTRIBUTE = "class";
    private static final String ALT_ATTRIBUTE = "alt";
    private static final String SRC_ATTRIBUTE = "src";
    private static final String TITLE_ATTRIBUTE = "title";

    /**
     * Instanciates a new attribute modifier for the attribute <code>alt</code>.
     *
     * @param altText The name of the <code>alt</code> text to add to the tag. Must
     *                not be blank.
     * @return The new attribute modifier.
     */
    public static AttributeModifier newAttributeAppenderForAlt(
            final String altText) {
        PARAM_REQ.String.requireNotBlank(altText, "The alt attribute parameter must not be null.");
        return new AttributeModifier(ALT_ATTRIBUTE, true, new Model<String>(
                altText));
    }

    /**
     * Instanciates a new attribute modifier for the attribute <code>src</code>.
     *
     * @param srcText The name of the <code>src</code> text to add to the tag. Must
     *                not be blank.
     * @return The new attribute modifier.
     */
    public static AttributeModifier newAttributeAppenderForSrc(
            final String srcText) {
        PARAM_REQ.String.requireNotBlank(srcText, "The src attribute parameter must not be null.");
        return new AttributeModifier(SRC_ATTRIBUTE, true, new Model<String>(
                srcText));
    }

    /**
     * Instanciates a new attribute modifier for the attribute
     * <code>title</code>.
     *
     * @param titleText The name of the <code>title</code> text to add to the tag.
     *                  Must not be blank.
     * @return The new attribute modifier.
     */
    public static AttributeModifier newAttributeAppenderForTitle(
            final String titleText) {
        PARAM_REQ.String.requireNotBlank(titleText, "The title attribute parameter must not be null.");
        return new AttributeModifier(TITLE_ATTRIBUTE, true, new Model<String>(
                titleText));
    }

    /**
     * Instanciates a new attribute modifier for the attribute
     * <code>class</code>.
     *
     * @param cssClassNameParam The name of the CSS class to add to the tag. Must not be
     *                          blank.
     * @return The new attribute modifier.
     */
    public static AttributeModifier newAttributeModifierForClass(
            final String cssClassNameParam) {
        PARAM_REQ.String.requireNotBlank(cssClassNameParam, "The CSS class parameter must not be null.");
        return new AttributeModifier(CLASS_ATTRIBUTE, true, new Model<String>(
                cssClassNameParam.trim()));
    }

    private AttributeModifierFactory() {
    }
}
