package com.github.lynxie.oxocarbon.enums

import com.github.lynxie.oxocarbon.settings.configurable.AppearanceSettingsConfigurable
import com.github.lynxie.oxocarbon.settings.configurable.CustomThemeSettingsConfigurable
import com.github.lynxie.oxocarbon.settings.configurable.OxocarbonParentSettingsConfigurable
import com.intellij.openapi.options.Configurable

enum class SettingsCategory(val configurable: Configurable, val categoryName: String, val id: String) {
    PARENT(OxocarbonParentSettingsConfigurable(), "Oxocarbon Settings", "6f004dfd-8730-4721-90c6-92d762bd0ffd"),
    APPEARANCE(AppearanceSettingsConfigurable(), "Appearance","2ea70210-3f9b-476a-9733-87d1b9864d67"),
    CUSTOM_THEME(CustomThemeSettingsConfigurable(), "Custom Theme","1c3ed063-89a6-463e-95ab-358b5fdafa95")

    ;

    companion object {
        fun getAllConfigurables(): Array<Configurable> {
            val array : Array<Configurable> = emptyArray()
            entries.forEach { array[it.ordinal] = it.configurable }
            return array
        }

        @OptIn(ExperimentalStdlibApi::class)
        fun getSettingsCategoryByName(name: String): SettingsCategory {
            return entries.first { it.categoryName == name }
        }
    }
}