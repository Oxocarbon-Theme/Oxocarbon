package com.github.lynxie.oxocarbon.settings.configurable

import com.github.lynxie.oxocarbon.enums.SettingsCategory
import com.github.lynxie.oxocarbon.panels.SettingsParentPanel
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.Configurable.Composite
import com.intellij.openapi.options.SearchableConfigurable
import javax.swing.JComponent

class OxocarbonParentSettingsConfigurable : SearchableConfigurable, Composite {
    private var settingsParentPanel : SettingsParentPanel? = null

    override fun getDisplayName(): String {
        return SettingsCategory.PARENT.categoryName
    }

    override fun getId(): String {
        return SettingsCategory.PARENT.id
    }

    override fun createComponent(): JComponent {
        settingsParentPanel = SettingsParentPanel()
        return settingsParentPanel!!.settingsPanel
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun apply() { }

    override fun disposeUIResources() {
        settingsParentPanel = null
    }

    override fun getConfigurables(): Array<Configurable> {
        return SettingsCategory.getAllConfigurables()
    }
}