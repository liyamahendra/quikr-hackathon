package com.mahendraliya.quikrcars.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mahendraliya on 21/08/15.
 */
public class QCSQLiteOpenHelper extends SQLiteOpenHelper {

    private Context mContext;
    private static QCSQLiteOpenHelper dbHelperInstance = null;
    private static SQLiteDatabase database;

    private QCSQLiteOpenHelper(Context context) {
        super(context, QCDbConstants.DB_NAME, null, QCDbConstants.DATABASE_VERSION);
        mContext = context;
    }

    public static QCSQLiteOpenHelper getInstance(Context context) {
        if(dbHelperInstance == null) {
            dbHelperInstance = new QCSQLiteOpenHelper(context);
            database = dbHelperInstance.getWritableDatabase();
        }

        return dbHelperInstance;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QCDbConstants.Cars.CREATE_SCRIPT);
        db.execSQL(QCDbConstants.Cities.CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QCDbConstants.Cars.QUERY_DROP_TABLE);
        db.execSQL(QCDbConstants.Cities.QUERY_DROP_TABLE);
        onCreate(db);
    }

    public void clearOldData(SQLiteDatabase db) {
        db.execSQL(QCDbConstants.Cars.QUERY_DELETE);
        db.execSQL(QCDbConstants.Cities.QUERY_DELETE);
    }
}
