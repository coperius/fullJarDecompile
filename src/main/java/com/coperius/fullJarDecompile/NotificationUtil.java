package com.coperius.fullJarDecompile;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;

public class NotificationUtil {
    private static void showNotification(String title, String content, NotificationType type) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("FullJarDecompile Notifications")  // Define a unique group ID
                .createNotification(title, content, type)
                .notify(null);
    }

    public static void Warn(String title, String content) {
        showNotification(title, content, NotificationType.WARNING);
    }

    public static void Info(String title, String content) {
        showNotification(title, content, NotificationType.INFORMATION);
    }

    public static void Error(String title, String content) {
        showNotification(title, content, NotificationType.ERROR);
    }
}
