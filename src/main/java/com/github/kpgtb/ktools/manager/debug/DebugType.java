/*
 *    Copyright 2023 KPG-TB
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.kpgtb.ktools.manager.debug;

/**
 * Debug type that is used to send message
 */
public enum DebugType {
    START("debug.start"),
    STOP("debug.stop"),
    LANGUAGE("debug.language"),
    CACHE("debug.cache"),
    COMMAND("debug.command"),
    PARSER("debug.parser"),
    LISTENER("debug.listener"),
    RECIPE("debug.recipe"),
    GUI("debug.gui"),
    DATA("debug.data"),
    UPDATER("debug.updater");

    private final String configStr;

    DebugType(String configStr) {
        this.configStr = configStr;
    }

    /**
     * Get path to config
     * @return Path to config where type is enabled or disabled
     */
    public String getConfigStr() {
        return configStr;
    }
}
