package com.tuananh.restaurant.manager.database;

import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;

import java.util.List;

/**
 * Created by framgia on 04/05/2017.
 */
public interface DatabaseInterface {
    boolean insertBoard(Board board);
    List<Board> getBoardAllByIdGroupBoard(int idGroupBoard);
    List<GroupBoard> getGroupBoardAll();
    Board getBoardById(int idBoard);
    boolean updateBoard(Board board);
    boolean deleteBoard(int idBoard);
    boolean insertBoardCommodity(int idBoard, int idCommodity, int number);
    boolean updateBoardCommodity(int idBoard, int idCommodity, int number);
    List<GroupCommodity> getGroupCommodityAll();
    List<Commodity> getCommodityAllCommon();
    List<Commodity> getCommodityAllByIdGroupCommodity(int idGroupCommodity);
    List<Commodity> getCommoditySelectedInBoardList(int idBoard);
    boolean insertCommodity(Commodity commodity);
}
