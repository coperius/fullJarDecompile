package com.coperius.fullJarDecompile;

class Entry {
    private final String path;
    private final java.util.List<Entry> children = new java.util.ArrayList<>();
    private final boolean isDirectory;
    private String content = null;

    public Entry(String path, boolean isDirectory) {
        this.path = path;
        this.isDirectory = isDirectory;
    }

    public void addChild(Entry child) {
        children.add(child);
    }

    public String getPath() {
        return path;
    }

    public java.util.List<Entry> getChildren() {
        return children;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
