package com.restaurant.tuananh.mvvm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by TuanAnh on 3/19/2017.
 */
public class BaseViewModel<T extends ViewDataBinding, TV extends ViewInterface>
    extends BaseObservable {
    private static final String TAG = "BaseVM";
    private T mBinding;
    private Handler mHandler = new Handler();
    private Thread mUiThread;
    private TV mView;
    private boolean mIsInitialized = false;
    private boolean mIsFirst = true;

    public BaseViewModel() {
        ViewModelsUtil.getInstance().add(this);
    }

    public boolean IsFirst() {
        return mIsFirst;
    }

    public boolean IsInitialized() {
        return mIsInitialized;
    }

    protected void onSaveInstanceState(Bundle saveInstanceState) {
    }

    public void onCreate(Bundle savedInstanceState) {
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    @CallSuper
    public void attach(final T binding, ViewInterface view) {
        init(binding, view);
        onAttached(!mIsInitialized);
        mIsFirst = !mIsInitialized;
        if (!mIsInitialized) mIsInitialized = true;
    }

    @CallSuper
    public void onDetached(boolean isFinish) {
        Log.d(TAG, "onDetached, isFinish: " + isFinish + " class: " + getClass().getName());
        mView = null;
        if (isFinish) finish();
    }

    @CallSuper
    public void onAttached(boolean isFirst) {
        Log.d(TAG, "onAttached, isFirst: " + isFirst + " class: " + getClass().getName());
    }

    private void init(final T binding, ViewInterface view) {
        mBinding = binding;
        mView = (TV) view;
        mUiThread = Thread.currentThread();
    }

    public void Toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public final void runOnUiThread(Runnable action) {
        if (Thread.currentThread() != mUiThread) {
            mHandler.post(action);
        } else {
            action.run();
        }
    }

    @CallSuper
    public void finish() {
        Log.d(TAG, "finish " + getClass().getName());
        ViewModelsUtil.getInstance().remove(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
    }

    public void onLoaded() {
    }

    public boolean hasView() {
        return mView != null;
    }

    public TV getView() {
        return mView;
    }

    public Activity getActivity() {
        return hasView() ? mView.getActivity() : null;
    }

    public Context getContext() {
        return mBinding.getRoot().getContext();
    }

    protected Context getApplicationContext() {
        return getActivity() == null ? null : getActivity().getApplicationContext();
    }

    protected Application getApplication() {
        return getActivity() == null ? null : getActivity().getApplication();
    }

    protected Resources getResources() {
        return getContext() == null ? null : getContext().getResources();
    }

    protected String getString(int resId) {
        return getResources() == null ? null : getResources().getString(resId);
    }

    protected Intent getIntent() {
        return getActivity() == null ? null : getActivity().getIntent();
    }

    public void startActivity(Intent intent) {
        getActivity().startActivity(intent);
    }

    public T getBinding() {
        return mBinding;
    }

    public FragmentManager getSupportFragmentManager() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity == null) return null;
        return activity.getSupportFragmentManager();
    }

    public FragmentTransaction beginSupportFragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }

    protected void onStart() {
    }

    protected void onRestart() {
    }

    protected void onStop() {
    }

    protected void onPause() {
    }

    protected void onResume() {
    }

    protected void onConfigurationChanged(Configuration newConfig) {
    }
}
