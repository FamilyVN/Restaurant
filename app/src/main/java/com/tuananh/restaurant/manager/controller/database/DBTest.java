package com.tuananh.restaurant.manager.controller.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.controller.board.DBBoard;
import com.tuananh.restaurant.manager.controller.board.DBGroupBoard;
import com.tuananh.restaurant.manager.controller.commodity.DBCommodity;
import com.tuananh.restaurant.manager.controller.commodity.DBGroupCommodity;
import com.tuananh.restaurant.manager.controller.setting.DBSetting;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;
import com.tuananh.restaurant.manager.data.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.data.model.setting.Setting;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;

/**
 * Created by framgia on 17/09/2016.
 */
public class DBTest {
    public final static int NOT_COMMON = 0;
    public final static int COMMON = 1;
    public final static int NOT_SAVE = 0;
    public final static int SAVE = 1;

    public static void createDB(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
            Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Constants.UPDATE, true)) {
            DBHelper dbHelper = new DBHelper(context);
            dbHelper.createDBBoard();
            dbHelper.createDBGroupBoard();
            dbHelper.createDBGroupCommodity();
            dbHelper.createDBCommodity();
            dbHelper.createDBSetting();
            //---
            DBGroupBoard dbGroupBoard = dbHelper.getDBGroupBoard();
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 1-5"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 6-10"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 11-15"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 16-20"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 21-25"));
            //---
            DBBoard dbBoard = dbHelper.getDBBoard();
            int idGroupBoard = 1;
            while (idGroupBoard < 6) {
                for (int i = 1; i <= 5; i++) {
                    dbBoard.addBoard(
                        new Board(idGroupBoard, "Bàn " + ((idGroupBoard - 1) * 5 + i), NOT_SAVE));
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
            DBCommodity dbCommodity = dbHelper.getDBCommodity();
            // nuoc mia
            dbCommodity.addCommodity(new Commodity("Nước mía", 7000, 2, COMMON));
            dbCommodity.addCommodity(new Commodity("Nước mía ko đá", 8000, 2, NOT_COMMON));
            // nuoc dua
            dbCommodity.addCommodity(new Commodity("Nước dừa", 12000, 3, COMMON));
            dbCommodity.addCommodity(new Commodity("Quả dừa", 25000, 3, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Ca dừa", 25000, 3, NOT_COMMON));
            // sua chua
            dbCommodity.addCommodity(new Commodity("Sữa chua", 5000, 4, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Sữa chua đánh đá", 7000, 4, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Sữa chua thạch", 10000, 4, NOT_COMMON));
            // nuoc ngot
            dbCommodity.addCommodity(new Commodity("Chanh muối", 8000, 5, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("247", 10000, 5, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Coca lon", 10000, 5, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Number1", 10000, 5, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Bò húc", 12000, 5, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Bia hà nội", 10000, 5, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Sting", 10000, 5, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Lavie", 5000, 5, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Nutri", 12000, 5, NOT_COMMON));
            // nhan tran
            dbCommodity.addCommodity(new Commodity("Nhân trần", 3000, 6, COMMON));
            dbCommodity.addCommodity(new Commodity("Ca nhân trần", 15000, 6, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Nửa Ca nhân trần", 8000, 6, NOT_COMMON));
            // do an
            dbCommodity.addCommodity(new Commodity("Ostar", 7000, 7, COMMON));
            dbCommodity.addCommodity(new Commodity("Bim bim", 5000, 7, COMMON));
            dbCommodity.addCommodity(new Commodity("Hướng dương", 5000, 7, COMMON));
            dbCommodity.addCommodity(new Commodity("Kẹo mút", 1000, 7, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Bánh mỳ nho", 4000, 7, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Bò khô", 5000, 7, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Giấy lau", 2000, 7, NOT_COMMON));
            // thuoc la
            dbCommodity.addCommodity(new Commodity("Vina", 20000, 8, COMMON));
            dbCommodity.addCommodity(new Commodity("Thăng long", 10000, 8, COMMON));
            dbCommodity.addCommodity(new Commodity("Ngựa", 23000, 8, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Mèo", 22000, 8, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Man trắng", 25000, 8, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("3 số", 30000, 8, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Kent bạc", 25000, 8, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Bật lửa", 2000, 8, COMMON));
            // nuoc ngam
            dbCommodity.addCommodity(new Commodity("Nước sấu", 10000, 9, COMMON));
            //---
            DBSetting dbSetting = dbHelper.getDBSetting();
            dbSetting.addSetting(new Setting("Nhóm thực đơn", R.drawable.ic_group_foods));
            dbSetting.addSetting(new Setting("Đồ uống, món ăn", R.drawable.ic_food));
            dbSetting.addSetting(new Setting("Sơ đồ bàn", R.drawable.ic_map_table));
            //
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.UPDATE, false);
            editor.apply();
        }
    }
}
