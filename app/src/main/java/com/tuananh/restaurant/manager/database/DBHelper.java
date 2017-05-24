package com.tuananh.restaurant.manager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tuananh.databasehelper.DatabaseHelper;

/**
 * Created by framgia on 28/04/2017.
 */
public class DBHelper extends DatabaseHelper {
    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS";
    private static DBHelper sInstance;
    private boolean mIsCopyFromAsset = true;

    private DBHelper(Context context) {
        // boolean isCopyFromAsset == true -> copy database from asset
        // boolean isCopyFromAsset == false -> create new database in onCreate
        super(context, DATABASE_NAME, null, DATABASE_VERSION, true);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (!mIsCopyFromAsset) {
            onCreateTable(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (!mIsCopyFromAsset) {
            db.execSQL(DROP_TABLE_IF_EXISTS + DBConstant.TABLE_BOARD);
            db.execSQL(DROP_TABLE_IF_EXISTS + DBConstant.TABLE_GROUP_BOARD);
            db.execSQL(DROP_TABLE_IF_EXISTS + DBConstant.TABLE_GROUP_COMMODITY);
            db.execSQL(DROP_TABLE_IF_EXISTS + DBConstant.TABLE_COMMODITY);
            db.execSQL(DROP_TABLE_IF_EXISTS + DBConstant.TABLE_SETTING);
            db.execSQL(DROP_TABLE_IF_EXISTS + DBConstant.TABLE_BOARD_COMMODITY);
            onCreate(db);
        }
    }

    private void onCreateTable(SQLiteDatabase db) {
        String CREATE_TABLE_BOARD =
            "CREATE TABLE " + DBConstant.TABLE_BOARD + "("
                + DBConstant.KEY_ID_BOARD + " INTEGER PRIMARY KEY,"
                + DBConstant.KEY_FOR_ID_GROUP_BOARD + " INTEGER,"
                + DBConstant.KEY_NAME_BOARD + " TEXT,"
                + DBConstant.KEY_IS_SAVE + " INTEGER,"
                + DBConstant.KEY_IS_PAID + " INTEGER" + ")";
        String CREATE_TABLE_GROUP_BOARD =
            "CREATE TABLE " + DBConstant.TABLE_GROUP_BOARD + "("
                + DBConstant.KEY_ID_GROUP_BOARD + " INTEGER PRIMARY KEY,"
                + DBConstant.KEY_NAME_GROUP_BOARD + " TEXT" + ")";
        String CREATE_TABLE_GROUP_COMMODITY =
            "CREATE TABLE " + DBConstant.TABLE_GROUP_COMMODITY + "("
                + DBConstant.KEY_ID_GROUP_COMMODITY + " INTEGER PRIMARY KEY,"
                + DBConstant.KEY_NAME_GROUP_COMMODITY + " TEXT" + ")";
        String CREATE_TABLE_COMMODITY =
            "CREATE TABLE " + DBConstant.TABLE_COMMODITY + "("
                + DBConstant.KEY_ID_COMMODITY + " INTEGER PRIMARY KEY,"
                + DBConstant.KEY_NAME_COMMODITY + " TEXT,"
                + DBConstant.KEY_COST_COMMODITY + " INTEGER,"
                + DBConstant.KEY_FOR_ID_GROUP_COMMODITY + " INTEGER,"
                + DBConstant.KEY_IS_COMMON_COMMODITY + " INTEGER,"
                + DBConstant.KEY_NUMBER_COMMODITY + " INTEGER" + ")";
        String CREATE_TABLE_SETTING =
            "CREATE TABLE " + DBConstant.TABLE_SETTING + "("
                + DBConstant.KEY_ID_SETTING + " INTEGER PRIMARY KEY,"
                + DBConstant.KEY_NAME_SETTING + " TEXT,"
                + DBConstant.KEY_ID_IMAGE_SETTING + " INTEGER" + ")";
        String CREATE_TABLE_BOARD_COMMODITY =
            "CREATE TABLE " + DBConstant.TABLE_BOARD_COMMODITY + "("
                + DBConstant.KEY_ID_BOARD_COMMODITY + " INTEGER PRIMARY KEY,"
                + DBConstant.KEY_ID_BOARD + " INTEGER,"
                + DBConstant.KEY_ID_COMMODITY + " INTEGER,"
                + DBConstant.KEY_NUMBER_COMMODITY_IN_BOARD + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_BOARD);
        db.execSQL(CREATE_TABLE_GROUP_BOARD);
        db.execSQL(CREATE_TABLE_GROUP_COMMODITY);
        db.execSQL(CREATE_TABLE_COMMODITY);
        db.execSQL(CREATE_TABLE_SETTING);
        db.execSQL(CREATE_TABLE_BOARD_COMMODITY);
    }
}
