package com.tuananh.restaurant.manager.view.activity.menu;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.database.DBHelper;
import com.tuananh.restaurant.manager.database.DBTest;
import com.tuananh.restaurant.manager.databinding.ActivityMenuBinding;
import com.tuananh.restaurant.manager.model.MenuModel;
import com.tuananh.restaurant.manager.view.adapter.MenuGridViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 03/05/2017.
 */
public class MenuActivityViewModel extends BaseViewModel<ActivityMenuBinding, MenuActivity> {
    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        if (isFirst) {
            // if isCopyFromAsset == false -> createDB
            if (!DBHelper.getInstance(getContext()).isCopyFromAsset()) {
                DBTest.createDB(getContext());
            }
            createItemMenu();
        }
    }

    private void createItemMenu() {
        List<MenuModel> menuModelList = new ArrayList<>();
        menuModelList.add(new MenuModel(MenuGridViewAdapter.POSITION_SELL,
            android.R.color.holo_orange_light,
            R.string.text_menu_sell,
            R.drawable.img_sell));
        menuModelList.add(new MenuModel(MenuGridViewAdapter.POSITION_QUICK_PAYMENT,
            android.R.color.holo_green_light,
            R.string.text_menu_quick_payment,
            R.drawable.img_quick_payment));
        menuModelList.add(new MenuModel(MenuGridViewAdapter.POSITION_SETTINGS,
            android.R.color.holo_red_light,
            R.string.text_menu_settings,
            R.drawable.img_settings));
        menuModelList.add(new MenuModel(MenuGridViewAdapter.POSITION_HELP,
            android.R.color.holo_blue_light,
            R.string.text_menu_help,
            R.drawable.img_help));
        getView().showItemMenu(menuModelList);
    }
}
