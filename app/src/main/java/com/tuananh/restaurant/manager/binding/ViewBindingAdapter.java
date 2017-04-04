package com.tuananh.restaurant.manager.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by TuanAnh on 3/23/2017.
 */
public class ViewBindingAdapter {
    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
                                        RecyclerView.LayoutManager manager) {
        recyclerView.setLayoutManager(manager);
    }

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("adapter")
    public static void setAdapter(GridView gridView, BaseAdapter adapter) {
        gridView.setAdapter(adapter);
    }
}
