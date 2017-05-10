package com.example.jay.parsecontacts.controllers;

import android.util.Log;

import com.example.jay.parsecontacts.models.api.RestApiManager;
import com.example.jay.parsecontacts.models.pojo.Contacts;
import com.example.jay.parsecontacts.models.pojo.FeedResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jay on 5/8/17.
 */

public class Controller {

    private static final String TAG = Controller.class.getSimpleName();
    private FeedCallbackListener mListener;
    private RestApiManager mApiManager;

    public Controller(FeedCallbackListener listener) {
        mListener = listener;
        mApiManager = new RestApiManager();
    }

    public void startFetching() {

        mApiManager.getFeedApi().getItems(new Callback<FeedResponse>() {
            @Override
            public void success(FeedResponse feedResponse, Response response) {
                Log.i(TAG,response.toString());

                List<Contacts> contactsList = feedResponse.getContacts();
                mListener.onFetchProgress(contactsList);
                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });
    }

    public interface FeedCallbackListener {
        void onFetchStart();
        void onFetchProgress(Contacts contacts);
        void onFetchProgress(List<Contacts> contactsList);
        void onFetchComplete();
        void onFetchFailed();
        void onItemClick(int p);
    }
}
