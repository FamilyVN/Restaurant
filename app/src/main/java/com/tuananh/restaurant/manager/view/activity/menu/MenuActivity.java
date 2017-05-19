package com.tuananh.restaurant.manager.view.activity.menu;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.restaurant.tuananh.mvvm.BaseActivityHeaderFooter;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ActivityMenuBinding;
import com.tuananh.restaurant.manager.model.MenuModel;
import com.tuananh.restaurant.manager.view.activity.payment.PaymentActivity;
import com.tuananh.restaurant.manager.view.activity.sell.SellActivity;
import com.tuananh.restaurant.manager.view.activity.settings.SettingActivity;
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
        Intent intent;
        switch (position) {
            case MenuGridViewAdapter.POSITION_SELL:
                intent = new Intent(MenuActivity.this, SellActivity.class);
                break;
            case MenuGridViewAdapter.POSITION_QUICK_PAYMENT:
                intent = new Intent(MenuActivity.this, PaymentActivity.class);
                break;
            case MenuGridViewAdapter.POSITION_SETTINGS:
                intent = new Intent(MenuActivity.this, SettingActivity.class);
                break;
            default:
                intent = new Intent(MenuActivity.this, SellActivity.class);
                break;
        }
        startActivity(intent);
    }
}
