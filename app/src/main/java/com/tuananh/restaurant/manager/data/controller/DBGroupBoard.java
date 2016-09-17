package com.tuananh.restaurant.manager.data.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.GroupBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 17/09/2016.
 */
public class DBGroupBoard {
    private SQLiteOpenHelper mSQLiteOpenHelper;

    public DBGroupBoard(SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLiteOpenHelper = sqLiteOpenHelper;
    }

    public void addGroupBoard(GroupBoard groupBoard) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_GROUP_BOARD, groupBoard.getNameGroupBoard());
        db.insert(Constants.TABLE_GROUP_BOARD, null, values);
        db.close();
    }

    public boolean updateGroupBoard(GroupBoard groupBoard) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_GROUP_BOARD, groupBoard.getNameGroupBoard());
        int checkUpdate =
            db.update(Constants.TABLE_GROUP_BOARD, values, Constants.KEY_ID_GROUP_BOARD + " = ?",
                new String[]{String.valueOf(groupBoard.getId())});
        db.close();
        return checkUpdate >= Constants.CHECK_ADD_TRUE;
    }

    public List<GroupBoard> getGroupBoardAll() {
        List<GroupBoard> historyList = new ArrayList<GroupBoard>();
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_GROUP_BOARD;
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                GroupBoard groupBoard =
                    new GroupBoard(
                        cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID_GROUP_BOARD)),
                        cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_GROUP_BOARD))
                    );
                historyList.add(groupBoard);
            } while (cursor.moveToNext());
        }
        db.close();
        return historyList;
    }
}
