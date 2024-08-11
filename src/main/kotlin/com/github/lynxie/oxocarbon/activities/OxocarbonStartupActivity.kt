package com.github.lynxie.oxocarbon.activities

import com.github.lynxie.oxocarbon.OxocarbonManager
import com.github.lynxie.oxocarbon.notifications.SettingsNotifications
import com.github.lynxie.oxocarbon.settings.OxocarbonVersionSettings
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.util.registry.Registry

class OxocarbonStartupActivity : ProjectActivity {
    
    override suspend fun execute(project: Project) {
        removeDefaultBlankEditorText()
        sendStartupNotifications(project)
    }
    
    private fun removeDefaultBlankEditorText() {
        Registry.get("editor.paint.empty.text").setValue(false)
    }
    
    private fun sendStartupNotifications(project: Project) {
        val currentPluginVersion : String = OxocarbonManager.currentVersion
        val versionSettingsInstance : OxocarbonVersionSettings = OxocarbonVersionSettings.instance
        
        if (versionSettingsInstance.version == null || versionSettingsInstance.version!!.isEmpty()) {
            versionSettingsInstance.version = currentPluginVersion
            SettingsNotifications.notifyWelcome(project)
        }
    }
}