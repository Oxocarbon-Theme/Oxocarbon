package com.github.lynxie.oxocarbon.panels

import com.github.lynxie.oxocarbon.enums.ThemeVariant
import com.github.lynxie.oxocarbon.settings.ThemeSettings
import com.github.lynxie.oxocarbon.topics.DropdownItemChangeListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.observable.util.whenItemSelected
import com.intellij.openapi.ui.ComboBox
import com.intellij.util.ui.FormBuilder
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JPanel

class AppearanceSettingsPanel {

    val settingsPanel: JPanel
    val themeSelectionDropdown = ComboBox(ThemeVariant.values())

    init {

        initializeDropdownCustomRenderer()

        settingsPanel = FormBuilder.createFormBuilder()
            .setFormLeftIndent(0)
            .addLabeledComponent(JLabel("Theme variant: "), themeSelectionDropdown, 0, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel

        themeSelectionDropdown.whenItemSelected<ThemeVariant> {
            ApplicationManager.getApplication().messageBus.syncPublisher(DropdownItemChangeListener.DROPDOWN_ITEM_TOPIC).dropdownItemChanged()
        }
    }


    /**
     * Initializes a custom renderer so that the dropdown renders the theme variant names properly.
     */

    private fun initializeDropdownCustomRenderer() {
        themeSelectionDropdown.renderer = object : DefaultListCellRenderer() {
            override fun getListCellRendererComponent(
                list: JList<*>?,
                value: Any?,
                index: Int,
                isSelected: Boolean,
                cellHasFocus: Boolean
            ): Component? {
                val renderComponent = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
                text = if (value is ThemeVariant) value.themeName else value.toString()
                return renderComponent
            }
        }
    }

    /**
     * Sets the theme variant to whatever was supplied, currently mainly used to synchronize the dropdown value to
     * whatever was selected within the IDE's default Appearance tab.
     *
     * @param themeVariant - Sets the dropdown item to the theme variant supplied.
     */
    fun setVariantDropdownItem(themeVariant: ThemeVariant) {
        themeSelectionDropdown.selectedItem = themeVariant
        ThemeSettings.instance.state.dropdownState = themeVariant
        themeSelectionDropdown.updateUI()
    }
}