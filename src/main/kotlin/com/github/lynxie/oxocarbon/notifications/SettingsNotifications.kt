package com.github.lynxie.oxocarbon.notifications

import com.intellij.ide.BrowserUtil
import com.intellij.notification.Notification
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


        private val NOTIFICATION_GROUP = NotificationGroupManager.getInstance().getNotificationGroup(
            NOTIFICATION_GROUP_ID
        )

        private val pluginIcon: Icon = IconLoader.getIcon("/META-INF/pluginIcon.svg", SettingsNotifications::class.java)

        @Suppress("DialogTitleCapitalization")
        fun notifyWelcome(project: Project) {
            val notification = NOTIFICATION_GROUP.createNotification(
                title = "Oxocarbon Installation Successful",
                WELCOME_MESSAGE,
                NotificationType.INFORMATION
            )
            addNotificationActions(notification)
            notification.setIcon(pluginIcon)
            notification.notify(project)
        }

        @Suppress("DialogTitleCapitalization")
        fun notifyLightVariantWarning(project: Project) {
            val notification = NOTIFICATION_GROUP.createNotification(
                "Oxocarbon Light Mode Notice",
                LIGHT_MODE_WARNING,
                NotificationType.WARNING
            )
            addNotificationActions(notification)
            notification.setIcon(pluginIcon)
            notification.notify(project)
        }

        private fun addNotificationActions(notification: Notification) {
            notification.addAction(NotificationAction.createSimple("GitHub") { BrowserUtil.browse(GITHUB_LINK) })
        }
    }
}