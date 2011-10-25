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
package com.googlecode.wicketelements.components.togglepane;

/**
 * Listener for toggle pane state changes.
 */
public interface TogglePaneStateListener {
    /**
     * Fired when the toggle pane is enabled.
     *
     * @param stateEventParam The event object.
     */
    void togglePaneEnabled(final TogglePaneStateEvent stateEventParam);

    /**
     * Fired when the toggle pane is disabled.
     *
     * @param stateEventParam The event object.
     */
    void togglePaneDisabled(TogglePaneStateEvent stateEventParam);

    /**
     * Fired when the toggle pane is collapsed.
     *
     * @param stateEventParam The event object.
     */
    void togglePaneCollapsed(TogglePaneStateEvent stateEventParam);

    /**
     * Fired when the toggle pane is expanded.
     *
     * @param stateEventParam The event object.
     */
    void togglePaneExpanded(TogglePaneStateEvent stateEventParam);
}
