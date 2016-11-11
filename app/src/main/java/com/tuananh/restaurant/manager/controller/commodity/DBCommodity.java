package com.tuananh.restaurant.manager.controller.commodity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.controller.database.DBTest;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;

import java.util.ArrayList;
import java.util.List;

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
        values.put(Constants.KEY_IS_COMMON_COMMODITY, commodity.getIsCommon());
//        values.put(Constants.KEY_NUMBER_COMMODITY, commodity.getNumber());
        values.put(Constants.KEY_NUMBER_COMMODITY, 1);
        db.insert(Constants.TABLE_COMMODITY, null, values);
        db.close();
    }

    public List<Commodity> getCommodityAllByIdGroupCommodity(int idGroupCommodity) {
        List<Commodity> commodityList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Constants.TABLE_COMMODITY + " WHERE " +
            Constants.KEY_FOR_ID_GROUP_COMMODITY + " =?";
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(idGroupCommodity)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Commodity commodity =
                    new Commodity(
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID_COMMODITY)),
                        cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_COST_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_FOR_ID_GROUP_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_IS_COMMON_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_NUMBER_COMMODITY))
                    );
                commodityList.add(commodity);
            } while (cursor.moveToNext());
        }
        db.close();
        return commodityList;
    }

    public List<Commodity> getCommodityAllCommon() {
        List<Commodity> commodityList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Constants.TABLE_COMMODITY
            + " WHERE " + Constants.KEY_IS_COMMON_COMMODITY + " =?";
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(DBTest.COMMON)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Commodity commodity =
                    new Commodity(
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID_COMMODITY)),
                        cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_COST_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_FOR_ID_GROUP_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_IS_COMMON_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_NUMBER_COMMODITY))
                    );
                commodityList.add(commodity);
            } while (cursor.moveToNext());
        }
        db.close();
        return commodityList;
    }
}
