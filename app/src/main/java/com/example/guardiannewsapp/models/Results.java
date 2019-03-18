package com.example.guardiannewsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news_table")
public class Results {


    @SerializedName("webTitle")
    @Expose
    @ColumnInfo(name = "webTitle")
    private String webTitle;

    @SerializedName("webUrl")
    @Expose
    @ColumnInfo(name = "webUrl")
    @NonNull
    @PrimaryKey
    private String webUrl;


    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        return "Results{" +
                "webTitle='" + webTitle + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
