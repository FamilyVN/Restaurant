package com.tuananh.restaurant.manager.controller.board;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.data.Constants;

/**
 * Created by framgia on 31/10/2016.
 */
public class DBBoardCommodity {
    private SQLiteOpenHelper mSQLiteOpenHelper;

    public DBBoardCommodity(SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLiteOpenHelper = sqLiteOpenHelper;
    }

    public void addBoardCommodity(int idBoard, int idCommodity, int number) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_ID_BOARD, idBoard);
        values.put(Constants.KEY_ID_COMMODITY, idCommodity);
        values.put(Constants.KEY_NUMBER_COMMODITY_IN_BOARD, number);
        db.insert(Constants.TABLE_BOARD_COMMODITY, null, values);
        db.close();
    }
}
