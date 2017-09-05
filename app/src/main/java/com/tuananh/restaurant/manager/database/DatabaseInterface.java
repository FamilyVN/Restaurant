package com.tuananh.restaurant.manager.database;

import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.model.board.GroupBoard;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;
import com.tuananh.restaurant.manager.model.setting.Setting;

import java.util.List;

/**
 * Created by framgia on 04/05/2017.
 */
public interface DatabaseInterface {
    // board
    boolean insertBoard(Board board);
    List<Board> getBoardAllByIdGroupBoard(int idGroupBoard);
    Board getBoardById(int idBoard);
    boolean updateBoard(Board board);
    List<Board> getBoardAllSave();
    // group board
    List<GroupBoard> getGroupBoardAll();
    boolean insertGroupBoard(GroupBoard groupBoard);
    // board commodity
    boolean insertBoardCommodity(int idBoard, int idCommodity, int number);
    boolean updateBoardCommodity(int idBoard, int idCommodity, int number);
    boolean deleteBoardCommodity(int idBoard);
    // group commodity
    boolean insertGroupCommodity(GroupCommodity groupCommodity);
    List<GroupCommodity> getGroupCommodityAll();
    // commodity
    List<Commodity> getCommodityAllCommon();
    List<Commodity> getCommodityAllByIdGroupCommodity(int idGroupCommodity);
    List<Commodity> getCommoditySelectedInBoardList(int idBoard);
    boolean insertCommodity(Commodity commodity);
    boolean updateCommodity(int idCommodity, int isCommonCommodity);
    boolean deleteCommodity(int idCommodity);
    // setting
    List<Setting> getSettingAll();
    boolean insertSetting(Setting setting);
}
