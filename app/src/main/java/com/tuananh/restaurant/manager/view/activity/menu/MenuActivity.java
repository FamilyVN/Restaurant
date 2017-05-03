package com.tuananh.restaurant.manager.view.activity.menu;

import android.view.View;
import android.widget.AdapterView;

import com.restaurant.tuananh.mvvm.BaseActivityHeaderFooter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityMenuBinding;
import com.tuananh.restaurant.manager.model.MenuModel;
import com.tuananh.restaurant.manager.view.adapter.MenuGridViewAdapter;

import java.util.List;

public class MenuActivity
    extends BaseActivityHeaderFooter<ActivityMenuBinding, MenuActivityViewModel>
    implements AdapterView.OnItemClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_menu;
    }

    public void showItemMenu(List<MenuModel> menuModelList) {
        MenuGridViewAdapter menuGridViewAdapter =
            new MenuGridViewAdapter(MenuActivity.this, menuModelList);
        getBinding().setAdapter(menuGridViewAdapter);
        getBinding().gridViewMenu.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case MenuGridViewAdapter.POSITION_SELL:
                // TODO: 03/05/2017
                break;
            case MenuGridViewAdapter.POSITION_QUICK_PAYMENT:
                // TODO: 03/05/2017
                break;
            case MenuGridViewAdapter.POSITION_SETTINGS:
                // TODO: 03/05/2017
                break;
            case MenuGridViewAdapter.POSITION_HELP:
                // TODO: 03/05/2017
                break;
        }
    }
}
