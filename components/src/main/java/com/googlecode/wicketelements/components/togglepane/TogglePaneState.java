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

import java.io.Serializable;

/**
 * Contains the state of the associated toggle pane.
 */
public interface TogglePaneState extends Serializable {
    /**
     * Toggles the content of the toggle pane.
     */
    void toggleContent();

    /**
     * Toggles the enable state of the toggle pane.  If disabled, the user can no longer click on the title to toggle
     * the toggle pane.
     */
    void toggleEnableState();

    /**
     * Tells wether the toggle pane is collapsed.
     *
     * @return {@code true} if the toggle pane is collapsed (content not visible), {@code false} else.
     */
    boolean isCollapsed();

    /**
     * Tells wether the toggle pane is expanded.
     *
     * @return {@code true} if the toggle pane is expanded (content visible), {@code false} else.
     */
    boolean isExpanded();

    /**
     * Tells wether the toggle pane is enabled.
     *
     * @return {@code true} if the toggle pane is enabled, {@code false} else.
     */
    boolean isEnabled();

    /**
     * Tells wether the toggle pane is disabled.
     *
     * @return {@code true} if the toggle pane is disabled, {@code false} else.
     */
    boolean isDisabled();

    /**
     * Add an event listener to the toggle panel's state object.
     *
     * @param togglePaneStateListenerParam The specified event listener.  Must not be {@code null}.
     */
    void addEventListener(final TogglePaneStateListener togglePaneStateListenerParam);

    /**
     * Removes an event listener from the toggle panel's state object.
     *
     * @param togglePaneStateListenerParam The specified event listener.  Must not be {@code null}.
     */
    void removeEventListener(final TogglePaneStateListener togglePaneStateListenerParam);
}
