package com.coperius.fullJarDecompile;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.JarFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.impl.RootBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.java.decompiler.IdeaDecompiler;

public class LegalNotice {

    private static final String LEGAL_NOTICE_KEY = "decompiler.legal.notice.accepted";

    public static boolean isAccepted() {
        return PropertiesComponent.getInstance().isValueSet(LEGAL_NOTICE_KEY);
    }

    public static void ShowOriginalNotice(Project project) throws Exception {

        if (isAccepted()) {
            return;
        }

        // find the IdeaDecompiler.LegalBurden subscriber
        var messageBus = ApplicationManager.getApplication().getMessageBus();
        if (!(messageBus instanceof RootBus root)) {
            throw new Exception("RootBus not found");
        }

        FileEditorManagerListener.Before subscriber = null;
        var subscribers = root.computeSubscribers$intellij_platform_core(FileEditorManagerListener.Before.FILE_EDITOR_MANAGER);
        for (var sub : subscribers) {
            if (sub instanceof IdeaDecompiler.LegalBurden) {
                subscriber = (FileEditorManagerListener.Before) sub;
                break;
            }
        }

        if (subscriber == null) {
            return;
        }

        // we need any jar class file to trigger the decompiler
        // get self resource file
        var selfClassFile = getSelfClassFile();
        subscriber.beforeFileOpened(FileEditorManager.getInstance(project), selfClassFile);

    }

    private static @NotNull VirtualFile getSelfClassFile() throws Exception {
        var selfClassName = "/" + LegalNotice.class.getName().replace('.', '/') + ".class";

        var res = LegalNotice.class.getResource(selfClassName);
        if (res == null) {
            throw new Exception("resource not found " + selfClassName);
        }

        var path = res.getPath();
        if (path == null) {
            throw new Exception("resource not found " + selfClassName);
        }
        // remove prefix "file:" if exists
        if (path.startsWith("file:")) {
            path = path.substring(5);
        }
        // get JarFileSystem
        var jarFileSystem = JarFileSystem.getInstance();
        var jarFile = jarFileSystem.findFileByPath(path);
        if (jarFile == null) {
            throw new Exception("not a jar " + path);
        }
        return jarFile;
    }

}
