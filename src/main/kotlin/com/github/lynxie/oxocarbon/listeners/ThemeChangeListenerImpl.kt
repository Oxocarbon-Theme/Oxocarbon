package com.github.lynxie.oxocarbon.listeners

import com.github.lynxie.oxocarbon.OxocarbonManager
import com.github.lynxie.oxocarbon.enums.ThemeVariant
import com.github.lynxie.oxocarbon.notifications.SettingsNotifications
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.ProjectManager

class ThemeChangeListenerImpl : LafManagerListener {

    override fun lookAndFeelChanged(lafManager: LafManager) {

        val selectedLafName = lafManager.currentLookAndFeel.name
        val settingsPanel = OxocarbonManager.appearanceSettingsPanel

        ApplicationManager.getApplication().invokeLater {
            val themeVariant = ThemeVariant.getThemeVariantWithName(selectedLafName)
            if (settingsPanel != null) {
                if (settingsPanel.themeSelectionDropdown.selectedItem != themeVariant) {
                    settingsPanel.setVariantDropdownItem(themeVariant)
                }
            }
        }

        // Notify user if needed
        if (selectedLafName == ThemeVariant.LIGHT.themeName) {
            val project = ProjectManager.getInstance().defaultProject
            SettingsNotifications.notifyLightVariantWarning(project)
        }
    }
}