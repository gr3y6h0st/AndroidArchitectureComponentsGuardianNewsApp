package com.example.guardiannewsapp.retrofit;

import com.example.guardiannewsapp.models.Feed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitEndpoints {

    @GET("/search")
    Call<Feed> getLatestFeed(@Query("order-by") String orderBy,
                                        @Query("api-key") String apiKey);

}
