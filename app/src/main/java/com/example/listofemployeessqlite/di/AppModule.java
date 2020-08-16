package com.example.listofemployeessqlite.di;

import android.app.Application;
import android.content.Context;


import com.example.listofemployeessqlite.persistence.EmployeesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    EmployeesRepository provideEmployeesRepository(Context context){
        return new EmployeesRepository(context);
    }
}
