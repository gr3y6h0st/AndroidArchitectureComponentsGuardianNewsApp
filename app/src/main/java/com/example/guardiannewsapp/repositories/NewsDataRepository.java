package com.example.guardiannewsapp.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.guardiannewsapp.BuildConfig;
import com.example.guardiannewsapp.NewsDAO;
import com.example.guardiannewsapp.NewsRoomDatabase;
import com.example.guardiannewsapp.models.Feed;
import com.example.guardiannewsapp.models.Results;
import com.example.guardiannewsapp.retrofit.RetrofitClientInstance;
import com.example.guardiannewsapp.retrofit.RetrofitEndpoints;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Singleton Pattern
 */
public class NewsDataRepository {

    private static final String TAG = NewsDataRepository.class.getSimpleName();

    private NewsDAO mNewsDao;

    private ArrayList<Results> mResults = new ArrayList<>();
    private static NewsDataRepository instance;
    private LiveData<List<Results>> mAllNews;

    public static NewsDataRepository getInstance(){
        if(instance == null){
            instance = new NewsDataRepository();
        }
        return instance;
    }

    /**
     * instantiates repository
     * accesses the NewsDAO
     * @param application
    public NewsDataRepository(Application application) {
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application);
        mNewsDao = db.newsDAO();
        mAllNews = mNewsDao.getAllNews();
    }

    public LiveData<List<Results>> getAllNews() {
        return mAllNews;
    }*/

    // get data from webservice
    public MutableLiveData<List<Results>> fetchNewsData() {
        final MutableLiveData<List<Results>> mutableAllNews = new MutableLiveData<>();
        /*Create handle for the RetrofitInstance interface*/
        RetrofitEndpoints service = RetrofitClientInstance.getRetrofitInstance().create(RetrofitEndpoints.class);

        Call<Feed> call = service.getLatestFeed("newest", BuildConfig.theGuardianApiKey);
        call.enqueue(new Callback<Feed>() {

            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.i(TAG, "onResponse: received information: "  + response.body().toString() + "\n");
                Log.i(TAG, "onInsert: going to add to DAO: " + response.body().getResponseData().getResults().get(0).toString());
                //insert results object into DAO to populate db.
                mResults = response.body().getResponseData().getResults();
                mutableAllNews.setValue(mResults);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e("SORRY ERROR OCCURED: ", t.getMessage());
            }
        });
        return mutableAllNews;
    }


    /**
     * The Repositories' AsyncTask Method:
     * Called when ViewModel class calls the repository's public method: fetchNewsData().
     */
    /*private static class insertAsyncTask extends AsyncTask<Void, Void, ArrayList<Results>> {

        private NewsDAO mAsyncTaskDao;
        private ArrayList<Results> mResultsArrayList;


        insertAsyncTask(NewsDAO dao, ArrayList<Results> results) {
            mAsyncTaskDao = dao;
            mResultsArrayList = results;
        }

        @Override
        protected ArrayList<Results> doInBackground(Void... voids) {

            try{
                mAsyncTaskDao.insert(mResultsArrayList.get(0));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return mResultsArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Results> results) {
            super.onPostExecute(results);

            Log.i("ASYNCTASK POSTEXECUTE: ", results.get(0).getWebTitle());

        }
    }
    private static ArrayList<NewsData> getNewsData(String json) throws JSONException {
        JSONObject NewsDataData = new JSONObject(json);
        JSONObject response = NewsDataData.getJSONObject("response");
        JSONArray currentNewsData = response.getJSONArray("Results");

        ArrayList<NewsData> NewsDataArray = new ArrayList<NewsData>();
        for(int i = 0; i < currentNewsData.length(); i++){
            JSONObject currentNewsItem = currentNewsData.getJSONObject(i);

            NewsData newsDataSpecifics = new NewsData(
                    currentNewsItem.getString("webUrl"),
                    currentNewsItem.getString("webTitle")

            );
            NewsDataArray.add(newsDataSpecifics);
            Log.v("READ ", NewsDataArray.get(i).getWEBTITLE());
        }
        return NewsDataArray;
    }*/
}