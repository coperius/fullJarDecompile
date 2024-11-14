package com.coperius.fullJarDecompiler;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

public class NotificationUtil {
    private static void showNotification(Project project, String title, String content, NotificationType type) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("FullJarDecompiler Notifications")  // Define a unique group ID
                .createNotification(title, content, type)
                .notify(project);
    }

    public static void Warn(Project project, String title, String content) {
        showNotification(project, title, content, NotificationType.WARNING);
    }

    public static void Info(Project project, String title, String content) {
        showNotification(project, title, content, NotificationType.INFORMATION);
    }

    public static void Error(Project project, String title, String content) {
        showNotification(project, title, content, NotificationType.ERROR);
    }
}
