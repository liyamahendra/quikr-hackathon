package com.mahendraliya.quikrcars.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.mahendraliya.quikrcars.R;
import com.mahendraliya.quikrcars.api.GetAllCarsTask;
import com.mahendraliya.quikrcars.api.GetApiHitsTask;
import com.mahendraliya.quikrcars.api.ITaskResponse;
import com.mahendraliya.quikrcars.db.QCDbConstants;
import com.mahendraliya.quikrcars.db.adapters.CarsAdapter;
import com.mahendraliya.quikrcars.db.dao.DAOCars;
import com.mahendraliya.quikrcars.models.Car;
import com.mahendraliya.quikrcars.utils.Constants;
import com.mahendraliya.quikrcars.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class QCCarDetailsActivity extends QCBaseActivity {

    private FloatingActionButton fabShare;
    private String shareMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        setupLayout();
        setupEventListeners();
    }

    protected void setupLayout() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.screen_title_car_details));

        fabShare = (FloatingActionButton) findViewById(R.id.fabShare);

        ImageView imgCar = (ImageView) findViewById(R.id.imgCar);
        TextView lblPrice = (TextView) findViewById(R.id.lblPrice);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        TextView lblCarName = (TextView) findViewById(R.id.lblCarName);
        TextView lblDescription = (TextView) findViewById(R.id.lblDescription);
        TextView lblBrand = (TextView) findViewById(R.id.lblBrand);
        TextView lblType = (TextView) findViewById(R.id.lblType);
        TextView lblEngineCC = (TextView) findViewById(R.id.lblEngineCC);
        TextView lblMileage = (TextView) findViewById(R.id.lblMileage);
        TextView lblAbsExists = (TextView) findViewById(R.id.lblAbsExists);
        View vCarColor = findViewById(R.id.vCarColor);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            shareMessage = extras.getString(Constants.EXTRA_LINK);
            Glide.with(this).load(extras.getString(Constants.EXTRA_IMAGE_URL)).placeholder(R.drawable.placeholder_car).into(imgCar);

            lblPrice.setText(extras.getString(Constants.EXTRA_PRICE));
            lblCarName.setText(extras.getString(Constants.EXTRA_NAME));
            lblDescription.setText(extras.getString(Constants.EXTRA_DESCRIPTION));
            lblBrand.setText(extras.getString(Constants.EXTRA_BRAND));
            lblType.setText(extras.getString(Constants.EXTRA_TYPE));
            lblEngineCC.setText(extras.getString(Constants.EXTRA_ENGINE_CC));
            lblMileage.setText(extras.getString(Constants.EXTRA_MILEAGE));
            lblAbsExists.setText((extras.getBoolean(Constants.EXTRA_ABS_EXISTS)) ? "Yes" : "No");

            ratingBar.setRating(extras.getFloat(Constants.EXTRA_RATING));
            vCarColor.setBackgroundColor(Color.parseColor(extras.getString(Constants.EXTRA_COLOR)));
        }

    }

    private void setupEventListeners() {
        Events events = new Events();
        fabShare.setOnClickListener(events.shareClickListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Here 'home' is the 'back' button
        if(item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private class Events {
        View.OnClickListener shareClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.share(QCCarDetailsActivity.this, shareMessage);
            }
        };
    }
}
