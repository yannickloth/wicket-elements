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
package com.googlecode.wicketelements.components.locale;

import java.util.List;
import java.util.Locale;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Abstract model that contains a list of locales.  As the list of available
 * locales varies from one application to the other and as the source for
 * getting the locales may vary, the developer is required to implement the
 * <code>load</code> method.
 * 
 * @author Yannick LOTH
 */
public abstract class LocalesListModel extends LoadableDetachableModel<List<Locale>> {
}
