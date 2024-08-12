package com.github.lynxie.oxocarbon.settings.configurable

import com.github.lynxie.oxocarbon.OxocarbonManager
import com.github.lynxie.oxocarbon.panels.AppearanceSettingsPanel
import com.github.lynxie.oxocarbon.settings.ThemeSettings
import com.intellij.openapi.options.SearchableConfigurable
import java.util.*
import javax.swing.JComponent

class AppearanceSettingsConfigurable : SearchableConfigurable {

    private var appearanceSettingsPanel: AppearanceSettingsPanel? = null

    override fun getDisplayName(): String {
        return "Oxocarbon"
    }

    override fun getId(): String {
        return "2ea70210-3f9b-476a-9733-87d1b9864d67"
    }

    override fun createComponent(): JComponent {
        appearanceSettingsPanel = AppearanceSettingsPanel()
        OxocarbonManager.appearanceSettingsPanel = appearanceSettingsPanel
        return appearanceSettingsPanel!!.settingsPanel
    }

    // TODO:: Update this when we add more settings.
    override fun isModified(): Boolean {
        val themeSettingsState = Objects.requireNonNull(ThemeSettings.instance.state)

        return !appearanceSettingsPanel!!.themeSelectionDropdown.selectedItem?.equals(themeSettingsState.dropdownState)!!
    }

    // TODO:: Implement functionality for this when we have more settings. Useless to do it now.
    override fun apply() { }

    // TODO:: This does nothing right now, as the settings are never modified to begin with, since we dynamically update
    //  the theme when the dropdown item gets changed, so we'll need to update this when we add more settings.
    override fun reset() {
        val themeSettingsState = Objects.requireNonNull(ThemeSettings.instance.state)

        appearanceSettingsPanel?.setVariantDropdownItem(themeSettingsState.dropdownState)
    }

    override fun disposeUIResources() {
        appearanceSettingsPanel = null
    }
}