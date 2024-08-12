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
 * This must be updated with an [ActionLink] that selects the child category on click.
 */
class SettingsParentPanel {

    private val appearanceActionLink: ActionLink = ActionLink("Appearance")

    val settingsPanel: JPanel = FormBuilder.createFormBuilder()
        .setFormLeftIndent(0)
        .addComponent(appearanceActionLink)
        .addComponentFillVertically(JPanel(), 0)
        .panel

    init {
        appearanceActionLink.addActionListener {
            ApplicationManager.getApplication().invokeLater {
                val settings = Settings.KEY.getData(DataManager.getInstance().getDataContext(settingsPanel))!!

                settings.select(settings.find(SettingsCategory.APPEARANCE.id))

            }
        }
    }
}