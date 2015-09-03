package com.mahendraliya.quikrcars.models;

import com.mahendraliya.quikrcars.R;

import java.util.ArrayList;

/**
 * Created by mahendraliya on 21/08/15.
 */
public class Car {
    private int id;
    private String name;
    private String imageUrl;
    private float price;
    private String brand;
    private String type;
    private float rating;
    private String color;
    private String engineCC;
    private String mileage;
    private boolean absExists;
    private String description;
    private String link;
    private ArrayList<City> cities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return new StringBuilder("\u20B9 ").append(price).append("L").toString();
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineCC() {
        return engineCC;
    }

    public void setEngineCC(String engineCC) {
        this.engineCC = engineCC;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public boolean isAbsExists() {
        return absExists;
    }

    public void setAbsExists(boolean absExists) {
        this.absExists = absExists;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
