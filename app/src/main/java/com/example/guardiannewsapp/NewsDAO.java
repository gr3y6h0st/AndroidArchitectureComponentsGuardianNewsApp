package com.example.guardiannewsapp;

import com.example.guardiannewsapp.models.Results;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NewsDAO {

    @Insert
    void insert(Results results);

    @Query("DELETE FROM news_table")
    void deleteAll();

    @Query("SELECT * from news_table ORDER BY webTitle ASC")
    LiveData<List<Results>> getAllNews();
}

