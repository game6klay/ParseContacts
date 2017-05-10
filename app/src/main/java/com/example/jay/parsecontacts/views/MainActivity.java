package com.example.jay.parsecontacts.views;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.jay.parsecontacts.R;
import com.example.jay.parsecontacts.controllers.Controller;
import com.example.jay.parsecontacts.models.adapters.FeedAdapter;
import com.example.jay.parsecontacts.models.pojo.Contacts;
import com.example.jay.parsecontacts.models.pojo.FeedResponse;
import com.example.jay.parsecontacts.models.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Controller.FeedCallbackListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;

    //private FeedResponse response = new FeedResponse();
    List<Contacts> list = new ArrayList<>();
    private FeedAdapter mFeedAdapter;
    private Controller mController;

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_ID = "EXTRA_ID";
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_EMAIL = "EXTRA_EMAIL";
    private static final String EXTRA_GENDER = "EXTRA_GENDER";
    private static final String EXTRA_PHONE = "EXTRA_PHONE";
    private static final String EXTRA_ADDRESS = "EXTRA_ADDRESS";
    private static final String EXTRA_IMAGE_RES_ID = "EXTRA_IMAGE_RES_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configToolbar();
        mController = new Controller(MainActivity.this);
        configViews();
        mController.startFetching();
    }

    private void configToolbar(){
        setSupportActionBar(mToolbar);
    }

    private void configViews() {
        ButterKnife.bind(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());


        mFeedAdapter = new FeedAdapter(this, list);
        mRecyclerView.setAdapter(mFeedAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.startFetching();
            }
        });
    }


    @Override
    public void onFetchStart() {
    }

    @Override
    public void onFetchProgress(Contacts contact) {
        mFeedAdapter.addItem(contact);
    }

    @Override
    public void onFetchProgress(List<Contacts> list) {
        //List<Contacts> contactsList = items.();
        mFeedAdapter.addItems(list);
    }

    @Override
    public void onFetchComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }

    @Override
    public void onItemClick(int p) {

        String name = "NAME";

        Contacts contact = list.get(p);
        Intent i = new Intent(this, DetailsActivity.class);
        Bundle extras = new Bundle();
        //Bundle extras1 = new Bundle();
        i.putExtra(EXTRA_PHONE,new Gson().toJson(contact.getPhone()));
        extras.putString(EXTRA_ID, contact.getId());
        extras.putString(EXTRA_NAME, contact.getName());
        extras.putString(EXTRA_EMAIL,  contact.getEmail());
        extras.putString(EXTRA_GENDER, ""+ contact.getGender());
        extras.putString(EXTRA_ADDRESS, ""+ contact.getAddress());
        extras.putString(EXTRA_IMAGE_RES_ID, Constants.PHOTO_URL);
        i.putExtra(BUNDLE_EXTRAS, extras);
        startActivity(i);

    }
}
