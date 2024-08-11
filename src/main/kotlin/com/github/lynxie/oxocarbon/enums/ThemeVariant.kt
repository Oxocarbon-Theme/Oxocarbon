package com.github.lynxie.oxocarbon.enums

enum class ThemeVariant(val themeName: String) {
    DARK("Oxocarbon Dark"),
    LIGHT("Oxocarbon Light"), 
    ;
    
    override fun toString(): String {
        return themeName
    }

    companion object {
        fun getThemeVariantWithName(name: String): ThemeVariant {
            return values().first { it.themeName == name }
        }
    }
}