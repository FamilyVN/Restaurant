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
                        new Board(idGroupBoard, "Bàn " + ((idGroupBoard - 1) * 5 + i)));
                }
                idGroupBoard++;
                if (idGroupBoard == 6) {
                    dbBoard.addBoard(
                        new Board(idGroupBoard - 1, "Bàn " + ((idGroupBoard - 2) * 5 + 6)));
                }
            }
            //
            DBGroupCommodity dbGroupCommodity = dbHelper.getDBGroupCommodity();
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Phổ biến"));
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nước mía"));
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nước dừa"));
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Đồ ăn"));
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Thuốc lá"));
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Nhân trần"));
            dbGroupCommodity.addGroupCommodity(new GroupCommodity("Khác"));
            //
            DBCommodity dbCommodity = dbHelper.getDBCommodity();
            dbCommodity.addCommodity(new Commodity("Nước mía", 7000, 2, true));
            dbCommodity.addCommodity(new Commodity("Nước dừa", 12000, 3, true));
            dbCommodity.addCommodity(new Commodity("Nhân trần", 3000, 6, true));
            //
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.UPDATE, false);
            editor.apply();
        }
    }
}
