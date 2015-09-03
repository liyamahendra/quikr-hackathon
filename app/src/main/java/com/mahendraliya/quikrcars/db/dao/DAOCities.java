package com.mahendraliya.quikrcars.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.mahendraliya.quikrcars.db.QCDbConstants;
import com.mahendraliya.quikrcars.db.QCSQLiteOpenHelper;
import com.mahendraliya.quikrcars.models.Car;
import com.mahendraliya.quikrcars.models.City;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mahendraliya on 21/08/15.
 */
public class DAOCities {
    protected QCSQLiteOpenHelper dbHelper;

    public DAOCities(Context context) {
        dbHelper = QCSQLiteOpenHelper.getInstance(context);
    }

    public void saveAllCities(int carID, JSONArray arrCities) {

        dbHelper.getDatabase().beginTransaction();
        SQLiteStatement insert = dbHelper.getDatabase().compileStatement(QCDbConstants.Cities.QUERY_INSERT);

        for (int i=0; i<arrCities.length(); i++) {
            try {
                JSONObject city = arrCities.getJSONObject(i);
                insert.bindString(1, String.valueOf(carID));
                insert.bindString(2, city.getString("city"));
                insert.bindString(3, city.getString("users"));
                insert.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dbHelper.getDatabase().setTransactionSuccessful();

        dbHelper.getDatabase().endTransaction();
    }

    public ArrayList<City> getAllCities(Context context, int carID) {

        Cursor cursor = dbHelper.getDatabase().rawQuery(QCDbConstants.Cities.getAllCarCities(carID), null);
        ArrayList<City> arrCities = new ArrayList<City>();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            City car = new City();
            car.setId(cursor.getInt(cursor.getColumnIndex(QCDbConstants.Cities.COLUMN_ID)));
            car.setCarID(carID);
            car.setCity(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cities.COLUMN_CITY_NAME)));
            car.setUsers(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cities.COLUMN_USERS)));

            arrCities.add(car);

            cursor.moveToNext();
        }

        if(cursor != null && !cursor.isClosed())
            cursor.close();

        return arrCities;
    }
}
