package com.example.guardiannewsapp;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NewsDAO {

    @Insert
    void insert(NewsData newsData);

    @Query("DELETE FROM news_table")
    void deleteAll();

    @Query("SELECT * from news_table ORDER BY webTitle ASC")
    LiveData<List<NewsData>> getAllNews();
}

