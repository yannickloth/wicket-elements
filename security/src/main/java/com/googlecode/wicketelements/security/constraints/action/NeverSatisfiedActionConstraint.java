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
package com.googlecode.wicketelements.security.constraints.action;

import com.googlecode.wicketelements.security.SecurityConstraint;
import com.googlecode.wicketelements.security.annotations.Factory;
import org.apache.wicket.Component;

public class NeverSatisfiedActionConstraint implements SecurityConstraint {
    private static final NeverSatisfiedActionConstraint INSTANCE = new NeverSatisfiedActionConstraint();

    @Factory
    public static NeverSatisfiedActionConstraint getInstance() {
        return INSTANCE;
    }

    private NeverSatisfiedActionConstraint() {
        if (INSTANCE != null) {
            throw new IllegalStateException("The constructor of this singleton may only be executed once.");
        }
    }

    public <T extends Component> boolean isSatisfiedConstraint(final T componentClassParam) {
        return false;
    }
}