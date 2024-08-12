package com.github.lynxie.oxocarbon.enums

import com.github.lynxie.oxocarbon.settings.configurable.AppearanceSettingsConfigurable
import com.intellij.openapi.options.Configurable

enum class SettingsCategory(val configurable: Configurable, val id: String) {
    APPEARANCE(AppearanceSettingsConfigurable(), "2ea70210-3f9b-476a-9733-87d1b9864d67")

    ;

    companion object {
        fun getAllConfigurables(): Array<Configurable> {
            val array : Array<Configurable> = emptyArray()
            values().forEach { array[it.ordinal] = it.configurable }
            return array
        }
    }
}