package com.tuananh.restaurant.manager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tuananh.databasehelper.queryhelper.QueryHelper;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.model.setting.Setting;

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
    public boolean insertBoard(Board board) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_NAME_BOARD, board.getNameBoard());
        values.put(DBConstant.KEY_FOR_ID_GROUP_BOARD, board.getIdForGroupBoard());
        values.put(DBConstant.KEY_IS_SAVE, board.isSave());
        values.put(DBConstant.KEY_IS_USED, board.isUsed());
        long checkInsert = db.insert(DBConstant.TABLE_BOARD, null, values);
        db.close();
        return checkInsert != Constant.INSERT_FAILED;
    }

    @Override
    public List<Board> getBoardAllByIdGroupBoard(int idGroupBoard) {
        List<Board> boardList = new ArrayList<>();
        QueryHelper queryHelper = new QueryHelper();
        queryHelper
            .setTableName(DBConstant.TABLE_BOARD)
            .addCondition(DBConstant.KEY_FOR_ID_GROUP_BOARD, idGroupBoard)
            .addCondition(DBConstant.KEY_IS_USED, Constant.TRUE);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Board board = new Board(
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_FOR_ID_GROUP_BOARD)),
                    cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_SAVE)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_USED))
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
    public boolean insertGroupBoard(GroupBoard groupBoard) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_NAME_GROUP_BOARD, groupBoard.getNameGroupBoard());
        long checkInsert = db.insert(DBConstant.TABLE_GROUP_BOARD, null, values);
        db.close();
        return checkInsert != Constant.INSERT_FAILED;
    }

    @Override
    public Board getBoardById(int idBoard) {
        Board board = null;
        QueryHelper queryHelper = new QueryHelper();
        queryHelper
            .setTableName(DBConstant.TABLE_BOARD)
            .addCondition(DBConstant.KEY_ID_BOARD, idBoard)
            .addCondition(DBConstant.KEY_IS_USED, Constant.TRUE);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                board = new Board(
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_FOR_ID_GROUP_BOARD)),
                    cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_SAVE)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_USED))
                );
            } while (cursor.moveToNext());
        }
        return board;
    }

    @Override
    public boolean updateBoard(Board board) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_NAME_BOARD, board.getNameBoard());
        values.put(DBConstant.KEY_FOR_ID_GROUP_BOARD, board.getIdForGroupBoard());
        values.put(DBConstant.KEY_IS_SAVE, board.isSave());
        // if board.isUsed() == false ~~ delete board
        values.put(DBConstant.KEY_IS_USED, board.isUsed());
        int checkUpdate = 0;
        try {
            checkUpdate = db.update(DBConstant.TABLE_BOARD, values,
                DBConstant.KEY_ID_BOARD + "= ?", new String[]{String.valueOf(board.getIdBoard())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return checkUpdate >= Constant.UPDATE_SUCCESS;
    }

    @Override
    public List<Board> getBoardAllSave() {
        List<Board> boardList = new ArrayList<>();
        QueryHelper queryHelper = new QueryHelper();
        queryHelper
            .setTableName(DBConstant.TABLE_BOARD)
            .addCondition(DBConstant.KEY_IS_SAVE, Constant.TRUE)
            .addCondition(DBConstant.KEY_IS_USED, Constant.TRUE);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Board board = new Board(
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_FOR_ID_GROUP_BOARD)),
                    cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_BOARD)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_SAVE)),
                    cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_IS_USED))
                );
                boardList.add(board);
            } while (cursor.moveToNext());
        }
        return boardList;
    }

    @Override
    public boolean insertBoardCommodity(int idBoard, int idCommodity, int number) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_ID_BOARD, idBoard);
        values.put(DBConstant.KEY_ID_COMMODITY, idCommodity);
        values.put(DBConstant.KEY_NUMBER_COMMODITY_IN_BOARD, number);
        values.put(DBConstant.KEY_IS_PAID_IN_BOARD_COMMODITY, Constant.FALSE);
        long checkInsert = db.insert(DBConstant.TABLE_BOARD_COMMODITY, null, values);
        db.close();
        return checkInsert != Constant.INSERT_FAILED;
    }

    @Override
    public boolean updateBoardCommodity(int idBoard, int idCommodity, int number) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_ID_BOARD, idBoard);
        values.put(DBConstant.KEY_ID_COMMODITY, idCommodity);
        values.put(DBConstant.KEY_NUMBER_COMMODITY_IN_BOARD, number);
        int checkUpdate = 0;
        try {
            checkUpdate = db.update(DBConstant.TABLE_BOARD_COMMODITY, values,
                DBConstant.KEY_ID_COMMODITY + "= ? AND " + DBConstant.KEY_ID_BOARD + "= ?",
                new String[]{String.valueOf(idCommodity), String.valueOf(idBoard)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        if (checkUpdate <= 0) {
            return insertBoardCommodity(idBoard, idCommodity, number);
        }
        return checkUpdate >= Constant.UPDATE_SUCCESS;
    }

    @Override
    public boolean deleteBoardCommodity(int idBoard) {
        // not delete row
        // -> update field isPaid = true
        // -> update field isSave = false
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        // values isPaid for boardCommodity table
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_IS_PAID_IN_BOARD_COMMODITY, Constant.TRUE);
        // values isSave for board table
        ContentValues valuesBoard = new ContentValues();
        valuesBoard.put(DBConstant.KEY_IS_SAVE, Constant.FALSE);
        int checkUpdateBoardCommodity = 0;
        int checkUpdateBoard = 0;
        try {
            checkUpdateBoardCommodity = db.update(DBConstant.TABLE_BOARD_COMMODITY, values,
                DBConstant.KEY_ID_BOARD + "= ?", new String[]{String.valueOf(idBoard)});
            checkUpdateBoard = db.update(DBConstant.TABLE_BOARD, valuesBoard,
                DBConstant.KEY_ID_BOARD + "= ?", new String[]{String.valueOf(idBoard)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return checkUpdateBoardCommodity >= Constant.UPDATE_SUCCESS
            && checkUpdateBoard >= Constant.UPDATE_SUCCESS;
    }

    @Override
    public boolean cancelBoardCommodity(int idBoard) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        int deleteBoardCommodity = 0;
        int checkUpdateBoard = 0;
        ContentValues valuesBoard = new ContentValues();
        valuesBoard.put(DBConstant.KEY_IS_SAVE, Constant.FALSE);
        try {
            deleteBoardCommodity = db.delete(DBConstant.TABLE_BOARD_COMMODITY,
                DBConstant.KEY_ID_BOARD + " = ?", new String[]{String.valueOf(idBoard)});
            checkUpdateBoard = db.update(DBConstant.TABLE_BOARD, valuesBoard,
                DBConstant.KEY_ID_BOARD + " = ?", new String[]{String.valueOf(idBoard)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return deleteBoardCommodity > 0 && checkUpdateBoard >= Constant.UPDATE_SUCCESS;
    }

    @Override
    public boolean insertGroupCommodity(GroupCommodity groupCommodity) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_NAME_GROUP_COMMODITY, groupCommodity.getNameGroupCommodity());
        long checkInsert = db.insert(DBConstant.TABLE_GROUP_COMMODITY, null, values);
        db.close();
        return checkInsert != Constant.INSERT_FAILED;
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
            .addCondition(DBConstant.KEY_IS_COMMON_COMMODITY, Constant.COMMON);
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
            .addCondition(
                DBConstant.TABLE_BOARD_COMMODITY + "." + DBConstant.KEY_IS_PAID_IN_BOARD_COMMODITY,
                Constant.FALSE)
            .addCondition(DBConstant.TABLE_BOARD_COMMODITY + "." + DBConstant.KEY_ID_BOARD,
                idBoard);
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
                        cursor.getInt(
                            cursor.getColumnIndex(DBConstant.KEY_NUMBER_COMMODITY_IN_BOARD)),
                        cursor.getInt(
                            cursor.getColumnIndex(DBConstant.KEY_IS_PAID_IN_BOARD_COMMODITY))
                    );
                commoditySelectedList.add(commodity);
            } while (cursor.moveToNext());
        }
        return commoditySelectedList;
    }

    @Override
    public boolean insertCommodity(Commodity commodity) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_NAME_COMMODITY, commodity.getNameCommodity());
        values.put(DBConstant.KEY_COST_COMMODITY, commodity.getCostCommodity());
        values.put(DBConstant.KEY_FOR_ID_GROUP_COMMODITY, commodity.getIdForGroupCommodity());
        values.put(DBConstant.KEY_IS_COMMON_COMMODITY, commodity.isCommonCommodity());
        values.put(DBConstant.KEY_NUMBER_COMMODITY, 1);
        long checkInsert = db.insert(DBConstant.TABLE_COMMODITY, null, values);
        db.close();
        return checkInsert != Constant.INSERT_FAILED;
    }

    @Override
    public boolean updateCommodity(int idCommodity, int isCommonCommodity) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_IS_COMMON_COMMODITY, isCommonCommodity);
        int checkUpdateCommodity = 0;
        try {
            checkUpdateCommodity = db.update(DBConstant.TABLE_COMMODITY, values,
                DBConstant.KEY_ID_COMMODITY + "= ?", new String[]{String.valueOf(idCommodity)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return checkUpdateCommodity >= Constant.UPDATE_SUCCESS;
    }

    @Override
    public boolean deleteCommodity(int idCommodity) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        int checkDeleteCommodity =
            db.delete(DBConstant.TABLE_COMMODITY, DBConstant.KEY_ID_COMMODITY + "=?",
                new String[]{String.valueOf(idCommodity)});
        return checkDeleteCommodity > 0;
    }

    @Override
    public List<Setting> getSettingAll() {
        List<Setting> settingList = new ArrayList<>();
        QueryHelper queryHelper = new QueryHelper();
        queryHelper.setTableName(DBConstant.TABLE_SETTING);
        Cursor cursor = DBHelper.getInstance(mContext).query(queryHelper);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Setting setting =
                    new Setting(
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_SETTING)),
                        cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME_SETTING)),
                        cursor.getInt(cursor.getColumnIndex(DBConstant.KEY_ID_IMAGE_SETTING))
                    );
                settingList.add(setting);
            } while (cursor.moveToNext());
        }
        return settingList;
    }

    @Override
    public boolean insertSetting(Setting setting) {
        SQLiteDatabase db = DBHelper.getInstance(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstant.KEY_NAME_SETTING, setting.getNameSettings());
        values.put(DBConstant.KEY_ID_IMAGE_SETTING, setting.getIdImageSettings());
        long checkInsert = db.insert(DBConstant.TABLE_SETTING, null, values);
        db.close();
        return checkInsert != Constant.INSERT_FAILED;
    }
}
