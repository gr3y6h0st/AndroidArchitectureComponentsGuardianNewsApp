package com.example.guardiannewsapp.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.guardiannewsapp.NetworkUtils;
import com.example.guardiannewsapp.NewsDAO;
import com.example.guardiannewsapp.NewsData;
import com.example.guardiannewsapp.NewsRoomDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

import static com.example.guardiannewsapp.NetworkUtils.getResponseFromHttpUrl;

public class NewsDataRepository {

    private static final String RESULTS = "results";
    private NewsDAO mNewsDao;
    private LiveData<List<NewsData>> mAllNews;

    /**
     * instantiates repository
     * accesses the NewsDAO
     * @param application
     */
    public NewsDataRepository(Application application) {
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application);
        mNewsDao = db.newsDAO();
        mAllNews = mNewsDao.getAllNews();
    }

    public LiveData<List<NewsData>> getAllNews() {
        return mAllNews;
    }

    // get data from webservice
    public void fetchNewsData() {
        new insertAsyncTask(mNewsDao).execute();
    }


    /**
     * The Repositories' AsyncTask Method:
     * Called when ViewModel class calls the repository's public method: fetchNewsData().
     */
    private static class insertAsyncTask extends AsyncTask<Void, Void, ArrayList<NewsData>> {

        private NewsDAO mAsyncTaskDao;

        insertAsyncTask(NewsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected ArrayList<NewsData> doInBackground(Void... voids) {

            try{
                URL NewsDataRequestUrl = NetworkUtils.buildUrl();

                String jsonNewsDatabaseResponse = getResponseFromHttpUrl(NewsDataRequestUrl);

                mAsyncTaskDao.insert(getNewsData(jsonNewsDatabaseResponse).get(0));
                return getNewsData(jsonNewsDatabaseResponse);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<NewsData> newsData) {
            super.onPostExecute(newsData);
            Log.i("READ THIS HERE: ", newsData.get(0).getWEBURL());

        }
    }
    private static ArrayList<NewsData> getNewsData(String json) throws JSONException {
        JSONObject NewsDataData = new JSONObject(json);
        JSONObject response = NewsDataData.getJSONObject("response");
        JSONArray currentNewsData = response.getJSONArray("results");

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
    }
}