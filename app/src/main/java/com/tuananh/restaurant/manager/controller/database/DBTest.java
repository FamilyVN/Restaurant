package com.tuananh.restaurant.manager.controller.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.controller.board.DBGroupBoard;
import com.tuananh.restaurant.manager.controller.commodity.DBGroupCommodity;
import com.tuananh.restaurant.manager.controller.setting.DBSetting;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.model.setting.Setting;

/**
 * Created by framgia on 17/09/2016.
 */
public class DBTest {
    public final static int NOT_COMMON = 0;
    public final static int NOT_SAVE = 0;
    public final static int NOT_PAID = 0;

    public static void createDB(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
            Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Constant.UPDATE, true)) {
            DBHelper dbHelper = new DBHelper(context);
            dbHelper.createDBGroupBoard();
            dbHelper.createDBGroupCommodity();
            dbHelper.createDBSetting();
            //---
            DBGroupBoard dbGroupBoard = dbHelper.getDBGroupBoard();
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 1-5"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 6-10"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 11-15"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 16-20"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 21-25"));
            //---
            int idGroupBoard = 1;
            while (idGroupBoard < 6) {
                for (int i = 1; i <= 5; i++) {
                    DatabaseManager.getInstance(context).insertBoard(new Board(
                        idGroupBoard, "Bàn " + ((idGroupBoard - 1) * 5 + i), NOT_SAVE, NOT_PAID));
                }
                idGroupBoard++;
            }
            //---
            DBGroupCommodity dbGroupCommodity = dbHelper.getDBGroupCommodity();
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Phổ biến")); // 1
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nước mía")); // 2
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nước dừa")); // 3
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Sữa chua")); // 4
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nước ngọt"));// 5
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nhân trần"));// 6
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Đồ ăn"));    // 7
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Thuốc lá")); // 8
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nước ngâm"));// 9
            //
            // nuoc mia
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Nước mía", 7000, 2, Constant.COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Nước mía ko đá", 8000, 2, NOT_COMMON));
            // nuoc dua
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Nước dừa", 12000, 3, Constant.COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Quả dừa", 25000, 3, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Ca dừa", 25000, 3, NOT_COMMON));
            // sua chua
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Sữa chua", 5000, 4, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Sữa chua đánh đá", 7000, 4, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Sữa chua thạch", 10000, 4, NOT_COMMON));
            // nuoc ngot
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Chanh muối", 8000, 5, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("247", 10000, 5, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Coca lon", 10000, 5, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Number1", 10000, 5, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Bò húc", 12000, 5, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Bia hà nội", 10000, 5, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Sting", 10000, 5, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Lavie", 5000, 5, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Nutri", 12000, 5, NOT_COMMON));
            // nhan tran
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Nhân trần", 3000, 6, Constant.COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Ca nhân trần", 15000, 6, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Nửa Ca nhân trần", 8000, 6, NOT_COMMON));
            // do an
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Ostar", 7000, 7, Constant.COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Bim bim", 5000, 7, Constant.COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Hướng dương", 5000, 7, Constant.COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Kẹo mút", 1000, 7, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Bánh mỳ nho", 4000, 7, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Bò khô", 5000, 7, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Giấy lau", 2000, 7, NOT_COMMON));
            // thuoc la
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Vina", 20000, 8, Constant.COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Thăng long", 10000, 8, Constant.COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Ngựa", 23000, 8, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Mèo", 22000, 8, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Man trắng", 25000, 8, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("3 số", 30000, 8, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Kent bạc", 25000, 8, NOT_COMMON));
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Bật lửa", 2000, 8, Constant.COMMON));
            // nuoc ngam
            DatabaseManager.getInstance(context)
                .insertCommodity(new Commodity("Nước sấu", 10000, 9, Constant.COMMON));
            //---
            DBSetting dbSetting = dbHelper.getDBSetting();
            dbSetting.addSetting(new Setting("Nhóm thực đơn", R.drawable.ic_group_foods));
            dbSetting.addSetting(new Setting("Đồ uống, món ăn", R.drawable.ic_food));
            dbSetting.addSetting(new Setting("Sơ đồ bàn", R.drawable.ic_map_table));
            //
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constant.UPDATE, false);
            editor.apply();
        }
    }
}
