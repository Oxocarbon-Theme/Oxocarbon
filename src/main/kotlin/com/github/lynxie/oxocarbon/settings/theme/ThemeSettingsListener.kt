package com.github.lynxie.oxocarbon.settings.theme

import com.intellij.ide.actions.QuickChangeLookAndFeel
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.editor.colors.EditorColorsManager
import java.util.*

class ThemeSettingsListener : LafManagerListener {
    
    private var editorColorsManager : EditorColorsManager = EditorColorsManager.getInstance()

    override fun lookAndFeelChanged(lafManager : LafManager) {
        
        val currentlyActiveUiName = lafManager.currentLookAndFeel.name
        val themeSettingsState = Objects.requireNonNull(ThemeSettings.instance.state)
        
        val selectedThemeName = themeSettingsState.dropdownState
        
        if (selectedThemeName != currentlyActiveUiName && selectedThemeName.toString().contains("Oxocarbon")) {
            val selectedLaf = LafManager.getInstance().installedLookAndFeels.first { it.name == selectedThemeName }
            
            QuickChangeLookAndFeel.switchLafAndUpdateUI(lafManager, selectedLaf, false)
        }
    }
}