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

import com.googlecode.wicketelements.common.parameter.ParamValidator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;

/**
 * Factory to create an attribute appender for the attribute <code>class</code>.
 * @author Yannick LOTH
 */
public final class AttributeAppenderFactory {

    private static final String WHITE_SPACE = " ";
    private static final String CLASS_ATTRIBUTE = "class";

    private AttributeAppenderFactory() {
    }

    /**
     * Instanciates a new attribute appender for the attribute <code>class</code>.
     * @param cssClassNameParam The name of the CSS class to add to the tag. Must not be blank.
     * @return The new attribute appender.
     */
    public static AttributeAppender newAttributeAppenderForClass(final String cssClassNameParam) {
        ParamValidator.notBlank(cssClassNameParam);
        return new AttributeAppender(CLASS_ATTRIBUTE, new Model<String>(cssClassNameParam.trim()), WHITE_SPACE);
    }
}
