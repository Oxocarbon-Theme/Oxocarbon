package com.github.lynxie.oxocarbon.topics

import com.intellij.util.messages.Topic

fun interface DropdownItemChangeListener {

    companion object {
        val DROPDOWN_ITEM_TOPIC : Topic<DropdownItemChangeListener> = Topic.create("Dropdown Item ChangeListener", DropdownItemChangeListener::class.java)
    }

    fun dropdownItemChanged()
}