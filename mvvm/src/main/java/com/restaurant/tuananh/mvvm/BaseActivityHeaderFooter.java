package com.restaurant.tuananh.mvvm;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.restaurant.tuananh.mvvm.databinding.ActivityHeaderFooterBinding;

import java.lang.reflect.ParameterizedType;

/**
 * Created by TuanAnh on 3/19/2017.
 */
public abstract class BaseActivityHeaderFooter<TB extends ViewDataBinding, TM extends BaseViewModel>
    extends AppCompatActivity implements ViewInterface {
    private ActivityHeaderFooterBinding bindingBase;
    private TB mBinding;
    private TM mModel;
    private boolean mLoaded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    public void init(@Nullable Bundle savedInstanceState) {
        bindingBase = DataBindingUtil.setContentView(this, R.layout.activity_header_footer);
        String name = ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[1].toString();
        mModel = ViewModelsUtil.getInstance().get(name.replace("class ", ""));
        if (mModel == null) {
            mModel = ViewModelsUtil.getInstance().newInstance(name.replace("class ", ""));
        }
        mModel.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), null, false);
        bindingBase.content.addView(mBinding.getRoot());
        if (!mBinding.setVariable(com.restaurant.tuananh.mvvm.BR.viewModel, mModel)) {
            throw new IllegalArgumentException("You should add 'viewModel' variable");
        }
        onViewCreated();
    }

    protected void addHeader(View view) {
        bindingBase.header.removeAllViews();
        bindingBase.header.addView(view);
    }

    protected void addFooter(View view) {
        bindingBase.footer.removeAllViews();
        bindingBase.footer.addView(view);
    }

    @Override
    public void onDestroy() {
        mLoaded = false;
        mModel.onDetached(isFinishing());
        super.onDestroy();
    }

    public TM getViewModel() {
        return mModel;
    }

    public TB getBinding() {
        return mBinding;
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mLoaded = false;
        mModel.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mModel.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        mModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!mLoaded) {
            mLoaded = true;
            mModel.onLoaded();
        }
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mModel.onStart();
        mModel.attach(mBinding, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mModel.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mModel.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mModel.onRestart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mModel.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        mModel.onSaveInstanceState(saveInstanceState);
        super.onSaveInstanceState(saveInstanceState);
    }

    protected void onViewCreated() {
    }
}
