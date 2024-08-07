package com.github.lynxie.oxocarbon

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import java.util.Objects

class OxocarbonManager {
    
    companion object {
        private const val PLUGIN_ID : String = "com.github.lynxie.Oxocarbon"
        
        val currentVersion : String
            get() = Objects.requireNonNull(PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID))?.version ?: "")
    }
}