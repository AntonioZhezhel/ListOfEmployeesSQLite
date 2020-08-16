package com.example.listofemployeessqlite.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

//class provides current / updated database changes.
// This defines the methods of live data from the database,
// which will later be observed in the activity.

public class EmployeesRepository {

    private EmployeesDao mEmployeesDao;
    private LiveData<List<EmployeesModel>> mAllEmployees;

    //get dependencies using dagger2
    @Inject
    public EmployeesRepository(Context application){
        EmployeesDatabase db = EmployeesDatabase.getDatabase(application);
        mEmployeesDao = db.employeesDao();
        mAllEmployees = mEmployeesDao.getAlphabetizedWords();
    }

    public LiveData<List<EmployeesModel>> getAllEmployees(){
        return mAllEmployees;
    }

    public void insert(EmployeesModel employeesModel){
        EmployeesDatabase.databaseWriteExecutor.execute(() -> {
            mEmployeesDao.insert(employeesModel);
        });
    }

    public void deleteNote(int employeesId){
        EmployeesDatabase.databaseWriteExecutor.execute(() -> {
            mEmployeesDao.delete(employeesId);
        });
    }
}
