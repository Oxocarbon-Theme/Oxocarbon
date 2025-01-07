package com.github.lynxie.oxocarbon

import com.github.lynxie.oxocarbon.panels.AppearanceSettingsPanel
import com.github.lynxie.oxocarbon.panels.CustomThemeSettingsPanel
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import java.util.*

class OxocarbonManager {

    companion object {

        private const val PLUGIN_ID : String = "com.github.lynxie.Oxocarbon"

        var appearanceSettingsPanel: AppearanceSettingsPanel? = null
        var customThemeSettingsPanel: CustomThemeSettingsPanel? = null
        
        val currentVersion : String
            get() = Objects.requireNonNull(PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID))?.version ?: "")
    }
}