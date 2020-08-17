package com.example.listofemployeessqlite.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//using @Database the database class is annotated

//Inside the annotation arguments,
// an array of entities is provided in which
// a list of tables / entities that are needed inside the database is provided

@Database(entities = {EmployeesModel.class},version = 5,exportSchema = false)
public abstract class EmployeesDatabase extends RoomDatabase {

    public abstract EmployeesDao employeesDao();
    private static volatile EmployeesDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static EmployeesDatabase getDatabase(final Context context){

        if (INSTANCE == null){
            synchronized (EmployeesDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),EmployeesDatabase.class,"bws_coworker").build();
                }
            }
        }
        return INSTANCE;
    }
}
