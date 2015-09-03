package com.mahendraliya.quikrcars.db;

/**
 * Created by mahendraliya on 21/08/15.
 */
public class QCDbConstants {
    public static final String DB_NAME = "quikrcars.db";

    public static final int DATABASE_VERSION = 2;

    public static class Cars {
        public static final String TABLE_NAME = "tbl_cars";

        public static final String COLUMN_ID                    = "id";
        public static final String COLUMN_NAME                  = "name";
        public static final String COLUMN_IMAGE_URL             = "image_url";
        public static final String COLUMN_PRICE                 = "price";
        public static final String COLUMN_BRAND                 = "brand";
        public static final String COLUMN_TYPE                  = "type";
        public static final String COLUMN_RATING                = "rating";
        public static final String COLUMN_COLOR                 = "color";
        public static final String COLUMN_ENGINE_CC             = "engine_cc";
        public static final String COLUMN_MILEAGE               = "mileage";
        public static final String COLUMN_ABS_EXISTS            = "abs_exists";
        public static final String COLUMN_DESCRIPTION           = "description";
        public static final String COLUMN_LINK                  = "link";



        public static final String CREATE_SCRIPT = "CREATE TABLE " + TABLE_NAME + " ( "
                + COLUMN_ID + "  integer primary key, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_IMAGE_URL + " text, "
                + COLUMN_PRICE + " real, "
                + COLUMN_BRAND + " text, "
                + COLUMN_TYPE + " text, "
                + COLUMN_RATING + " integer, "
                + COLUMN_COLOR + " text, "
                + COLUMN_ENGINE_CC + " text, "
                + COLUMN_MILEAGE + " text, "
                + COLUMN_ABS_EXISTS + " int, "
                + COLUMN_DESCRIPTION + " text, "
                + COLUMN_LINK + " text "
                + "); ";

        //public static final String QUERY_INSERT = "INSERT OR REPLACE INTO " + TABLE_NAME + " ( " +
        public static final String QUERY_INSERT = "INSERT INTO " + TABLE_NAME + " ( " +
                COLUMN_ID + ", " +
                COLUMN_NAME + ", " +
                COLUMN_IMAGE_URL + ", " +
                COLUMN_PRICE + ", " +
                COLUMN_BRAND + ", " +
                COLUMN_TYPE + ", " +
                COLUMN_RATING + ", " +
                COLUMN_COLOR + ", " +
                COLUMN_ENGINE_CC + ", " +
                COLUMN_MILEAGE + ", " +
                COLUMN_ABS_EXISTS + ", " +
                COLUMN_DESCRIPTION + ", " +
                COLUMN_LINK +
                " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
                ;

        public static final String QUERY_SELECT = "SELECT * FROM " + TABLE_NAME;

        public static final String QUERY_DELETE = "DELETE FROM " + TABLE_NAME;

        public static final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String getQueryForCarsSortedByPrice() {
            return new StringBuilder(QUERY_SELECT).append(" ORDER BY ").append(COLUMN_PRICE).toString();
        }

        public static final String getQueryForCarsSortedByRating() {
            return new StringBuilder(QUERY_SELECT).append(" ORDER BY ").append(COLUMN_RATING).append(" DESC").toString();
        }
    }

    public static class Cities {
        public static final String TABLE_NAME = "tbl_cities";

        public static final String COLUMN_ID                    = "id";
        public static final String COLUMN_CAR_ID                = "car_id";
        public static final String COLUMN_CITY_NAME             = "city_name";
        public static final String COLUMN_USERS                 = "users";

        public static final String CREATE_SCRIPT = "CREATE TABLE " + TABLE_NAME + " ( "
                + COLUMN_ID + "  integer primary key autoincrement, "
                + COLUMN_CAR_ID + " integer not null, "
                + COLUMN_CITY_NAME + " text, "
                + COLUMN_USERS + " text "
                + " ); ";

        public static final String QUERY_INSERT = "INSERT OR REPLACE INTO " + TABLE_NAME + " ( " +
                COLUMN_CAR_ID + ", " +
                COLUMN_CITY_NAME + ", " +
                COLUMN_USERS +
                " ) VALUES (?, ?, ?);"
                ;

        public static final String QUERY_SELECT = "SELECT * FROM " + TABLE_NAME;

        public static final String QUERY_DELETE = "DELETE FROM " + TABLE_NAME;

        public static final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String getAllCarCities(int carID) {
            return new StringBuilder(QUERY_SELECT).append(" WHERE ").append(COLUMN_CAR_ID)
                    .append(" = ").append(carID).toString();
        }

    }
}
