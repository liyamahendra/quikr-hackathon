package com.mahendraliya.quikrcars.db.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mahendraliya.quikrcars.R;
import com.mahendraliya.quikrcars.models.Car;
import com.mahendraliya.quikrcars.ui.QCCarDetailsActivity;
import com.mahendraliya.quikrcars.utils.Constants;

import java.util.ArrayList;

/**
 * Created by mahendraliya on 21/08/15.
 */
public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {

    private ArrayList<Car> mData;
    private Context mContext;
    private boolean isOnBindCall;

    public CarsAdapter(Context context, ArrayList<Car> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car_card, parent, false);
        CarViewHolder vh = new CarViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, final int position) {

        Glide.with(mContext).load(mData.get(position).getImageUrl()).placeholder(R.drawable.placeholder_car).into(holder.imgCar);

        holder.lblCarName.setText(mData.get(position).getName());
        holder.lblBrandAndType.setText(new StringBuilder(mData.get(position).getBrand()).append(", ").append(mData.get(position).getType()));
        holder.lblDescription.setText(mData.get(position).getDescription());
        holder.lblPrice.setText(mData.get(position).getPrice());

        holder.ratingBar.setRating(mData.get(position).getRating());

        ((View) holder.lblCarName.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCarDetails(mData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCar;
        private TextView lblCarName, lblBrandAndType, lblDescription, lblPrice;
        private RatingBar ratingBar;

        public CarViewHolder(View v) {
            super(v);
            setupLayout(v);
        }

        private void setupLayout(View v) {
            imgCar = (ImageView)v.findViewById(R.id.imgCar);
            lblCarName = (TextView)v.findViewById(R.id.lblCarName);
            lblBrandAndType = (TextView)v.findViewById(R.id.lblBrandAndType);
            lblDescription = (TextView)v.findViewById(R.id.lblDescription);
            lblPrice = (TextView)v.findViewById(R.id.lblPrice);
            ratingBar = (RatingBar)v.findViewById(R.id.ratingBar);
        }
    }

    private void showCarDetails(Car car) {
        Intent showCarDetailsIntent = new Intent(mContext, QCCarDetailsActivity.class);

        showCarDetailsIntent.putExtra(Constants.EXTRA_IMAGE_URL, car.getImageUrl());
        showCarDetailsIntent.putExtra(Constants.EXTRA_NAME, car.getName());
        showCarDetailsIntent.putExtra(Constants.EXTRA_DESCRIPTION, car.getDescription());
        showCarDetailsIntent.putExtra(Constants.EXTRA_PRICE, car.getPrice());
        showCarDetailsIntent.putExtra(Constants.EXTRA_RATING, car.getRating());
        showCarDetailsIntent.putExtra(Constants.EXTRA_BRAND, car.getBrand());
        showCarDetailsIntent.putExtra(Constants.EXTRA_TYPE, car.getType());
        showCarDetailsIntent.putExtra(Constants.EXTRA_ENGINE_CC, car.getEngineCC());
        showCarDetailsIntent.putExtra(Constants.EXTRA_MILEAGE, car.getMileage());
        showCarDetailsIntent.putExtra(Constants.EXTRA_ABS_EXISTS, car.isAbsExists());
        showCarDetailsIntent.putExtra(Constants.EXTRA_COLOR, car.getColor());
        showCarDetailsIntent.putExtra(Constants.EXTRA_LINK, car.getLink());

        mContext.startActivity(showCarDetailsIntent);
    }
}
