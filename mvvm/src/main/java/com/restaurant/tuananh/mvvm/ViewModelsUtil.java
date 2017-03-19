package com.restaurant.tuananh.mvvm;

import android.databinding.ViewDataBinding;

import java.util.WeakHashMap;

/**
 * Created by TuanAnh on 3/19/2017.
 */
public class ViewModelsUtil {
    private static ViewModelsUtil sInstance;
    private WeakHashMap<Class, BaseViewModel> mModels = new WeakHashMap<>();

    public static ViewModelsUtil getInstance() {
        if (sInstance == null) sInstance = new ViewModelsUtil();
        return sInstance;
    }

    public void add(BaseViewModel model) {
        mModels.put(model.getClass(), model);
    }

    public <T extends BaseViewModel> T get(Class<T> model) {
        return (T) mModels.get(model);
    }

    public <T extends BaseViewModel> T get(String className) {
        try {
            Class<BaseViewModel> c = (Class<BaseViewModel>) Class.forName(className);
            return (T) mModels.get(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends BaseViewModel> T newInstance(String className) {
        try {
            Class<BaseViewModel> c = (Class<BaseViewModel>) Class.forName(className);
            return (T) c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends ViewDataBinding> void remove(BaseViewModel model) {
        mModels.remove(model.getClass());
    }
}
