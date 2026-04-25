package com.example.spitifi.data.local;

import android.view.ViewDebug;

import androidx.room.Dao;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao

public interface AudioDao {
    @Insert
    void insert(AudioEntity audio);

    @Query("Select * from audio_files order by createdAt DESC")
    List<AudioEntity> getAll();
    @Query("Select * from audio_files where tag = :tag order by createdAt desc")
    List<AudioEntity> getByTag(String tag);
    @Query("SELECT * FROM audio_files WHERE date(createdAt/1000, 'unixepoch') = date(:time/1000, 'unixepoch')")
    List<AudioEntity> getByDate(long time);
}
