package com.mahendraliya.quikrcars.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.mahendraliya.quikrcars.db.QCDbConstants;
import com.mahendraliya.quikrcars.db.QCSQLiteOpenHelper;
import com.mahendraliya.quikrcars.models.Car;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mahendraliya on 21/08/15.
 */
public class DAOCars {
    private QCSQLiteOpenHelper dbHelper;
    private DAOCities daoCities;

    public DAOCars(Context context) {
        dbHelper = QCSQLiteOpenHelper.getInstance(context);
        daoCities = new DAOCities(context);
    }

    public void saveAllCars(JSONArray arrCars) {

        dbHelper.clearOldData(dbHelper.getDatabase());

        dbHelper.getDatabase().beginTransaction();
        SQLiteStatement insert = dbHelper.getDatabase().compileStatement(QCDbConstants.Cars.QUERY_INSERT);

        for (int i=0; i<arrCars.length(); i++) {
            try {
                JSONObject car = arrCars.getJSONObject(i);
                insert.bindString(1, String.valueOf(i));
                insert.bindString(2, car.getString("name"));
                insert.bindString(3, car.getString("image"));
                insert.bindString(4, car.getString("price"));
                insert.bindString(5, car.getString("brand"));
                insert.bindString(6, car.getString("type"));
                insert.bindString(7, car.getString("rating"));
                insert.bindString(8, car.getString("color"));
                insert.bindString(9, car.getString("engine_cc"));
                insert.bindString(10, car.getString("mileage"));
                insert.bindString(11, String.valueOf((car.getString("abs_exist").equalsIgnoreCase("yes")) ? 1 : 0));
                insert.bindString(12, car.getString("description"));
                insert.bindString(13, car.getString("link"));
                insert.execute();

                // save the cities
                daoCities.saveAllCities(i, car.getJSONArray("cities"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dbHelper.getDatabase().setTransactionSuccessful();

        dbHelper.getDatabase().endTransaction();
    }

    public ArrayList<Car> getAllCars(Context context, String query) {

        if(query == null) {
            query = QCDbConstants.Cars.QUERY_SELECT;
        }

        Cursor cursor = dbHelper.getDatabase().rawQuery(query, null);
        ArrayList<Car> arrCars = new ArrayList<Car>();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            Car car = new Car();
            car.setId(cursor.getInt(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_ID)));
            car.setName(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_NAME)));
            car.setImageUrl(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_IMAGE_URL)));
            car.setPrice(Float.parseFloat(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_PRICE))));
            car.setBrand(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_BRAND)));
            car.setType(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_TYPE)));
            car.setRating(Float.parseFloat(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_RATING))));
            car.setColor(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_COLOR)));
            car.setEngineCC(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_ENGINE_CC)));
            car.setMileage(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_MILEAGE)));
            car.setAbsExists(Integer.parseInt(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_ABS_EXISTS))) == 1);
            car.setDescription(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_DESCRIPTION)));
            car.setLink(cursor.getString(cursor.getColumnIndex(QCDbConstants.Cars.COLUMN_LINK)));

            arrCars.add(car);

            cursor.moveToNext();
        }

        if(cursor != null && !cursor.isClosed())
            cursor.close();

        return arrCars;
    }
}
