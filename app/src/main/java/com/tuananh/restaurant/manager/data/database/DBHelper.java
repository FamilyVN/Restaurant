package com.tuananh.restaurant.manager.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.controller.DBBoard;
import com.tuananh.restaurant.manager.data.controller.DBGroupBoard;
import com.tuananh.restaurant.manager.data.enums.Status;

/**
 * Created by framgia on 16/09/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "restaurant";
    private final static int DATABASE_VERSION = 1;
    private DBBoard mDBBoard;
    private DBGroupBoard mDBGroupBoard;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void create(Status statusDbStatus, Status statusDbGroupStatus) {
        if (statusDbStatus == Status.CREATE) {
            mDBBoard = new DBBoard(this);
        }
        if (statusDbGroupStatus == Status.CREATE) {
            mDBGroupBoard = new DBGroupBoard(this);
        }
    }

    public DBBoard getDBBoard() {
        return mDBBoard;
    }

    public DBGroupBoard getDBGroupBoard() {
        return mDBGroupBoard;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onCreateTable(db);
    }

    private void onCreateTable(SQLiteDatabase db) {
        String CREATE_TABLE_BOARD = "CREATE TABLE " + Constants.TABLE_BOARD + "("
            + Constants.KEY_ID_BOARD + " INTEGER PRIMARY KEY,"
            + Constants.KEY_NAME_BOARD + " TEXT,"
            + Constants.KEY_FOR_ID_GROUP_BOARD + " INTEGER" + ")";
        String CREATE_TABLE_GROUP_BOARD = "CREATE TABLE " + Constants.TABLE_GROUP_BOARD + "("
            + Constants.KEY_ID_GROUP_BOARD + " INTEGER PRIMARY KEY,"
            + Constants.KEY_NAME_GROUP_BOARD + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_BOARD);
        db.execSQL(CREATE_TABLE_GROUP_BOARD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_BOARD);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_GROUP_BOARD);
        onCreate(db);
    }
}
