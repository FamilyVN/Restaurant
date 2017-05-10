package com.tuananh.restaurant.manager.controller.board;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.database.DBConstant;

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
        values.put(DBConstant.KEY_ID_BOARD, idBoard);
        values.put(DBConstant.KEY_ID_COMMODITY, idCommodity);
        values.put(DBConstant.KEY_NUMBER_COMMODITY_IN_BOARD, number);
        db.insert(DBConstant.TABLE_BOARD_COMMODITY, null, values);
        db.close();
    }
}
