package com.github.lynxie.oxocarbon.panels

import com.github.lynxie.oxocarbon.enums.SettingsCategory
import com.intellij.ide.DataManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.ex.Settings
import com.intellij.ui.components.ActionLink
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel

/**
 * Parent container for all other settings that we may have in the future.
 *
 * DO NOT configure this class, if sub-categories are to be added to [SettingsParentPanel], please add an entry to [SettingsCategory]
 */
class SettingsParentPanel {

    val settingsPanel: JPanel

    init {
        settingsPanel = initialiseActionLinks()
        initialiseActionLinkListeners()
    }

    /**
     * Dynamically creates an [ActionLink] for each [SettingsCategory], and adds it to the [SettingsParentPanel.settingsPanel].
     */
    private fun initialiseActionLinks(): JPanel {
        val formBuilder = FormBuilder.createFormBuilder()
        SettingsCategory.values().forEach { category ->
            if (category.categoryName != SettingsCategory.PARENT.categoryName) {
                formBuilder.addComponent(ActionLink(category.categoryName))
            }
        }
        return formBuilder.setFormLeftIndent(0).addComponentFillVertically(JPanel(), 0).panel
    }

    /**
     * Dynamically creates an [ActionLink.actionListener] for all sub categories stored within [SettingsParentPanel]
     *
     */
    private fun initialiseActionLinkListeners() {
        settingsPanel.components.forEach { component ->
            if (component is ActionLink && component.text != SettingsCategory.PARENT.categoryName) {
                component.addActionListener {
                    val settingsCategory = SettingsCategory.getSettingsCategoryByName(component.text)
                    ApplicationManager.getApplication().invokeLater {
                        val settings = Settings.KEY.getData(DataManager.getInstance().getDataContext(settingsPanel))!!

                        settings.select(settings.find(settingsCategory.id))
                    }
                }
            }
        }
    }
}