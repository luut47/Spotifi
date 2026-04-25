package com.example.spitifi.data.model;

public class AudioFile {
    private String title;
    private String filePath;
    private long createdAt;
    private long size;

    public AudioFile(String title, String filePath, long createdAt, long size) {
        this.title = title;
        this.filePath = filePath;
        this.createdAt = createdAt;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getSize() {
        return size;
    }
}
