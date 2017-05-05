package com.tuananh.restaurant.manager.database;

import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;

import java.util.List;

/**
 * Created by framgia on 04/05/2017.
 */
public interface DatabaseInterface {
    List<Board> getBoardAllByIdGroupBoard(int idGroupBoard);
    List<GroupBoard> getGroupBoardAll();
}
