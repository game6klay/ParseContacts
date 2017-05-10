package com.example.jay.parsecontacts.models.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by jay on 5/8/17.
 */

public class RestApiManager {
    private FeedApi mFeedApi;

    public FeedApi getFeedApi() {

        if(mFeedApi == null) {
            Gson gson = new GsonBuilder()
                    .create();
            mFeedApi = new RestAdapter.Builder()
                    .setEndpoint("http://api.androidhive.info/contacts/")
                    .setConverter(new GsonConverter(gson))
                    .build()
                    .create(FeedApi.class);
        }
        return mFeedApi;
    }

}
