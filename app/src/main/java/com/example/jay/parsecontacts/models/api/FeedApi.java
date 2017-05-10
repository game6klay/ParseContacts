package com.example.jay.parsecontacts.models.api;

import com.example.jay.parsecontacts.models.pojo.Contacts;
import com.example.jay.parsecontacts.models.pojo.FeedResponse;
import com.example.jay.parsecontacts.models.utils.Constants;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by jay on 5/8/17.
 */

public interface FeedApi {

    @GET("/")
    void getItems(Callback<FeedResponse> responseCallback);
}
