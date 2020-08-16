package com.example.listofemployeessqlite;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.internal.Beta;

//the application class that initializes
// the dagger to build the required dependencies.

@Beta
    public class BaseApplication extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return  androidInjector;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);
    }



}
