package com.restaurant.tuananh.mvvm;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

/**
 * Created by TuanAnh on 3/19/2017.
 */
public abstract class BaseFragment<TB extends ViewDataBinding, TM extends BaseViewModel>
    extends Fragment implements ViewInterface {
    protected TB mBinding;
    protected TM mModel;

    public void init(@Nullable Bundle savedInstanceState, LayoutInflater inflater, @Nullable
        ViewGroup container, @LayoutRes int layoutResource, Class<TM> vmClass) {
        mBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false);
        //  mModel = ViewModelsUtil.getInstance().get(vmClass);
        if (mModel == null) {
            try {
                mModel = vmClass.newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        mModel.onCreate(savedInstanceState);
        if (!mBinding.setVariable(com.restaurant.tuananh.mvvm.BR.viewModel, mModel)) {
            throw new IllegalArgumentException("You should add 'viewModel' variable");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        init(savedInstanceState, container, inflater);
        return mBinding.getRoot();
    }

    public void init(@Nullable Bundle savedInstanceState, @Nullable ViewGroup container,
                     LayoutInflater inflater) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        String name = ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[1].toString();
        //mModel = ViewModelsUtil.getInstance().get(name.replace("class ", ""));
        if (mModel == null) {
            mModel = ViewModelsUtil.getInstance().newInstance(name.replace("class ", ""));
        }
        mModel.onCreate(savedInstanceState);
        if (!mBinding.setVariable(com.restaurant.tuananh.mvvm.BR.viewModel, mModel)) {
            throw new IllegalArgumentException("You should add 'viewModel' variable");
        }
    }

    @Override
    public void onDestroyView() {
        mModel.onDetached(getActivity() == null || getActivity().isFinishing());
        super.onDestroy();
    }

    public TM getViewModel() {
        return mModel;
    }

    public TB getBinding() {
        return mBinding;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mModel != null) mModel.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mModel != null) mModel.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        if (mModel != null) {
            mModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onStart() {
        super.onStart();
        mModel.onStart();
        mModel.attach(mBinding, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mModel.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mModel.onStop();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (mModel != null) super.onViewStateRestored(savedInstanceState);
        mModel.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        if (mModel != null) mModel.onSaveInstanceState(saveInstanceState);
        super.onSaveInstanceState(saveInstanceState);
    }
}
