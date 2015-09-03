package com.mahendraliya.quikrcars.models;

/**
 * Created by mahendraliya on 21/08/15.
 */
public class City {
    private int id;
    private int carID;
    private String city;
    private String users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
