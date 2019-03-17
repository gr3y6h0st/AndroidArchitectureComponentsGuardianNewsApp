package com.example.guardiannewsapp.viewmodels;

import android.app.Application;

import com.example.guardiannewsapp.NewsData;
import com.example.guardiannewsapp.repositories.NewsDataRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NewsDataViewModel extends AndroidViewModel {

    private LiveData<List<NewsData>> mAllNewsData;

    //retrieve the data from the REPOSITORY class, by populating the Database.
    public NewsDataViewModel(@NonNull Application application) {
        super(application);
        NewsDataRepository mRepository = new NewsDataRepository(application);
        mRepository.fetchNewsData();
        mAllNewsData = mRepository.getAllNews();
    }

    public LiveData<List<NewsData>> getAllNews() {
        return mAllNewsData; }
}
