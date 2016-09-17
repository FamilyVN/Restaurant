package com.tuananh.restaurant.manager.data.controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.Board;

/**
 * Created by framgia on 17/09/2016.
 */
public class DBBoard {
    private SQLiteOpenHelper mSQLiteOpenHelper;

    public DBBoard(SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLiteOpenHelper = sqLiteOpenHelper;
    }

    public void addBoard(Board board) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_BOARD, board.getNameBoard());
        values.put(Constants.KEY_ID_GROUP_BOARD, board.getNameBoard());
        db.insert(Constants.TABLE_BOARD, null, values);
        db.close();
    }

    public boolean updateBoard(Board board) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_BOARD, board.getNameBoard());
        values.put(Constants.KEY_ID_GROUP_BOARD, board.getIdGroup());
        int checkUpdate = db.update(Constants.TABLE_BOARD, values, Constants.KEY_ID_BOARD + " = ?",
            new String[]{String.valueOf(board.getId())});
        db.close();
        return checkUpdate >= Constants.CHECK_ADD_TRUE;
    }
}
