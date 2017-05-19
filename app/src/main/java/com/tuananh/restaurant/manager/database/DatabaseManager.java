package com.tuananh.restaurant.manager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tuananh.databasehelper.queryhelper.QueryHelper;
import com.tuananh.restaurant.manager.controller.database.DBTest;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 04/05/2017.
 */
public class DatabaseManager implements DatabaseInterface {
    private static DatabaseManager sInstance;
    private Context mContext;

    private DatabaseManager(Context context) {
        mContext = context;
    }

    public static synchronized DatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseManager(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public List<Board> getBoardAllByIdGroupBoard(int idGroupBoard) {
        List<Board> boardList = new ArrayList<>();
        QueryHelper queryHelper = new QueryHelper();
        queryHelper
            .setTableName(DBConstant.TABLE_BOARD)
            .addCondition(DBConstant.KEY_FOR_ID_GROUP_BOARD, idGroupBoard);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
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
        return boardList;
    }

    @Override
    public List<GroupBoard> getGroupBoardAll() {
        List<GroupBoard> groupBoardList = new ArrayList<>();
        QueryHelper queryHelper = new QueryHelper();
        queryHelper.setTableName(DBConstant.TABLE_GROUP_BOARD);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                GroupBoard groupBoard = new GroupBoard(
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_GROUP_BOARD)),
                    cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_GROUP_BOARD))
                );
                groupBoardList.add(groupBoard);
            } while (cursor.moveToNext());
        }
        return groupBoardList;
    }

    @Override
    public Board getBoardById(int idBoard) {
        Board board = null;
        QueryHelper queryHelper = new QueryHelper();
        queryHelper
            .setTableName(DBConstant.TABLE_BOARD)
            .addCondition(DBConstant.KEY_ID_BOARD, idBoard);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
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
        return board;
    }

    @Override
    public void addBoardCommodity(int idBoard, int idCommodity, int number) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_ID_BOARD, idBoard);
        values.put(DBConstant.KEY_ID_COMMODITY, idCommodity);
        values.put(DBConstant.KEY_NUMBER_COMMODITY_IN_BOARD, number);
        db.insert(DBConstant.TABLE_BOARD_COMMODITY, null, values);
        db.close();
    }

    @Override
    public List<GroupCommodity> getGroupCommodityAll() {
        List<GroupCommodity> groupCommodityList = new ArrayList<>();
        QueryHelper queryHelper = new QueryHelper();
        queryHelper.setTableName(DBConstant.TABLE_GROUP_COMMODITY);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                GroupCommodity groupCommodity =
                    new GroupCommodity(
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_GROUP_COMMODITY)),
                        cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_GROUP_COMMODITY))
                    );
                groupCommodityList.add(groupCommodity);
            } while (cursor.moveToNext());
        }
        return groupCommodityList;
    }

    @Override
    public List<Commodity> getCommodityAllCommon() {
        List<Commodity> commodityList = new ArrayList<>();
        QueryHelper queryHelper = new QueryHelper();
        queryHelper
            .setTableName(DBConstant.TABLE_COMMODITY)
            .addCondition(DBConstant.KEY_IS_COMMON_COMMODITY, DBTest.COMMON);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Commodity commodity =
                    new Commodity(
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_COMMODITY)),
                        cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_COST_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_FOR_ID_GROUP_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_COMMON_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_NUMBER_COMMODITY))
                    );
                commodityList.add(commodity);
            } while (cursor.moveToNext());
        }
        return commodityList;
    }

    public List<Commodity> getCommodityAllByIdGroupCommodity(int idGroupCommodity) {
        List<Commodity> commodityList = new ArrayList<>();
        QueryHelper queryHelper = new QueryHelper();
        queryHelper
            .setTableName(DBConstant.TABLE_COMMODITY)
            .addCondition(DBConstant.KEY_FOR_ID_GROUP_COMMODITY, idGroupCommodity);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Commodity commodity =
                    new Commodity(
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_COMMODITY)),
                        cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_COST_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_FOR_ID_GROUP_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_COMMON_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_NUMBER_COMMODITY))
                    );
                commodityList.add(commodity);
            } while (cursor.moveToNext());
        }
        return commodityList;
    }

    @Override
    public List<Commodity> getCommoditySelectedInBoardList(int idBoard) {
        List<Commodity> commoditySelectedList = new ArrayList<>();
        QueryHelper queryHelper = new QueryHelper();
        queryHelper
            .setJoinTable(DBConstant.TABLE_BOARD_COMMODITY, DBConstant.TABLE_COMMODITY,
                DBConstant.TABLE_BOARD_COMMODITY + "." + DBConstant.KEY_ID_COMMODITY,
                DBConstant.TABLE_COMMODITY + "." + DBConstant.KEY_ID_COMMODITY)
            .addCondition(DBConstant.KEY_ID_BOARD, idBoard);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Commodity commodity =
                    new Commodity(
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_COMMODITY)),
                        cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_COST_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_FOR_ID_GROUP_COMMODITY)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_COMMON_COMMODITY)),
                        cursor
                            .getInt(cursor.getColumnIndex(DBConstant.KEY_NUMBER_COMMODITY_IN_BOARD))
                    );
                commoditySelectedList.add(commodity);
            } while (cursor.moveToNext());
        }
        return commoditySelectedList;
    }
}
