package com.github.lynxie.oxocarbon.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.util.xmlb.XmlSerializerUtil

class CustomThemeSettings private constructor() : PersistentStateComponent<CustomThemeSettings> {


    override fun getState() = this

    override fun loadState(state: CustomThemeSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: CustomThemeSettings
            get() = ApplicationManager.getApplication().getService(CustomThemeSettings::class.java)
    }
}