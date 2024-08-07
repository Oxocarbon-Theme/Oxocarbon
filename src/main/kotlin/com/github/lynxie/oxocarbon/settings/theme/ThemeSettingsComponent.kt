package com.github.lynxie.oxocarbon.settings.theme

import com.intellij.ide.ui.LafManager
import com.intellij.openapi.ui.ComboBox
import com.intellij.util.ui.FormBuilder
import javax.swing.JLabel
import javax.swing.JPanel


class ThemeSettingsComponent {

    private val themeOptions = ThemeVariant.values()
    private val lafManagerInstance : LafManager = LafManager.getInstance()
    
    val settingsPanel : JPanel
    val themeSelectionDropdown = ComboBox<Any?>()
    
    init {
        themeOptions.forEach {
            themeSelectionDropdown.addItem(it.themeName)
            
            if (it.themeName == lafManagerInstance.currentLookAndFeel.name) {
                themeSelectionDropdown.selectedItem = it.themeName
            }
        }
        
        settingsPanel = FormBuilder.createFormBuilder()
            .setFormLeftIndent(0)
            .addLabeledComponent(JLabel("Theme mode: "), themeSelectionDropdown, 0, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }
    
    fun setDropdownItem(dropdownItem: Any?) {
        themeSelectionDropdown.selectedItem = dropdownItem
    }
}