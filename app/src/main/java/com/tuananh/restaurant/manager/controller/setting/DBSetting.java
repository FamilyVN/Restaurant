package com.tuananh.restaurant.manager.controller.setting;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.setting.Setting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 29/10/2016.
 */
public class DBSetting {
    private SQLiteOpenHelper mSQLiteOpenHelper;

    public DBSetting(SQLiteOpenHelper SQLiteOpenHelper) {
        mSQLiteOpenHelper = SQLiteOpenHelper;
    }

    public void addSetting(Setting setting) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_SETTING, setting.getName());
        values.put(Constants.KEY_ID_IMAGE_SETTING, setting.getIdImage());
        db.insert(Constants.TABLE_SETTING, null, values);
        db.close();
    }

    public List<Setting> getSettingAll() {
        List<Setting> settingList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_SETTING;
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Setting setting =
                    new Setting(
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID_SETTING)),
                        cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_SETTING)),
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID_IMAGE_SETTING))
                    );
                settingList.add(setting);
            } while (cursor.moveToNext());
        }
        db.close();
        return settingList;
    }
}
