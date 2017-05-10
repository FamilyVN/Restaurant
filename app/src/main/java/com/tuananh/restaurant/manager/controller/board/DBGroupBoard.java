package com.tuananh.restaurant.manager.controller.board;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.database.DBConstant;
import com.tuananh.restaurant.manager.model.board.GroupBoard;

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
        values.put(DBConstant.KEY_NAME_GROUP_BOARD, groupBoard.getNameGroupBoard());
        db.insert(DBConstant.TABLE_GROUP_BOARD, null, values);
        db.close();
    }

    public boolean updateGroupBoard(GroupBoard groupBoard) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_NAME_GROUP_BOARD, groupBoard.getNameGroupBoard());
        int checkUpdate =
            db.update(DBConstant.TABLE_GROUP_BOARD, values, DBConstant.KEY_ID_GROUP_BOARD + " = ?",
                new String[]{String.valueOf(groupBoard.getId())});
        db.close();
        return checkUpdate >= Constants.CHECK_ADD_TRUE;
    }

    public List<GroupBoard> getGroupBoardAll() {
        List<GroupBoard> groupBoardList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DBConstant.TABLE_GROUP_BOARD;
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                GroupBoard groupBoard =
                    new GroupBoard(
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_GROUP_BOARD)),
                        cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_GROUP_BOARD))
                    );
                groupBoardList.add(groupBoard);
            } while (cursor.moveToNext());
        }
        db.close();
        return groupBoardList;
    }
}
