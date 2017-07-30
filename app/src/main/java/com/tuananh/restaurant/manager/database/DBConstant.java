package com.tuananh.restaurant.manager.database;

/**
 * Created by framgia on 04/05/2017.
 */
public class DBConstant {
    // table board
    public final static String TABLE_BOARD = "board";
    public final static String KEY_ID_BOARD = "id_board";
    public final static String KEY_NAME_BOARD = "name_board";
    public final static String KEY_FOR_ID_GROUP_BOARD = "id_for_group_board";
    public final static String KEY_IS_SAVE = "is_save_board";
    // table group board
    public final static String TABLE_GROUP_BOARD = "group_board";
    public final static String KEY_ID_GROUP_BOARD = "id_group_board";
    public final static String KEY_NAME_GROUP_BOARD = "name_group_board";
    // table group commodity
    public final static String TABLE_GROUP_COMMODITY = "group_commodity";
    public final static String KEY_ID_GROUP_COMMODITY = "id_group_commodity";
    public final static String KEY_NAME_GROUP_COMMODITY = "name_group_commodity";
    // table commodity
    public final static String TABLE_COMMODITY = "commodity";
    public final static String KEY_ID_COMMODITY = "id_commodity";
    public final static String KEY_NAME_COMMODITY = "name_commodity";
    public final static String KEY_COST_COMMODITY = "cost_commodity";
    public final static String KEY_FOR_ID_GROUP_COMMODITY = "id_for_group_commodity";
    public final static String KEY_IS_COMMON_COMMODITY = "is_common_commodity";
    public final static String KEY_NUMBER_COMMODITY = "number_commodity";
    // table setting
    public final static String TABLE_SETTING = "setting";
    public final static String KEY_ID_SETTING = "id_setting";
    public final static String KEY_NAME_SETTING = "name_setting";
    public final static String KEY_ID_IMAGE_SETTING = "id_image_setting";
    // table board_commodity
    public final static String TABLE_BOARD_COMMODITY = "board_commodity";
    public final static String KEY_ID_BOARD_COMMODITY = "id_board_commodity";
    public final static String KEY_NUMBER_COMMODITY_IN_BOARD = "number_commodity_in_board";
    public final static String KEY_IS_PAID_IN_BOARD_COMMODITY = "is_paid_in_board_commodity";
}
