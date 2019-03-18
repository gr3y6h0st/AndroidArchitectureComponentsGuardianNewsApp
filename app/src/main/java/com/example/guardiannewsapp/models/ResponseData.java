package com.example.guardiannewsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseData {
    @SerializedName("results")
    @Expose
    private ArrayList<Results> results;

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "results=" + results +
                '}';
    }
}
