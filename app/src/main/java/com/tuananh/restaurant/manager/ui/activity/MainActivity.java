package com.tuananh.restaurant.manager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tuananh.restaurant.manager.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        findViewById(R.id.item_menu_sell).setOnClickListener(this);
        findViewById(R.id.item_menu_quick_payment).setOnClickListener(this);
        findViewById(R.id.item_menu_settings).setOnClickListener(this);
        findViewById(R.id.item_menu_help).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_menu_sell:
                startActivity(new Intent(this, SellActivity.class));
                break;
            case R.id.item_menu_quick_payment:
                // TODO: 11/09/2016  
                break;
            case R.id.item_menu_settings:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.item_menu_help:
                // TODO: 11/09/2016  
                break;
        }
    }
}
