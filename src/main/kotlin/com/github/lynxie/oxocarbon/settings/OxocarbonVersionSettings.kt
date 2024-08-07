package com.github.lynxie.oxocarbon.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "OxocarbonVersionSettings", storages = [(Storage("OxocarbonSettings.xml"))])
class OxocarbonVersionSettings private constructor() : PersistentStateComponent<OxocarbonVersionSettings> {

    var version: String? = this.state.version

    override fun getState() = this

    override fun loadState(versionSettings: OxocarbonVersionSettings) {
        XmlSerializerUtil.copyBean(versionSettings, this)
    }

    companion object {
        val instance: OxocarbonVersionSettings
            get() = ApplicationManager.getApplication().getService(OxocarbonVersionSettings::class.java)
    }
}