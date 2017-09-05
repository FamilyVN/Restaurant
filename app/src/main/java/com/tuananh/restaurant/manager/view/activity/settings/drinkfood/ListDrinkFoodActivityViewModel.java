package com.tuananh.restaurant.manager.view.activity.settings.drinkfood;

import android.util.Log;

import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityListDrinkFoodBinding;
import com.tuananh.restaurant.manager.model.BaseViewModelRestaurant;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.commodity.Commodity;
import com.tuananh.restaurant.manager.model.commodity.GroupCommodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\vu.tuan.anh on 19/07/2017.
 */
public class ListDrinkFoodActivityViewModel
    extends BaseViewModelRestaurant<ActivityListDrinkFoodBinding, ListDrinkFoodActivity> {
    private List<GroupCommodity> mGroupCommodityList = new ArrayList<>();
    private List<Commodity> mCommodityList = new ArrayList<>();
    private String mNameGroupCommoditySelected;
    private int mGroupCommodityId;

    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        if (isFirst) {
            loadData();
            loadDataCommodityList(Constant.ID_GROUP_DEFAULT);
        }
    }

    private void loadData() {
        mGroupCommodityList = DatabaseManager.getInstance(getContext()).getGroupCommodityAll();
        if (!mGroupCommodityList.isEmpty()) {
            mGroupCommodityList.get(Constant.ID_GROUP_DEFAULT).setSelected(true);
            setGroupCommodityId(Constant.ID_GROUP_DEFAULT);
            setNameGroupCommoditySelected(
                mGroupCommodityList.get(mGroupCommodityId).getNameGroupCommodity());
        }
        getView().showGroupCommodityList(mGroupCommodityList);
    }

    public void loadDataCommodityList(int position) {
        Log.d("TAG", "position = " + position);
        unSelectedGroupBoard();
        GroupCommodity groupCommodity = mGroupCommodityList.get(position);
        groupCommodity.setSelected(true);
        setNameGroupCommoditySelected(groupCommodity.getNameGroupCommodity());
        mCommodityList.clear();
        List<Commodity> commodityList;
        if (position == Constant.ID_GROUP_DEFAULT) {
            commodityList = DatabaseManager.getInstance(getContext()).getCommodityAllCommon();
            setGroupCommodityId(Constant.ID_GROUP_DEFAULT);
        } else {
            commodityList = DatabaseManager.getInstance(getContext())
                .getCommodityAllByIdGroupCommodity(groupCommodity.getIdGroupCommodity());
            setGroupCommodityId(groupCommodity.getIdGroupCommodity());
        }
        mCommodityList.addAll(commodityList);
        getView().showCommodityList(mCommodityList);
    }

    private void unSelectedGroupBoard() {
        for (GroupCommodity groupCommodity : mGroupCommodityList) {
            groupCommodity.setSelected(false);
        }
    }

    public int getGroupCommodityId() {
        return mGroupCommodityId;
    }

    public void setGroupCommodityId(int groupCommodityId) {
        mGroupCommodityId = groupCommodityId;
        notifyChange();
    }

    public String getNameGroupCommoditySelected() {
        return mNameGroupCommoditySelected;
    }

    public void setNameGroupCommoditySelected(String nameGroupCommoditySelected) {
        mNameGroupCommoditySelected = nameGroupCommoditySelected;
        notifyChange();
    }

    public void removeCommodity(Commodity commodity) {
        mCommodityList.remove(commodity);
        getView().showCommodityList(mCommodityList);
    }
}
