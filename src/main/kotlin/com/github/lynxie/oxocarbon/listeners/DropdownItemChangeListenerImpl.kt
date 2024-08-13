package com.github.lynxie.oxocarbon.listeners

import com.github.lynxie.oxocarbon.OxocarbonManager
import com.github.lynxie.oxocarbon.panels.AppearanceSettingsPanel
import com.github.lynxie.oxocarbon.enums.ThemeVariant
import com.github.lynxie.oxocarbon.topics.DropdownItemChangeListener
import com.intellij.ide.actions.QuickChangeLookAndFeel
import com.intellij.ide.ui.LafManager

class DropdownItemChangeListenerImpl : DropdownItemChangeListener {

    @Suppress("UnstableApiUsage")
    override fun dropdownItemChanged() {
        val lafManagerInstance = LafManager.getInstance()
        val appearanceSettingsPanel : AppearanceSettingsPanel? = OxocarbonManager.appearanceSettingsPanel

        val currentlyActiveLaf = lafManagerInstance.currentUIThemeLookAndFeel

        val selectedTheme = appearanceSettingsPanel?.themeSelectionDropdown?.selectedItem as ThemeVariant

        if (currentlyActiveLaf.name != selectedTheme.themeName && selectedTheme.themeName.contains("Oxocarbon")) {
            appearanceSettingsPanel.setVariantDropdownItem(selectedTheme)
        }

        val selectedLaf = lafManagerInstance.installedThemes.first { it.name == selectedTheme.toString() }

        QuickChangeLookAndFeel.switchLafAndUpdateUI(lafManagerInstance, selectedLaf, false)
    }
}