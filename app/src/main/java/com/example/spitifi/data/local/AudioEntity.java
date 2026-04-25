package com.example.spitifi.data.local;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "audio_files")
public class AudioEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String filePath;
    public String thumbnailPath; // ảnh video
    public String tag;           // audio | truyện ngắn | nhạc
    public long createdAt;
    public long size;

    public AudioEntity(String title, String filePath, long createdAt, long size) {
        this.title = title;
        this.filePath = filePath;
        this.createdAt = createdAt;
        this.size = size;
    }
}
