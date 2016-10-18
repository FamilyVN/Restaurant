package com.tuananh.restaurant.manager.ui.utility;

import com.tuananh.restaurant.manager.data.model.board.GroupBoard;

import java.util.List;

/**
 * Created by framgia on 18/10/2016.
 */
public class Utils {

    public static void unSelected(List<GroupBoard> groupBoardList) {
        for (GroupBoard groupBoard : groupBoardList) {
            groupBoard.setSelected(false);
        }
    }
}
