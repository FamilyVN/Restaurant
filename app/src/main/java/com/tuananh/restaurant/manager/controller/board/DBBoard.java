package com.tuananh.restaurant.manager.controller.board;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.database.DBConstant;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;

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
        values.put(DBConstant.KEY_NAME_BOARD, board.getNameBoard());
        values.put(DBConstant.KEY_FOR_ID_GROUP_BOARD, board.getIdGroup());
        db.insert(DBConstant.TABLE_BOARD, null, values);
        db.close();
    }

    public boolean updateBoard(Board board) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_NAME_BOARD, board.getNameBoard());
        values.put(DBConstant.KEY_FOR_ID_GROUP_BOARD, board.getIdGroup());
        int checkUpdate =
            db.update(DBConstant.TABLE_BOARD, values, DBConstant.KEY_ID_BOARD + " = ?",
                new String[]{String.valueOf(board.getId())});
        db.close();
        return checkUpdate >= Constant.CHECK_ADD_TRUE;
    }

    public List<Board> getBoardAllByIdGroupBoard(int idGroupBoard) {
        List<Board> boardList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DBConstant.TABLE_BOARD + " WHERE "
            + DBConstant.KEY_FOR_ID_GROUP_BOARD + " =?";
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(idGroupBoard)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Board board = new Board(
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_FOR_ID_GROUP_BOARD)),
                    cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_SAVE))
                );
                boardList.add(board);
            } while (cursor.moveToNext());
        }
        db.close();
        return boardList;
    }

    public Board getBoardById(int idBoard) {
        Board board = null;
        String selectQuery = "SELECT * FROM " + DBConstant.TABLE_BOARD + " WHERE "
            + DBConstant.KEY_ID_BOARD + " =?";
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(idBoard)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                board = new Board(
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_FOR_ID_GROUP_BOARD)),
                    cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_SAVE))
                );
            } while (cursor.moveToNext());
        }
        db.close();
        return board;
    }
}
