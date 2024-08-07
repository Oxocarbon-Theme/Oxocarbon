package com.github.lynxie.oxocarbon.settings.theme

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
    private var themeOptions = ThemeVariant.values()
    private var currentlySelectedTheme : String = ""
    

    @NonNls
    var dropdownState : Any = "Oxocarbon Dark"
    
    init {
        themeOptions.forEach {
            if (it.themeName == lafManagerInstance.currentLookAndFeel.name) {
                currentlySelectedTheme = it.themeName
            }
        }
        
        dropdownState = currentlySelectedTheme
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