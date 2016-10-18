package com.tuananh.restaurant.manager.data.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.controller.board.DBBoard;
import com.tuananh.restaurant.manager.data.controller.board.DBGroupBoard;
import com.tuananh.restaurant.manager.data.controller.commodity.DBCommodity;
import com.tuananh.restaurant.manager.data.controller.commodity.DBGroupCommodity;
import com.tuananh.restaurant.manager.data.model.board.Board;
import com.tuananh.restaurant.manager.data.model.board.GroupBoard;
import com.tuananh.restaurant.manager.data.model.commodity.Commodity;
import com.tuananh.restaurant.manager.data.model.commodity.GroupCommodity;

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
            //
            DBGroupBoard dbGroupBoard = dbHelper.getDBGroupBoard();
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 1-5"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 6-10"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 11-15"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 16-20"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 21-26"));
            //
            DBBoard dbBoard = dbHelper.getDBBoard();
            int idGroupBoard = 1;
            while (idGroupBoard < 6) {
                for (int i = 1; i <= 5; i++) {
                    dbBoard.addBoard(
                        new Board(idGroupBoard, "Bàn " + ((idGroupBoard - 1) * 5 + i), NOT_SAVE));
                }
                idGroupBoard++;
                if (idGroupBoard == 6) {
                    dbBoard.addBoard(
                        new Board(idGroupBoard - 1, "Bàn " + ((idGroupBoard - 2) * 5 + 6),
                            NOT_SAVE));
                }
            }
            //
            DBGroupCommodity dbGroupCommodity = dbHelper.getDBGroupCommodity();
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Phổ biến")); // 1
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nước mía")); // 2
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nước dừa")); // 3
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Đồ ăn"));    // 4
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Thuốc lá")); // 5
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nhân trần"));// 6
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Khác"));     // 7
            //
            DBCommodity dbCommodity = dbHelper.getDBCommodity();
            dbCommodity.addCommodity(new Commodity("Nước mía", 7000, 2, COMMON));
            dbCommodity.addCommodity(new Commodity("Nước dừa", 12000, 3, COMMON));
            dbCommodity.addCommodity(new Commodity("Nhân trần", 3000, 6, COMMON));
            dbCommodity.addCommodity(new Commodity("Nước mía không đá", 8000, 2, NOT_COMMON));
            dbCommodity.addCommodity(new Commodity("Hướng dương", 5000, 4, COMMON));
            dbCommodity.addCommodity(new Commodity("Ostar", 7000, 4, COMMON));
            dbCommodity.addCommodity(new Commodity("Bim bim", 5000, 4, COMMON));
            dbCommodity.addCommodity(new Commodity("Vina", 20000, 5, COMMON));
            //
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.UPDATE, false);
            editor.apply();
        }
    }
}
