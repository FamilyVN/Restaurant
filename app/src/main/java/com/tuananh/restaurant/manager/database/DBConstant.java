package com.tuananh.restaurant.manager.database;

/**
 * Created by framgia on 04/05/2017.
 */
public class DBConstant {
    // table board
    public static final String TABLE_BOARD = "board";
    public static final String KEY_ID_BOARD = "id_board";
    public static final String KEY_NAME_BOARD = "name_board";
    public static final String KEY_FOR_ID_GROUP_BOARD = "id_for_group_board";
    public static final String KEY_IS_SAVE = "is_save_board";
    public static final String KEY_IS_USED = "is_used";
    // table group board
    public static final String TABLE_GROUP_BOARD = "group_board";
    public static final String KEY_ID_GROUP_BOARD = "id_group_board";
    public static final String KEY_NAME_GROUP_BOARD = "name_group_board";
    // table group commodity
    public static final String TABLE_GROUP_COMMODITY = "group_commodity";
    public static final String KEY_ID_GROUP_COMMODITY = "id_group_commodity";
    public static final String KEY_NAME_GROUP_COMMODITY = "name_group_commodity";
    // table commodity
    public static final String TABLE_COMMODITY = "commodity";
    public static final String KEY_ID_COMMODITY = "id_commodity";
    public static final String KEY_NAME_COMMODITY = "name_commodity";
    public static final String KEY_COST_COMMODITY = "cost_commodity";
    public static final String KEY_FOR_ID_GROUP_COMMODITY = "id_for_group_commodity";
    public static final String KEY_IS_COMMON_COMMODITY = "is_common_commodity";
    public static final String KEY_NUMBER_COMMODITY = "number_commodity";
    // table setting
    public static final String TABLE_SETTING = "setting";
    public static final String KEY_ID_SETTING = "id_setting";
    public static final String KEY_NAME_SETTING = "name_setting";
    public static final String KEY_ID_IMAGE_SETTING = "id_image_setting";
    // table board_commodity
    public static final String TABLE_BOARD_COMMODITY = "board_commodity";
    public static final String KEY_ID_BOARD_COMMODITY = "id_board_commodity";
    public static final String KEY_NUMBER_COMMODITY_IN_BOARD = "number_commodity_in_board";
    public static final String KEY_IS_PAID_IN_BOARD_COMMODITY = "is_paid_in_board_commodity";
}
