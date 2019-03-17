package com.example.guardiannewsapp;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news_table")
public class NewsData{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String Results;

    public NewsData(){
    }
    public NewsData(String webUrl, String webTitle){
        this.WEBURL = webUrl;
        this.WEBTITLE = webTitle;
    }

    @ColumnInfo(name = "webTitle")
    private String WEBTITLE;
    @ColumnInfo(name = "webUrl")
    private String WEBURL;


    public String getWEBTITLE(){
        return this.WEBTITLE;
    }

    public String getWEBURL(){
        return this.WEBURL;
    }

    public void setWEBURL(String WEBURL) {
        this.WEBURL = WEBURL;
    }

    public void setWEBTITLE(String WEBTITLE) {
        this.WEBTITLE = WEBTITLE;
    }

    public String getResults() {
        return this.Results;
    }

    public void setResults(String results) {
        Results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
