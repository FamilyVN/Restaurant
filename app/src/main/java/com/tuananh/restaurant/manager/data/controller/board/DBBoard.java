package com.tuananh.restaurant.manager.data.controller.board;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.board.Board;

import java.util.ArrayList;
import java.util.List;

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
        values.put(Constants.KEY_FOR_ID_GROUP_BOARD, board.getIdGroup());
        db.insert(Constants.TABLE_BOARD, null, values);
        db.close();
    }

    public boolean updateBoard(Board board) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_BOARD, board.getNameBoard());
        values.put(Constants.KEY_FOR_ID_GROUP_BOARD, board.getIdGroup());
        int checkUpdate = db.update(Constants.TABLE_BOARD, values, Constants.KEY_ID_BOARD + " = ?",
            new String[]{String.valueOf(board.getId())});
        db.close();
        return checkUpdate >= Constants.CHECK_ADD_TRUE;
    }

    public List<Board> getBoardAllByIdGroupBoard(int idGroupBoard) {
        List<Board> boardList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Constants.TABLE_BOARD + " WHERE "
            + Constants.KEY_FOR_ID_GROUP_BOARD + " =?";
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + idGroupBoard});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Board board = new Board(
                    cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(Constants.KEY_FOR_ID_GROUP_BOARD)),
                    cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(Constants.KEY_IS_SAVE))
                );
                boardList.add(board);
            } while (cursor.moveToNext());
        }
        db.close();
        return boardList;
    }
}
