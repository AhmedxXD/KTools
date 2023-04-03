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

package com.github.kpgtb.ktools.manager.gui.item.common;

import com.github.kpgtb.ktools.manager.gui.container.PagedGuiContainer;
import com.github.kpgtb.ktools.manager.gui.item.GuiItem;
import com.github.kpgtb.ktools.manager.language.LanguageLevel;
import com.github.kpgtb.ktools.util.item.ItemBuilder;
import com.github.kpgtb.ktools.util.wrapper.ToolsObjectWrapper;
import org.bukkit.Material;

/**
 * Common used GuiItem - Turn Left Item
 */
public class LeftItem {
    /**
     * Get this item
     * @param wrapper Instance of ToolsObjectWrapper
     * @param pagedGuiContainer Container that should be turned
     * @return Item
     */
    public static GuiItem get(ToolsObjectWrapper wrapper, PagedGuiContainer pagedGuiContainer) {
        Material material = Material.ARROW;
        try {
            material = Material.valueOf(wrapper.getPlugin().getConfig().getString("gui.pages.leftItem").toUpperCase());
        } catch (Exception e) {}

        GuiItem result = new GuiItem(
          new ItemBuilder(material)
              .displayname(wrapper.getLanguageManager().getSingleString(LanguageLevel.GLOBAL, "pagesLeft"))
              .build()
        );
        result.setClickAction(e -> pagedGuiContainer.previousPage());
        return result;
    }
}
