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
package com.googlecode.wicketelements.components.lists;

import org.apache.wicket.Component;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

/**
 * Model class for the {@code ComponentListPanel}.
 * The list of components may be set dynamically, as this model extends {@code LoadableDetachableModel}.
 */
public abstract class ComponentListModel extends LoadableDetachableModel<List<? extends Component>> {
}
