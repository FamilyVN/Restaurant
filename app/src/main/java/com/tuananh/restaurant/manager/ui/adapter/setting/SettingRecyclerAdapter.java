package com.tuananh.restaurant.manager.ui.adapter.setting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.model.setting.Setting;
import com.tuananh.restaurant.manager.ui.listener.OnClickSettingItemListener;

import java.util.List;

/**
 * Created by framgia on 28/10/2016.
 */
public class SettingRecyclerAdapter
    extends RecyclerView.Adapter<SettingRecyclerAdapter.SettingViewHolder> {
    private List<Setting> mSettingList;
    private LayoutInflater mLayoutInflater;
    private OnClickSettingItemListener mOnClickSettingItemListener;

    public SettingRecyclerAdapter(Context context, List<Setting> settingList,
                                  OnClickSettingItemListener onClickSettingItemListener) {
        mSettingList = settingList;
        mLayoutInflater = LayoutInflater.from(context);
        mOnClickSettingItemListener = onClickSettingItemListener;
    }

    @Override
    public SettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SettingViewHolder(
            mLayoutInflater.inflate(R.layout.item_settings, parent, false));
    }

    @Override
    public void onBindViewHolder(final SettingViewHolder holder, final int position) {
        Setting setting = mSettingList.get(position);
        holder.mImageViewIcon.setImageResource(setting.getIdImage());
        holder.mTextViewName.setText(setting.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickSettingItemListener != null) {
                    mOnClickSettingItemListener.onClickItem(holder, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSettingList == null ? 0 : mSettingList.size();
    }

    public class SettingViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewIcon;
        private TextView mTextViewName;

        SettingViewHolder(View view) {
            super(view);
            mImageViewIcon = (ImageView) view.findViewById(R.id.icon_item_setting);
            mTextViewName = (TextView) view.findViewById(R.id.text_view_name_item_setting);
        }
    }
}
