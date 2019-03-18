package com.example.guardiannewsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feed {

    @SerializedName("response")
    @Expose
    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "responseData=" + responseData +
                '}';
    }
}
