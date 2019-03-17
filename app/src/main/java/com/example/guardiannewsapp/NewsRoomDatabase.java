package com.example.guardiannewsapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NewsData.class}, version = 1)
public abstract class NewsRoomDatabase extends RoomDatabase {
    public abstract NewsDAO newsDAO();

    private static volatile NewsRoomDatabase INSTANCE;

    /**
     * builds database called "news_database" using Room's databaseBuilder
     * and gets access to a new SQLite database
     * returns the instance of NewsRoomDatabase
     */
    public static NewsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsRoomDatabase.class, "news_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
