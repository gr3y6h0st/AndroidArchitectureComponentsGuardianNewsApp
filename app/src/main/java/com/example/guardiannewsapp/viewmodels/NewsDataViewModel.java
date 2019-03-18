package com.example.guardiannewsapp.viewmodels;

import com.example.guardiannewsapp.models.Results;
import com.example.guardiannewsapp.repositories.NewsDataRepository;

import java.util.List;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsDataViewModel extends ViewModel {

    private MutableLiveData<List<Results>> mAllNewsData;
    private NewsDataRepository mRepository;

    //retrieve the data from the REPOSITORY class, by populating the Database
    public void init() {
        if(mAllNewsData != null){
            return;
        }
        mRepository = NewsDataRepository.getInstance();
        mAllNewsData  = mRepository.fetchNewsData();
    }

    public MutableLiveData<List<Results>> getAllNews() {
        return mAllNewsData; }
}
