package com.github.lynxie.oxocarbon.settings.theme

import com.github.lynxie.oxocarbon.notifications.SettingsNotifications
import com.intellij.ide.ui.LafManager
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.ProjectManager
import java.util.*
import javax.swing.JComponent

class ThemeSettingsConfigurable : Configurable {
    
    private lateinit var oxocarbonSettingsComponent : ThemeSettingsComponent
    
    override fun createComponent(): JComponent {
        oxocarbonSettingsComponent = ThemeSettingsComponent()
        return oxocarbonSettingsComponent.settingsPanel
    }

    override fun isModified(): Boolean {
        val themeSettingsState = Objects.requireNonNull(ThemeSettings.instance.state)
        
        return !oxocarbonSettingsComponent.themeSelectionDropdown.selectedItem?.equals(themeSettingsState.dropdownState)!!
    }

    override fun apply() {
        val themeSettingsState = Objects.requireNonNull(ThemeSettings.instance.state)
        
        themeSettingsState.dropdownState = oxocarbonSettingsComponent.themeSelectionDropdown.selectedItem!!

        if (themeSettingsState.dropdownState == "Oxocarbon Light") {
            SettingsNotifications.notifyLightVariantWarning(ProjectManager.getInstance().openProjects.first())
        }
        
        LafManager.getInstance().updateUI()
    }

    override fun getDisplayName(): String {
        return "Oxocarbon"
    }

    override fun reset() {
        val themeSettingsState = Objects.requireNonNull(ThemeSettings.instance.state)

        oxocarbonSettingsComponent.setDropdownItem(themeSettingsState.dropdownState)
    }
}