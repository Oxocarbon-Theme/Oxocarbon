package com.github.lynxie.oxocarbon.settings

import com.github.lynxie.oxocarbon.enums.ThemeVariant
import com.intellij.ide.ui.LafManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NonNls

@State(name = "OxocarbonSettings", storages = [(Storage("OxocarbonSettings.xml"))])
class ThemeSettings private constructor(): PersistentStateComponent<ThemeSettings> {

    private val lafManagerInstance : LafManager = LafManager.getInstance()
    private var themeVariants = ThemeVariant.entries.toTypedArray()

    @NonNls
    var dropdownState : ThemeVariant = ThemeVariant.DARK
    
    init {
        
        themeVariants.forEach {
            @Suppress("UnstableApiUsage")
            if (it.themeName == lafManagerInstance.currentUIThemeLookAndFeel.name) {
                // Set initial state on IDE Launch.
                dropdownState = it
            }
        }
    }

    override fun getState() = this

    override fun loadState(state: ThemeSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }
    
    companion object {
        val instance: ThemeSettings
            get() = ApplicationManager.getApplication().getService(ThemeSettings::class.java)
    }
}