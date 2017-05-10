package com.example.jay.parsecontacts.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jay.parsecontacts.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jay on 5/8/17.
 */

public class DetailsActivity extends AppCompatActivity {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_ID = "EXTRA_ID";
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_EMAIL = "EXTRA_EMAIL";
    private static final String EXTRA_GENDER = "EXTRA_GENDER";
    private static final String EXTRA_PHONE = "EXTRA_PHONE";
    private static final String EXTRA_ADDRESS = "EXTRA_ADDRESS";
    private static final String EXTRA_IMAGE_RES_ID = "EXTRA_IMAGE_RES_ID";

    @BindView(R.id.detail_id)
    TextView tvId;

    @BindView(R.id.detail_name)
    TextView tvName;

    @BindView(R.id.detail_email)
    TextView tvEmail;

    @BindView(R.id.detail_gender)
    TextView tvGender;

    @BindView(R.id.detail_mobile)
    TextView tvMobile;

    @BindView(R.id.detail_address)
    TextView tvAddress;

    @BindView(R.id.detail_home)
    TextView tvHome;

    @BindView(R.id.detail_office)
    TextView tvOffice;

    @BindView(R.id.detail_image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        ButterKnife.bind(this);

        tvId.setText(extras.getString(EXTRA_ID));
        tvName.setText(extras.getString(EXTRA_NAME));
        tvEmail.setText(extras.getString(EXTRA_EMAIL));
        tvGender.setText(extras.getString(EXTRA_GENDER));
        tvAddress.setText(extras.getString(EXTRA_ADDRESS));

        Picasso.with(this)
                .load(extras.getString(EXTRA_IMAGE_RES_ID))
                .centerCrop()
                .fit()
                .into(imageView);

    }

}
