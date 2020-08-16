package com.example.listofemployeessqlite.di;

import com.example.listofemployeessqlite.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//class is a module, the module will be used to provide various activities
// in the application like MainActivity.
// All future actions are defined here.

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
