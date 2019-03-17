package com.example.guardiannewsapp;

import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //insert your own API KEY
    private static final String API_KEY = BuildConfig.theGuardianApiKey;


    private static final String STATIC_NEWS_DATABASE_URL =
            "https://content.guardianapis.com/search";

    private static final String NEWSDB_BASE_URL = STATIC_NEWS_DATABASE_URL;

    private static final String APPID_PARAM = "api-key";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(NEWSDB_BASE_URL).buildUpon()
                .appendQueryParameter("q", "debates")
                .appendQueryParameter(APPID_PARAM, API_KEY)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /** Method returns entire result from HTTP response.
     *
     * @param url URL to fetch the HTTP response from.
     * @return THe contents of HTTP response.
     * @throws IOException Related to network and stream reading
     * Handles Character encoding. Allocates/deallocates buffers as needed.
     */
    public static String getResponseFromHttpUrl (URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection)  url.openConnection();

        try{
            InputStream input = urlConnection.getInputStream();

            Scanner scanner = new Scanner(input);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
