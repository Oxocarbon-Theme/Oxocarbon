package com.github.lynxie.oxocarbon.settings.configurable

import com.github.lynxie.oxocarbon.OxocarbonManager
import com.github.lynxie.oxocarbon.enums.SettingsCategory
import com.github.lynxie.oxocarbon.panels.CustomThemeSettingsPanel
import com.intellij.openapi.options.SearchableConfigurable
import javax.swing.JComponent

class CustomThemeSettingsConfigurable : SearchableConfigurable {

    private var customThemeSettingsPanel : CustomThemeSettingsPanel? = null

    override fun createComponent(): JComponent {
        customThemeSettingsPanel = CustomThemeSettingsPanel()
        OxocarbonManager.customThemeSettingsPanel = customThemeSettingsPanel
        return customThemeSettingsPanel!!.panel
    }

    override fun getId(): String {
        return SettingsCategory.CUSTOM_THEME.id
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun apply() {
        TODO("Not yet implemented")
    }

    override fun getDisplayName(): String {
        return SettingsCategory.CUSTOM_THEME.categoryName
    }

    override fun disposeUIResources() {
        customThemeSettingsPanel = null
    }
}
