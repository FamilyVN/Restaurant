package com.tuananh.restaurant.manager.view.activity.settings.roomboard;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityRoomBoardBinding;
import com.tuananh.restaurant.manager.view.activity.BaseActivityRestaurant;

/**
 * Created by FRAMGIA\vu.tuan.anh on 20/07/2017.
 */
public class MapRoomBoardActivity
    extends BaseActivityRestaurant<ActivityRoomBoardBinding, MapRoomBoardActivityViewModel> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_room_board;
    }
}
