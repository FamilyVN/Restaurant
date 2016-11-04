package com.tuananh.restaurant.manager.controller.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuananh.restaurant.manager.controller.board.DBBoard;
import com.tuananh.restaurant.manager.controller.board.DBBoardCommodity;
import com.tuananh.restaurant.manager.controller.board.DBGroupBoard;
import com.tuananh.restaurant.manager.controller.commodity.DBCommodity;
import com.tuananh.restaurant.manager.controller.commodity.DBGroupCommodity;
import com.tuananh.restaurant.manager.controller.setting.DBSetting;
import com.tuananh.restaurant.manager.data.Constants;

/**
 * Created by framgia on 16/09/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "restaurant";
    private final static String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS";
    private final static int DATABASE_VERSION = 1;
    private DBBoard mDBBoard;
    private DBGroupBoard mDBGroupBoard;
    private DBGroupCommodity mDBGroupCommodity;
    private DBCommodity mDBCommodity;
    private DBSetting mDBSetting;
    private DBBoardCommodity mDBBoardCommodity;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void createDBBoard() {
        mDBBoard = new DBBoard(this);
    }

    public void createDBGroupBoard() {
        mDBGroupBoard = new DBGroupBoard(this);
    }

    public void createDBGroupCommodity() {
        mDBGroupCommodity = new DBGroupCommodity(this);
    }

    public void createDBCommodity() {
        mDBCommodity = new DBCommodity(this);
    }

    public void createDBSetting() {
        mDBSetting = new DBSetting(this);
    }

    public void createDBBoardCommodity() {
        mDBBoardCommodity = new DBBoardCommodity(this);
    }

    public DBBoard getDBBoard() {
        return mDBBoard;
    }

    public DBGroupBoard getDBGroupBoard() {
        return mDBGroupBoard;
    }

    public DBGroupCommodity getDBGroupCommodity() {
        return mDBGroupCommodity;
    }

    public DBCommodity getDBCommodity() {
        return mDBCommodity;
    }

    public DBSetting getDBSetting() {
        return mDBSetting;
    }

    public DBBoardCommodity getDBBoardCommodity() {
        return mDBBoardCommodity;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onCreateTable(db);
    }

    private void onCreateTable(SQLiteDatabase db) {
        String CREATE_TABLE_BOARD =
            "CREATE TABLE " + Constants.TABLE_BOARD + "("
                + Constants.KEY_ID_BOARD + " INTEGER PRIMARY KEY,"
                + Constants.KEY_NAME_BOARD + " TEXT,"
                + Constants.KEY_FOR_ID_GROUP_BOARD + " INTEGER,"
                + Constants.KEY_IS_SAVE + " INTEGER" + ")";
        String CREATE_TABLE_GROUP_BOARD =
            "CREATE TABLE " + Constants.TABLE_GROUP_BOARD + "("
                + Constants.KEY_ID_GROUP_BOARD + " INTEGER PRIMARY KEY,"
                + Constants.KEY_NAME_GROUP_BOARD + " TEXT" + ")";
        String CREATE_TABLE_GROUP_COMMODITY =
            "CREATE TABLE " + Constants.TABLE_GROUP_COMMODITY + "("
                + Constants.KEY_ID_GROUP_COMMODITY + " INTEGER PRIMARY KEY,"
                + Constants.KEY_NAME_GROUP_COMMODITY + " TEXT" + ")";
        String CREATE_TABLE_COMMODITY =
            "CREATE TABLE " + Constants.TABLE_COMMODITY + "("
                + Constants.KEY_ID_COMMODITY + " INTEGER PRIMARY KEY,"
                + Constants.KEY_NAME_COMMODITY + " TEXT,"
                + Constants.KEY_COST_COMMODITY + " INTEGER,"
                + Constants.KEY_FOR_ID_GROUP_COMMODITY + " INTEGER,"
                + Constants.KEY_IS_COMMON_COMMODITY + " INTEGER,"
                + Constants.KEY_NUMBER_COMMODITY + " INTEGER" + ")";
        String CREATE_TABLE_SETTING =
            "CREATE TABLE " + Constants.TABLE_SETTING + "("
                + Constants.KEY_ID_SETTING + " INTEGER PRIMARY KEY,"
                + Constants.KEY_NAME_SETTING + " TEXT,"
                + Constants.KEY_ID_IMAGE_SETTING + " INTEGER" + ")";
        String CREATE_TABLE_BOARD_COMMODITY =
            "CREATE TABLE " + Constants.TABLE_BOARD_COMMODITY + "("
                + Constants.KEY_ID_BOARD_COMMODITY + " INTEGER PRIMARY KEY,"
                + Constants.KEY_ID_BOARD + " INTEGER,"
                + Constants.KEY_NUMBER_COMMODITY_IN_BOARD + " INTEGER,"
                + Constants.KEY_ID_COMMODITY + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_BOARD);
        db.execSQL(CREATE_TABLE_GROUP_BOARD);
        db.execSQL(CREATE_TABLE_GROUP_COMMODITY);
        db.execSQL(CREATE_TABLE_COMMODITY);
        db.execSQL(CREATE_TABLE_SETTING);
        db.execSQL(CREATE_TABLE_BOARD_COMMODITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_IF_EXISTS + Constants.TABLE_BOARD);
        db.execSQL(DROP_TABLE_IF_EXISTS + Constants.TABLE_GROUP_BOARD);
        db.execSQL(DROP_TABLE_IF_EXISTS + Constants.TABLE_GROUP_COMMODITY);
        db.execSQL(DROP_TABLE_IF_EXISTS + Constants.TABLE_COMMODITY);
        db.execSQL(DROP_TABLE_IF_EXISTS + Constants.TABLE_SETTING);
        db.execSQL(DROP_TABLE_IF_EXISTS + Constants.TABLE_BOARD_COMMODITY);
        onCreate(db);
    }
}
