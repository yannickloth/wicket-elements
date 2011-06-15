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

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

/**
 * Test file for <code>ParamValidator</code> class.
 * @author Yannick LOTH
 */
@Test
public class ParamValidatorTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNotNullWithNull() {
        ParamValidator.notNull(null);
    }

    @Test
    public void testNotNullWithString() {
        ParamValidator.notNull("HELO");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNotBlankWithNull() {
        ParamValidator.notBlank(null);
    }

    @Test
    public void testNotBlankWithString() {
        ParamValidator.notBlank("HELO");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNotBlankWithEmptyString() {
        ParamValidator.notBlank("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNotBlankWithEmptySpacesString() {
        ParamValidator.notBlank("  ");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNotEmptyWithNullCollection() {
        ParamValidator.notEmpty(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNotEmptyWithEmptyList() {
        ParamValidator.notEmpty(new ArrayList<String>());
    }

    @Test
    public void testNotEmptyWithFilledList() {
        final List<String> coll = new ArrayList<String>();
        coll.add("HELO");
        ParamValidator.notEmpty(coll);
    }
}
