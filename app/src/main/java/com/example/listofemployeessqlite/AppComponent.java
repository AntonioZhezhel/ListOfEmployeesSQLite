package com.example.listofemployeessqlite;

import android.app.Application;

import com.example.listofemployeessqlite.di.ActivityModule;
import com.example.listofemployeessqlite.di.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

//a component class that is used along with
// the generated module classes to generate dependencies using dagger.

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class}
)
public interface AppComponent {

    void inject(BaseApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
