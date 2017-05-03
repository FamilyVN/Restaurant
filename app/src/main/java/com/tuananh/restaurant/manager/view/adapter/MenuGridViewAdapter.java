package com.tuananh.restaurant.manager.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.databinding.ItemGridViewMenuBinding;
import com.tuananh.restaurant.manager.model.MenuModel;

import java.util.List;

/**
 * Created by framgia on 28/04/2017.
 */
public class MenuGridViewAdapter extends BaseAdapter {
    public static final int POSITION_SELL = 0;
    public static final int POSITION_QUICK_PAYMENT = 1;
    public static final int POSITION_SETTINGS = 2;
    public static final int POSITION_HELP = 3;
    private List<MenuModel> mMenuModelList;
    private LayoutInflater mLayoutInflater;

    public MenuGridViewAdapter(Context context,
                               List<MenuModel> menuModelList) {
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
        GridViewHolder gridViewHolder;
        MenuModel menuModel = mMenuModelList.get(position);
        if (convertView == null) {
            ItemGridViewMenuBinding binding =
                DataBindingUtil
                    .inflate(mLayoutInflater, R.layout.item_grid_view_menu, parent, false);
            gridViewHolder = new GridViewHolder(binding);
            convertView = binding.getRoot();
            convertView.setTag(gridViewHolder);
        } else {
            gridViewHolder = (GridViewHolder) convertView.getTag();
        }
        gridViewHolder.mBinding.itemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        gridViewHolder.bind(menuModel);
        return convertView;
    }

    public class GridViewHolder {
        private ItemGridViewMenuBinding mBinding;

        public GridViewHolder(ItemGridViewMenuBinding binding) {
            mBinding = binding;
        }

        public void bind(MenuModel menuModel) {
            mBinding.imageMenu.setImageResource(menuModel.getIdImage());
            mBinding.imageMenu.setBackgroundResource(menuModel.getIdColor());
            mBinding.textMenu.setText(menuModel.getTitle());
        }
    }
}
