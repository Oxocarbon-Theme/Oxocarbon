package com.github.lynxie.oxocarbon.panels

import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel

class CustomThemeSettingsPanel {

    val panel : JPanel

    init {
        panel = FormBuilder.createFormBuilder()
            .setFormLeftIndent(0)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }
}