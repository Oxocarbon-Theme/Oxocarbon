package com.github.lynxie.oxocarbon.notifications

import com.intellij.ide.BrowserUtil
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import org.intellij.lang.annotations.Language
import javax.swing.Icon


class SettingsNotifications {

    companion object {

        private const val NOTIFICATION_GROUP_ID = "Oxocarbon Settings"
        private const val GITHUB_LINK: String = "https://github.com/ImLynxie/Oxocarbon"

        @Language("HTML")
        private const val WELCOME_MESSAGE: String = "<p>Thank you for using Oxocarbon!</p>"

        @Language("HTML")
        private const val LIGHT_MODE_WARNING: String =
            "<p>Oxocarbon Light is currently a work in progress, some things may not look correctly," +
                    " or may not appear at all! If you notice anything that may seem off, please submit a ticket on GitHub!"

        private val pluginIcon: Icon = IconLoader.getIcon("/META-INF/pluginIcon.svg", SettingsNotifications::class.java)

        @Suppress("DialogTitleCapitalization")
        fun notifyWelcome(project: Project) {
            NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID).createNotification(
                "Oxocarbon Installation Successful", WELCOME_MESSAGE, NotificationType.INFORMATION)
                .setIcon(pluginIcon)
                .notify(project)
        }

        fun notifyLightVariantWarning(project: Project) {
            NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID).createNotification(
                "Light mode WIP", LIGHT_MODE_WARNING, NotificationType.WARNING)
                .addAction(NotificationAction.createSimple("GitHub") {BrowserUtil.browse(GITHUB_LINK)})
                .setIcon(pluginIcon)
                .notify(project)
        }
    }
}