package com.tuananh.restaurant.manager.controller.commodity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.database.DBConstant;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 09/10/2016.
 */
public class DBGroupCommodity {
    private SQLiteOpenHelper mSQLiteOpenHelper;

    public DBGroupCommodity(SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLiteOpenHelper = sqLiteOpenHelper;
    }

    public void addGroupCommodity(GroupCommodity groupCommodity) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_NAME_GROUP_COMMODITY, groupCommodity.getNameGroupCommodity());
        db.insert(DBConstant.TABLE_GROUP_COMMODITY, null, values);
        db.close();
    }

    public List<GroupCommodity> getGroupCommodityAll() {
        List<GroupCommodity> groupCommodityList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DBConstant.TABLE_GROUP_COMMODITY;
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                GroupCommodity groupCommodity =
                    new GroupCommodity(
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_GROUP_COMMODITY)),
                        cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_GROUP_COMMODITY))
                    );
                groupCommodityList.add(groupCommodity);
            } while (cursor.moveToNext());
        }
        db.close();
        return groupCommodityList;
    }
}
