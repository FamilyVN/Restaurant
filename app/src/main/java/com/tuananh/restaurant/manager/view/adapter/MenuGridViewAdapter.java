package com.tuananh.restaurant.manager.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tuananh.restaurant.manager.model.MenuModel;

import java.util.List;

/**
 * Created by framgia on 28/04/2017.
 */
public class MenuGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<MenuModel> mMenuModelList;
    private LayoutInflater mLayoutInflater;

    public MenuGridViewAdapter(Context context,
                               List<MenuModel> menuModelList) {
        mContext = context;
        mMenuModelList = menuModelList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mMenuModelList == null ? 0 : mMenuModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public class GridViewHolder {
        public void bind() {
        }
    }
}
