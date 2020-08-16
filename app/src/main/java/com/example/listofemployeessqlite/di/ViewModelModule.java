package com.example.listofemployeessqlite.di;

import androidx.lifecycle.ViewModel;

import com.example.listofemployeessqlite.view.MainActivityVM;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

//this module class provides all view models in the project.
// All ViewModels are defined here

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM.class)
    abstract ViewModel bindMainActivityVM(MainActivityVM mainActivityVM);
}
