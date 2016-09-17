package com.tuananh.restaurant.manager.data.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.data.controller.DBBoard;
import com.tuananh.restaurant.manager.data.controller.DBGroupBoard;
import com.tuananh.restaurant.manager.data.enums.Status;
import com.tuananh.restaurant.manager.data.model.GroupBoard;

/**
 * Created by framgia on 17/09/2016.
 */
public class DBTest {
    public static void createDB(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants
            .SHARED_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Constants.UPDATE, true)) {
            DBHelper dbHelper = new DBHelper(context);
            dbHelper.create(Status.CREATE, Status.CREATE);
            DBBoard dbBoard = dbHelper.getDBBoard();
            DBGroupBoard dbGroupBoard = dbHelper.getDBGroupBoard();
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 1-5"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 6-10"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 11-15"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 16-20"));
            dbGroupBoard.addGroupBoard(new GroupBoard("Bàn 21-26"));
            //
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.UPDATE, false);
            editor.apply();
        }
    }
}
