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
package com.googlecode.wicketelements.common.parameter;

import java.util.Collection;
import org.apache.commons.lang.StringUtils;

/**
 * Provides several static methods to check parameters.
 * @author Yannick LOTH
 */
public final class ParamValidator {

    private ParamValidator() {
    }

    /**
     * Checks that a parameter is not null.
     * @param objParam The parameter.
     */
    public static void notNull(final Object objParam) {
        if (objParam == null) {
            throw new IllegalArgumentException("Parameter must not be null.");
        }
    }

    /**
     * Checks that a string parameter is not blank (null or empty or only spaces).
     * @param strParam The string.
     */
    public static void notBlank(final String strParam) {
        if (strParam == null || StringUtils.isBlank(strParam)) {
            throw new IllegalArgumentException("String parameter must not be blank.");
        }
    }

    /**
     * Checks that a collection is not empty.
     * @param collParam The collection which must not be empty.  Must not be <code>null</code>.
     */
    public static void notEmpty(final Collection collParam) {
        notNull(collParam);
        if (collParam.isEmpty()) {
            throw new IllegalArgumentException("Collection parameter must not be empty.");
        }
    }

    public static void instanceOf(final Object obj, final Class<?> klass, final String paramNameParam) {
        if (!klass.isAssignableFrom(obj.getClass())) {
            throw new IllegalArgumentException("Parameter '".concat(paramNameParam).concat("' must be have class ").concat(klass.getName()));
        }
    }

    public static void collectionElementsInstanceOf(final Collection<?> coll, final Class<?> klass, final String paramNameParam) {
        for (final Object current : coll) {
            if (!klass.isAssignableFrom(current.getClass())) {
                throw new IllegalArgumentException("Collection parameter '".concat(paramNameParam).concat("' must only contain elements with class ").concat(klass.getName()));
            }
        }
    }
}
