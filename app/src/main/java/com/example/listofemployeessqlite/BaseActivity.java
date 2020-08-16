package com.example.listofemployeessqlite;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.AndroidInjection;

//class takes common parameters for ViewDataBinding, BaseViewModel, etc.
//appropriate actions supply the base activity with parameters as needed.
//Everything happens in basic activity.

public abstract class BaseActivity <T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    private T mViewDataBinding;
    private V mViewModel;

    //getBindingVariable (), getLayoutId (), getViewModel () - methods for getting the binding variable,
    //action layout, and view model from the corresponding actions.

    public abstract int getBindingVariable();

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    private void performDependencyInjection() {

        AndroidInjection.inject(this);
    }

    public T getmViewDataBinding() {
        return mViewDataBinding;
    }

    private void performDataBinding(){

        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
    }

}
