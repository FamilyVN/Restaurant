package com.tuananh.restaurant.manager.data.controller.commodity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;

/**
 * Created by framgia on 11/10/2016.
 */
public class DBCommodity {
    private SQLiteOpenHelper mSQLiteOpenHelper;

    public DBCommodity(SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLiteOpenHelper = sqLiteOpenHelper;
    }

    public void addCommodity(Commodity commodity) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_COMMODITY, commodity.getName());
        values.put(Constants.KEY_COST_COMMODITY, commodity.getCost());
        values.put(Constants.KEY_FOR_ID_GROUP_COMMODITY, commodity.getIdGroupCommodity());
        values.put(Constants.KEY_COMMON_COMMODITY, commodity.isCommon());
        db.insert(Constants.TABLE_COMMODITY, null, values);
        db.close();
    }
}
