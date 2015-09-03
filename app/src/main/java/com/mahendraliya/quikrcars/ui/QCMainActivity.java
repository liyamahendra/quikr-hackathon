package com.mahendraliya.quikrcars.ui;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.mahendraliya.quikrcars.R;
import com.mahendraliya.quikrcars.api.GetAllCarsTask;
import com.mahendraliya.quikrcars.api.GetApiHitsTask;
import com.mahendraliya.quikrcars.api.ITaskResponse;
import com.mahendraliya.quikrcars.db.QCDbConstants;
import com.mahendraliya.quikrcars.db.adapters.CarsAdapter;
import com.mahendraliya.quikrcars.db.dao.DAOCars;
import com.mahendraliya.quikrcars.models.Car;
import com.mahendraliya.quikrcars.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class QCMainActivity extends QCBaseActivity {

    private DAOCars daoCars;
    private ArrayList<Car> arrCars;
    private CarsAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvCarsList;

    private ITaskResponse fetchAllCarsCallback = new ITaskResponse() {
        @Override
        public void onSuccess(String response) {
            try {
                daoCars.saveAllCars(new JSONArray(response));
            } catch(Exception ex) {
                ex.printStackTrace();
            } finally {
                displayNewsFromDb(null);
            }
        }

        @Override
        public void onFailure(String response) {
            showToastLong(new StringBuilder("Failed to get the cars from the server, please try again. (Reason: ").append(response).append(")").toString());
        }
    };

    private ITaskResponse getApiHitsCallback = new ITaskResponse() {
        @Override
        public void onSuccess(String response) {
            try {
                String apiHits = new JSONObject(response).getString("api_hits");
                StringBuilder message = new StringBuilder("The total API Hits till now are: ").append(apiHits);
                message.append("\n");
                message.append("And Total Car count is: ").append(arrCars.size());
                showToastLong(message.toString());
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void onFailure(String response) {
            showToastLong(new StringBuilder("Failed to get the cars from the server, please try again. (Reason: ").append(response).append(")").toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoCars = new DAOCars(this);

        setupLayout();
        setupEventListeners();
    }

    protected void setupLayout() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        rvCarsList = (RecyclerView) findViewById(R.id.rvCarsList);
        rvCarsList.setHasFixedSize(true);
        rvCarsList.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        fetchCarFromServer();
    }

    private void setupEventListeners() {
        Events events = new Events();
        swipeRefreshLayout.setOnRefreshListener(events.refreshFeedsListener);
    }

    private class Events {

        SwipeRefreshLayout.OnRefreshListener refreshFeedsListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchCarFromServer();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_get_api_hits:
                getApiHits();
                return true;

            case R.id.action_sort_by_price:
                mAdapter = new CarsAdapter(QCMainActivity.this, daoCars.getAllCars(QCMainActivity.this, QCDbConstants.Cars.getQueryForCarsSortedByPrice()));
                rvCarsList.setAdapter(mAdapter);
                return true;

            case R.id.action_sort_by_rating:
                mAdapter = new CarsAdapter(QCMainActivity.this, daoCars.getAllCars(QCMainActivity.this, QCDbConstants.Cars.getQueryForCarsSortedByRating()));
                rvCarsList.setAdapter(mAdapter);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void fetchCarFromServer() {
        if(!Utils.isNetworkAvailable(this)) {
            showAlertWithTitleAndMessage(this, getString(R.string.app_name), getString(R.string.error_message_internet_not_available));
            return;
        }

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        GetAllCarsTask getAllCarsTask = new GetAllCarsTask(this, fetchAllCarsCallback);
        getAllCarsTask.execute();
    }

    private void displayNewsFromDb(String query) {
        swipeRefreshLayout.setRefreshing(false);
        arrCars = daoCars.getAllCars(this, query);
        mAdapter = new CarsAdapter(this, arrCars);
        rvCarsList.setAdapter(mAdapter);
    }

    private void getApiHits() {
        if(!Utils.isNetworkAvailable(QCMainActivity.this)) {
            showAlertWithTitleAndMessage(QCMainActivity.this, getString(R.string.app_name), getString(R.string.error_message_internet_not_available));
            return;
        }

        GetApiHitsTask getApiHitsTask = new GetApiHitsTask(QCMainActivity.this, getApiHitsCallback);
        getApiHitsTask.execute();
    }
}
